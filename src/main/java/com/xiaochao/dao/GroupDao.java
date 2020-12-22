package com.xiaochao.dao;

import com.xiaochao.modal.Search;
import com.xiaochao.modal.Teacher;
import com.xiaochao.vo.VoAdviser;
import com.xiaochao.vo.VoStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupDao {

    Integer insertGroup(Integer searchId);


    /**
     * 统计小组数量
     *
     * @param designId 毕设 ID
     * @return 小组数量
     */
    Integer countGroup(Integer designId);

    /**
     *
     * 获取指定学生的研究方向
     *
     * @param studendId 学号 ID
     * @return 该学生的研究方向
     */
    Search getSearchBysId(Integer studendId);

    /**
     * 获取指定学生的指导教师
     *
     * @param studentId 学号 ID
     * @return 学生的指定教师
     */
    VoAdviser getTeacherBysId(Integer studentId);

    /**
     * 获取参与答辩的所有学生
     *
     * @param designId 毕设ID
     * @return 所有答辩学生
     */
    List<VoStudent> getAllStudent(Integer designId);

    int getTeacherSubject(Long teacherId);

    List<Integer> subjectIds(Integer designId);

    void updSubBy(@Param("studentId") Long sId, @Param("subjectId") Integer subId);

    List<Teacher> getTeachersByDesignId(int designId);

    int countAllSelect(int designId);
}
