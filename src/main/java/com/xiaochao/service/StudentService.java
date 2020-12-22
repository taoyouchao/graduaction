package com.xiaochao.service;

import com.xiaochao.dao.StudentDao;
import com.xiaochao.modal.Student;
import com.xiaochao.modal.Teacher;
import com.xiaochao.vo.ExcelStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-11 09:48
 **/
@Service
public class StudentService {
    @Resource
    private StudentDao studentDao;

    public List<Student> getStudentsByDesignId(Integer designId){
        return studentDao.getStudentsByDesignId(designId);
    }


    public List<ExcelStudent> getAllExcelStudentByDesignId(Integer designId){
        return studentDao.getAllExcelStudentByDesignId(designId);
    }

    public Integer getDesignId(Integer stuId){
        return studentDao.getDesignId(stuId);
    }



    public void insertExcelStudents( List<ExcelStudent> students){
        studentDao.insertExcelStudents(students);
        studentDao.insertRoleStudent(students);
    }

    public Integer deleteStudent(Integer studentId){
        return studentDao.deleteStudent(studentId);
    }







}
