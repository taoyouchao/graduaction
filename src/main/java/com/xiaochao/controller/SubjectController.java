package com.xiaochao.controller;

import com.xiaochao.modal.Subject;
import com.xiaochao.service.SubjectService;
import com.xiaochao.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @program: graduation
 * @description: 课题控制器
 * @author: 小超
 * @create: 2020-12-20 14:56
 **/
@RestController
public class SubjectController {
    @Resource
    private SubjectService subjectService;

    @ApiOperation("老师查询自己发布的所有课题")
    @GetMapping("/subject/teacher/GetAllSubject")
    public Map<String,Object> getSubByAdviser(@RequestParam("designId") Integer designId,
                                              @RequestParam("adviserId") Integer adviserId){
        if (designId==null||adviserId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,subjectService.getSubByAdviser(designId, adviserId),"获取数据成功");
    }

    @ApiOperation("管理员查询老师发布的所有课题")
    @GetMapping("/subject/admin/GetAllSubject")
    public Map<String,Object> getAllSubject(@RequestParam("designId") Integer designId){
        if (designId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,subjectService.getAllSubject(designId),"获取所有课题成功");
    }

    @ApiOperation("教师发布课题")
    @PostMapping("/subject/teacher/releaseSubject")
    public Map<String,Object> releaseSubject(@RequestBody Subject subject){
        if (subject==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer maxStu = subjectService.getMaxStu(subject.getAdviserId());
        if (maxStu<=0){
            return ResultMap.setResult(200,null,"发布课题以达上限");
        }
        Integer integer = subjectService.insertSubject(subject);
        if (integer==0){
            return ResultMap.setResult(200,integer,"老师发布课题失败");
        }
        subjectService.updateMaxStu(subject.getAdviserId(),maxStu-1);
        return ResultMap.setResult(200,integer,"老师发布课题成功");
    }

    @ApiOperation("管理员和教师都可删除课题")
    @DeleteMapping("/subject/teacher/deleteSubject")
    public Map<String,Object> deleteSubject(@RequestParam("subId") Integer subId,@RequestParam("teacherId")Integer  teacherId){
        if (subId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer maxStu = subjectService.getMaxStu(teacherId);
        Integer integer = subjectService.deleteSubject(subId);
        if (integer==0){
            return ResultMap.setResult(200,integer,"老师删除课题失败");
        }
        subjectService.updateMaxStu(teacherId,maxStu+1);
        return ResultMap.setResult(200,integer,"老师删除课题成功");
    }

    @ApiOperation("学生选题")
    @PostMapping("/subject/student/chose")
    public Map<String,Object> choseSub(@RequestParam("subId") Integer subId,
                                       @RequestParam("studentId") Integer studentId){

        Integer result = subjectService.choseSubject(subId, studentId);
        if (result==0){
            return ResultMap.setResult(200,result,"学生选题失败");
        }
        return ResultMap.setResult(200,result,"学生选题成功");
    }

    @ApiOperation("学生修改选题")
    @PutMapping("/subject/student/updateChose")
    public Map<String,Object> updateChose(@RequestParam("stuId") Integer stuId,
                                          @RequestParam("subId") Integer subId){

        Integer result = subjectService.choseSubject(subId, stuId);
        if (result==0){
            return ResultMap.setResult(200,result,"学生修改选题失败");
        }
        subjectService.updateChose(stuId);
        return ResultMap.setResult(200,result,"学生修改选题成功");
    }

}
