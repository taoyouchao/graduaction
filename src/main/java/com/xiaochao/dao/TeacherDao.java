package com.xiaochao.dao;

import com.xiaochao.modal.Teacher;
import com.xiaochao.vo.ExcelTeacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {

    /**
    * @Description: 通过毕业设计id获取所有老师信息
    * @Param: [designId]
    * @return: java.util.List<com.xiaochao.modal.Teacher>
    * @Date: 2020/12/12
    */
    public List<Teacher> getTeachersByDesignId(Integer designId);

    /**
    * @Description: 通过毕业设计id获取所有Excel格式的teacher
    * @Param: [designId]
    * @return: java.util.List<com.xiaochao.vo.ExcelTeacher>
    * @Date: 2020/12/12
    */
    public List<ExcelTeacher> getAllExcelTeacherByDesignId(Integer designId);

    /**
    * @Description: 通过Excel批量导入老师信息
    * @Param: [teachers]
    * @return: void
    * @Date: 2020/12/12
    */
    public void insertExcelTeachers(@Param("list") List<ExcelTeacher> teachers);

    /**
    * @Description: 批量导入的老师信息设置教师角色
    * @Param: [teachers]
    * @return: void
    * @Date: 2020/12/12
    */
    public void insertRoleTeacher(@Param("list") List<ExcelTeacher> teachers);

    /**
    * @Description: 删除教师信息
    * @Param: [teacherId]
    * @return: void
    * @Date: 2020/12/12
    */
    public Integer deleteTeacher(@Param("teacherId") Integer teacherId);

    /**
    * @Description: 教师查询教师个人用户信息
    * @Param: [teacherId]
    * @return: com.xiaochao.modal.Teacher
    * @Date: 2020/12/21
    */
    public Teacher getTeacher(Integer teacherId);

    public Integer updateTeacher(Teacher teacher);


}
