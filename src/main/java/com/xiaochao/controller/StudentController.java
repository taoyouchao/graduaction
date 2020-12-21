package com.xiaochao.controller;

import com.alibaba.excel.EasyExcel;
import com.xiaochao.service.StudentService;
import com.xiaochao.utils.ResultMap;
import com.xiaochao.utils.StudentExcelDataListener;
import com.xiaochao.utils.TeacherExcelDataListener;
import com.xiaochao.vo.ExcelStudent;
import com.xiaochao.vo.ExcelTeacher;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @program: graduation
 * @description: 学生Controller
 * @author: 小超
 * @create: 2020-12-14 17:00
 **/
@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @ApiOperation("根据毕业设计id获取学生列表")
    @GetMapping("/students")
    public Map<String,Object> getTeachersByDesignId(@RequestParam("designId") Integer designId){
        if (designId==null){
            return ResultMap.setResult(200,null,"毕业设计id为空");
        }
        return ResultMap.setResult(200,studentService.getStudentsByDesignId(designId),"获取到studentList");
    }

    @ApiOperation("删除学生信息")
    @DeleteMapping("/student/deleteStudent")
    public Map<String,Object> deleteTeacher(@RequestParam("studentId") Integer studentId){
        Integer result=studentService.deleteStudent(studentId);
        if (result==0){
            return ResultMap.setResult(200,null,"删除失败");
        }else {
            return ResultMap.setResult(200,result,"删除成功");
        }
    }


    @ApiOperation("管理员根据毕业设计id上传学生信息")
    @PostMapping("/student/upExcel")
    public Map<String,Object> uploadExcelTeacher(@RequestParam("file") MultipartFile multipartFile){
        String rootPath="E:\\2020第一学期\\graduation\\files2\\";
        File fileDir=new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            if (multipartFile!=null){
                //以原来的名称命名,覆盖掉旧的，这里也可以使用UUID之类的方式命名，这里就没有处理了
                String storagePath = rootPath+multipartFile.getOriginalFilename();
                System.out.println("上传的文件：" + multipartFile.getName() + "," + multipartFile.getContentType() + "," + multipartFile.getOriginalFilename()
                        +"，保存的路径为：" + storagePath);
                //保存到文件夹
                multipartFile.transferTo(new File(storagePath));
                //保存到数据库
                EasyExcel.read(storagePath, ExcelStudent.class, new StudentExcelDataListener(studentService)).sheet().doRead();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultMap.setResult(200,null,"上传文件成功");
    }






}
