package com.xiaochao.controller;

import com.xiaochao.service.SubStuInfoService;
import com.xiaochao.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-21 17:06
 **/
@RestController
public class SubStuInfoController {

    @Resource
    private SubStuInfoService subStuInfoService;

    @ApiOperation("指导老师获取自己指导的学生信息列表")
    @GetMapping("/subStuInfo/getInfo")
    public Map<String,Object> getStuInfoByAdviser(@RequestParam("adviserId") Integer adviserId){
        if (adviserId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,subStuInfoService.getStuInfoByAdviser(adviserId),"获取的学生信息列表");
    }

    @ApiOperation("指导老师打指导成绩")
    @PutMapping("/subStuInfo/upAdviser")
    public Map<String,Object> upAdviser( Integer studentId,Float adviserGrade){
        if (studentId==null||adviserGrade==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer result = subStuInfoService.upAdviser(studentId, adviserGrade);
        if (result==0){
            return ResultMap.setResult(200,result,"打分失败");
        }
        return ResultMap.setResult(200,result,"打分成功");
    }
    @ApiOperation("评审老师打评审成绩")
    @PutMapping("/subStuInfo/upReview")
    public Map<String, Object> upReview(Integer studentId, Float reviewGrade){
        if (studentId==null||reviewGrade==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer result = subStuInfoService.upReview(studentId, reviewGrade);
        if (result==0){
            return ResultMap.setResult(200,result,"打分失败");
        }
        return ResultMap.setResult(200,result,"打分成功");
    }
    @ApiOperation("答辩老师打答辩成绩")
    @PutMapping("/subStuInfo/upReply")
    public Map<String, Object> upReply(Integer studentId, Float replyGrade){
        if (studentId==null||replyGrade==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer result = subStuInfoService.upReply(studentId, replyGrade);
        if (result==0){
            return ResultMap.setResult(200,result,"打分失败");
        }
        return ResultMap.setResult(200,result,"打分成功");
    }







}
