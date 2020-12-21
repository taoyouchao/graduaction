package com.xiaochao.service;

import com.xiaochao.dao.GroupDao;
import com.xiaochao.modal.Group;
import com.xiaochao.modal.Search;
import com.xiaochao.modal.Teacher;
import com.xiaochao.utils.ResultMap;
import com.xiaochao.vo.VoAdviser;
import com.xiaochao.vo.VoStudent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName GroupService
 * @Description TODO
 * @Author guangmingdexin
 * @Date 2020/12/21 11:41
 * @Version 1.0
 **/
@Service
public class GroupService {

    @Resource
    private GroupDao groupDao;

    @Resource
    private TeacherService teacherService;

    @Resource
    private SearchService searchService;

    public int countGroup(Integer designId) {

        return groupDao.countGroup(designId);
    }

    public HashMap<String, Object> createGroups(Integer designId) {

        // 首先判断该毕业设计是否已经有分组如果有 则返回消息
        // 防止 重复分组
        int nGroup = groupDao.countGroup(designId);
        if(nGroup > 0) {
            return ResultMap.setResult(400,designId,"已经分组完成");
        }

        // 如果没有分组，则首先创建分组

        // 创建分组首先获取到参加答辩的老师、所有可以参加答辩的学生
        // 所有的研究方向 ，学生的题目 学生的指导老师

        /**
         * 定义：
         * 1. 所有参加答辩的学生数量 nStudent
         * 2. 参加答辩的老师数量 nTeacher
         * 3. 每个教师评审的数量 平均 nStudent / nTeacher
         * 4. 设置默认一组最多多少个学生 nMaxGroupStudent = 20;
         * 5. 设置默认一组最少多少个学生 nMinGroupStudent = 5;
         * 6. 每一组的 答辩老师的老师数量 nGroupTeacher (1 - 3);
         * 7. 组数 nGroup
         * 先将学生分配好
         * 8. 学生加入一组的优先级
         *              TWOLEVEL: 满足人数要求
         *              THREELEVEL : 研究方向一致
         *              FIVESLEVEL : 其他
         * 9. 教师加入一组的优先级
         *
         *              ONELEVEL : 一组中不能有 自己指导的学生
         *              TWOLEVEL : 已有的答辩老师数量
         *
         */
        int nMaxGroupStudent = 20;
        int nMinGroupStudent = 5;
        // 1. 获取所有数据
        // 1.1 获取 nStudent
        List<VoStudent> students = new ArrayList<>();
        // 模拟随便添加几个学生
        for (int i = 0; i < 200; i++) {
            students.add(new VoStudent());
        }

        int nStudent = students.size();

        // 1.2
        List<Teacher> teachers = teacherService.getTeachersByDesignId(designId);
        int nTeacher = teachers.size();

        // 1.3
        List<Search> searches = searchService.getAllSearch(designId);


        // 已经分配好的老师
        int totalTeacher = 0;

        LinkedList<Group> groups = new LinkedList<>();

        // 2. 创建一个组
        // 3. 分配学生
        for (int i = 0; i < nStudent; i++) {
            if(nGroup == 0) {
                // 当前还没有组 该学生只能 新建一个组
                VoStudent s = students.get(i);
                nGroup = createOneGroup(groups, s, designId, nGroup);

            }else {
                // 前面已经有创建好的组了
                // 现在需要判断是否符合加入的要求了
                VoStudent voS = students.get(i);
                Search search = groupDao.getSearchBysId(voS.getId());
                // 如果符合 直接加入组
                // 如果不符合 重新创建小组
                if(!condition(groups, voS, search, nMaxGroupStudent)) {
                    nGroup = createOneGroup(groups, voS, designId, nGroup);

                }

            }
        }

        // 优化 将多个 人数过少的组直接合并到一个组
        // 估计 最多应该为 多少个组
        // 每组人 最少 相应的 组数则最多
        int nMaxGroup = teachers.size() / nMinGroupStudent;
        groups = merge(groups, nMinGroupStudent, nMaxGroup);

        // 4. 分配教师
        for (int i = 0; i < nTeacher; i++) {
            Teacher t = teachers.get(i);
            VoAdviser v = new VoAdviser((int) t.getId().longValue(), t.getUsername(), t.getNickName());
            review(v, groups, 3);
        }

        // 5. 分配 学生 与教师的评审关系 （平均分配 例如 一个组 20 个学生 3 个答辩老师  6 7 7）

        return ResultMap.setResult("200", groups, "分组");

    }


    private int createOneGroup(LinkedList<Group> groups, VoStudent s, Integer designId, int groupNumber) {
        Group group = new Group();
        groups.add(group);

        // 获取 该学生的研究 方向
        Search search = groupDao.getSearchBysId(s.getId());
        VoAdviser t = groupDao.getTeacherBysId(s.getId());

        List<VoStudent> studentList = new ArrayList<>();
        studentList.add(s);

        List<VoAdviser> teacherList = new ArrayList<>();
        teacherList.add(t);

        group.setDesignId(designId);
        group.setGroupNumber(groupNumber + "");
        group.setStudents(studentList);
        group.setSearch(search);
        group.setAdvisers(teacherList);

        groupNumber ++;
        return groupNumber;
    }

    private boolean condition(LinkedList<Group> groups, VoStudent s, Search search,
                              int nMaxGroupStudent) {

        // 从头到尾遍历
        for (Group group : groups) {
            if(group.getStudents().size() >= nMaxGroupStudent) {
                continue;
            }

            if(!group.getSearch().getSearchId().equals(search.getSearchId())) {
                continue;
            }
            // 可以添加到这一组
            group.getStudents().add(s);
            return true;
        }

        return false;

    }

    private LinkedList<Group> merge(LinkedList<Group> groups, int nMinGroupStudent, int nMaxGroup) {
        // 如果 组数少于最大组数就不用合并，直接返回
        if(groups.size() < nMaxGroup) {
            return groups;
        }
        // 合并算法还真的不好想
//        LinkedList<Group> mergeGroup = new LinkedList<>();
//        for (Group group : groups) {
//            // 加入到合并链表中
//            if(group.getStudents().size() < nMinGroupStudent && (!groups.isEmpty())) {
//                mergeGroup.add(group);
//                groups.remove(group);
//            }
//        }
        // 暂时先不合并
        return groups;
    }

    private void review(VoAdviser t, LinkedList<Group> groups, int nDefaultReview) {
        // 从头到尾
        boolean isAdd = false;
        for (Group group : groups) {
            // 首先获取本组的指导教师
            // 排除掉本组的指导教师
            List<VoAdviser> ts = group.getAdvisers();
            boolean flag = false;
            for (VoAdviser v : ts) {
                if(v.getId().equals(t.getId())) {
                    flag = true;
                    // 直接下一组
                    break;
                }
            }
            if(!flag) {
                // 可以评审本组的学生
                // 判断是否已有评审老师
                List<VoAdviser> reviews = group.getReviews();
                if(reviews == null) {
                    reviews = new ArrayList<>();
                }
                // 如果这一组的评审老师 已经超过了 默认的值 则直接放到下一组
                if(reviews.size() <= nDefaultReview) {
                    reviews.add(t);
                    isAdd = true;
                }

            }
        }

        if(!isAdd) {
            // throw new ArithmeticException("该老师： " + t.toString() + "无法分配");
            System.out.println("该老师： " + t.toString() + "无法分配");
        }
    }

}
