package com.xiaochao.dao;

import com.xiaochao.modal.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao {

    /**
    * @Description: 通过id获取到该毕业设计下所有的课题
    * @Param: [designId]
    * @return: java.util.List<com.xiaochao.modal.Subject>
    * @Date: 2020/12/20
    */
    public List<Subject> getAllSubject(Integer designId);

    /**
    * @Description: 导师通过自己id获取到所有课题
    * @Param: [designId, adviserId]
    * @return: java.util.List<com.xiaochao.modal.Subject>
    * @Date: 2020/12/20
    */
    public List<Subject> getSubByAdviser(@Param("designId") Integer designId,@Param("adviserId") Integer adviserId);

    /**
    * @Description: 获取该老师剩余学生人数
    * @Param: [subjectId]
    * @return: java.lang.Integer
    * @Date: 2020/12/20
    */
    public Integer getMaxStudent(Integer teacherId);

    /**
    * @Description: 教师发布课题
    * @Param: [subject]
    * @return: void
    * @Date: 2020/12/20
    */
    public Integer insertSubject(Subject subject);

    /**
    * @Description: 更新最大学生人数
    * @Param: [teacherId, maxStu]
    * @return: java.lang.Integer
    * @Date: 2020/12/20
    */
    public Integer updateMaxStu(@Param("teacherId") Integer teacherId,@Param("maxStu") Integer maxStu);

    /**
    * @Description: 教师删除课题
    * @Param: [subId]
    * @return: void
    * @Date: 2020/12/20
    */
    public Integer deleteSubject(Integer subId);

    /**
    * @Description: 学生选题
    * @Param: [subId 课题id, studentId 学生id]
    * @return: void
    * @Date: 2020/12/20
    */
    public Integer choseSubject(@Param("subId") Integer subId,@Param("stuId") Integer studentId);

    /**
    * @Description: 学生修改选题
    * @Param: [stuId 学生id, subId 课题id]
    * @return: void
    * @Date: 2020/12/20
    */
    public Integer updateChose(@Param("stuId") Integer stuId);


}
