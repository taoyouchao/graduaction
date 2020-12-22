package com.xiaochao.controller;

import com.xiaochao.modal.Rule;
import com.xiaochao.service.SubStuInfoService;
import com.xiaochao.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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
    public Map<String,Object> upAdviser(@RequestBody List<Rule> ruleList, Integer studentId){
        Integer size=ruleList.size();
        float adviserGrade=0;
        for (Rule rule : ruleList) {
            adviserGrade+=(rule.getGrade()/size);
        }
        if (studentId==null||ruleList.isEmpty()){
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
    public Map<String, Object> upReview(@RequestBody List<Rule> ruleList,Integer studentId){
        if (studentId==null||ruleList.isEmpty()){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer size=ruleList.size();
        float reviewGrade=0;
        for (Rule rule : ruleList) {
            reviewGrade+=(rule.getGrade()/size);
        }
        Integer result = subStuInfoService.upReview(studentId, reviewGrade);
        if (result==0){
            return ResultMap.setResult(200,result,"打分失败");
        }
        return ResultMap.setResult(200,result,"打分成功");
    }

    @ApiOperation("答辩老师打答辩成绩")
    @PutMapping("/subStuInfo/upReply")
    public Map<String, Object> upReply(@RequestBody List<Rule> ruleList, Integer studentId){
        if (studentId==null||ruleList==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer size=ruleList.size();
        float replyGrade=0;
        for (Rule rule : ruleList) {
            replyGrade+=(rule.getGrade()/size);
        }
        Integer result = subStuInfoService.upReply(studentId, replyGrade);
        if (result==0){
            return ResultMap.setResult(200,result,"打分失败");
        }
        return ResultMap.setResult(200,result,"打分成功");
    }







}
