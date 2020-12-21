package com.xiaochao.service;

import com.xiaochao.dao.SubjectDao;
import com.xiaochao.modal.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-20 14:52
 **/
@Service
public class SubjectService {

    @Resource
    private SubjectDao subjectDao;
    /**
     * @Description: 通过id获取到该毕业设计下所有的课题
     * @Param: [designId]
     * @return: java.util.List<com.xiaochao.modal.Subject>
     * @Date: 2020/12/20
     */
    public List<Subject> getAllSubject(Integer designId){
        return subjectDao.getAllSubject(designId);
    }

    /**
    * @Description: 教师查询所有自己发布的课题
    * @Param: [designId, adviserId]
    * @return: java.util.List<com.xiaochao.modal.Subject>
    * @Date: 2020/12/20
    */
    public List<Subject> getSubByAdviser(Integer designId,Integer adviserId){
        return subjectDao.getSubByAdviser(designId,adviserId);
    }

    /**
    * @Description: 老师获取自己最大能发布的课题数
    * @Param: [teacherId]
    * @return: java.lang.Integer
    * @Date: 2020/12/20
    */
    public Integer getMaxStu(Integer teacherId){
        return subjectDao.getMaxStudent(teacherId);
    }

    /**
     * @Description: 教师发布课题
     * @Param: [subject]
     * @return: void
     * @Date: 2020/12/20
     */
    public Integer insertSubject(Subject subject){
        return subjectDao.insertSubject(subject);
    }

    public Integer updateMaxStu(Integer teacherId,Integer maxStu){
        return subjectDao.updateMaxStu(teacherId, maxStu);
    }

    /**
     * @Description: 教师删除课题
     * @Param: [subId]
     * @return: void
     * @Date: 2020/12/20
     */
    public Integer deleteSubject(Integer subId){
        return subjectDao.deleteSubject(subId);
    }

    /**
     * @Description: 学生选题
     * @Param: [subId 课题id, studentId 学生id]
     * @return: void
     * @Date: 2020/12/20
     */
    public Integer choseSubject(Integer subId,Integer studentId){
        return subjectDao.choseSubject(subId, studentId);
    }

    /**
     * @Description: 学生修改选题
     * @Param: [stuId 学生id, subId 课题id]
     * @return: void
     * @Date: 2020/12/20
     */
    public Integer updateChose(Integer stuId){
        return subjectDao.updateChose(stuId);
    }






}
