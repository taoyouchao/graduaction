package com.xiaochao.service;

import com.xiaochao.dao.TeacherDao;
import com.xiaochao.modal.Teacher;
import com.xiaochao.vo.ExcelTeacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-09 20:46
 **/
@Service
public class TeacherService {

    @Resource
    private TeacherDao teacherDao;

    public List<Teacher> getTeachersByDesignId(Integer designId){
        return teacherDao.getTeachersByDesignId(designId);
    }

    public Integer deleteTeacher(Integer teacherId){
        return teacherDao.deleteTeacher(teacherId);
    }

    public List<ExcelTeacher> getAllTeacherByDesignId(Integer designId){
        return teacherDao.getAllExcelTeacherByDesignId(designId);
    }

    public void insertExcelTeachers( List<ExcelTeacher> teachers){
        teacherDao.insertExcelTeachers(teachers);
        teacherDao.insertRoleTeacher(teachers);
    }

    public Teacher getTeacher(Integer teacherId){
        return teacherDao.getTeacher(teacherId);
    }
    public Integer updateTeacher(Teacher teacher){
        return teacherDao.updateTeacher(teacher);
    }

}
