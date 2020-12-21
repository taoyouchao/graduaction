package com.xiaochao.dao;

import com.xiaochao.modal.Student;
import com.xiaochao.vo.ExcelStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    /**
    * @Description: 通过毕业id获取学生信息列表
    * @Param: [designId]
    * @return: java.util.List<com.xiaochao.modal.Student>
    * @Date: 2020/12/12
    */
    public List<Student> getStudentsByDesignId(Integer designId);

    /**
    * @Description: 通过毕业id获取Excel格式的学生信息列表
    * @Param: [designId]
    * @return: java.util.List<com.xiaochao.vo.ExcelStudent>
    * @Date: 2020/12/12
    */
    public List<ExcelStudent> getAllExcelStudentByDesignId(Integer designId);

    /**
    * @Description: Excel批量插入学生信息
    * @Param: [students]
    * @return: void
    * @Date: 2020/12/12
    */
    public void insertExcelStudents(@Param("list") List<ExcelStudent> students);

    /**
    * @Description: 给批量导入的学生设置学生角色
    * @Param: [students]
    * @return: void
    * @Date: 2020/12/12
    */
    public void insertRoleStudent(@Param("list") List<ExcelStudent> students);


    public Integer deleteStudent(@Param("studentId") Integer studentId);


}
