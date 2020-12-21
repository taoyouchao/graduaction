package com.xiaochao.controller;

import com.xiaochao.dao.RuleDao;
import com.xiaochao.modal.Rule;
import com.xiaochao.service.RuleService;
import com.xiaochao.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: graduation
 * @description: 规则控制器
 * @author: 小超
 * @create: 2020-12-21 23:09
 **/
@RestController
public class RuleController {

    @Resource
    private RuleService ruleService;

    @ApiOperation("管理员上传规则")
    @PostMapping("/rule/admin/insertRule")
    public Map<String,Object> insertRule(@RequestBody Rule rule){
        if (rule==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer integer = ruleService.upRule(rule);
        if (integer==0){
            return ResultMap.setResult(200,integer,"添加失败");
        }
        return ResultMap.setResult(200,integer,"添加成功");
    }



    @ApiOperation("用户获取规则")
    @GetMapping("/rule/user/getRuleList")
    public Map<String ,Object> getRuleList(@RequestParam("designId") Integer designId){
        if (designId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        List<Rule> ruleList=ruleService.getDesignAllRule(designId);
        return ResultMap.setResult(200,ruleList,"该毕业设计下的所有打分规则");
    }


    @ApiOperation("管理员更新规则")
    @PutMapping("/rule/admin/updateRule")
    public Map<String,Object> updateRule(@RequestBody Rule rule){
        if (rule==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer result = ruleService.updateRule(rule);
        if (result==0){
            return ResultMap.setResult(200,result,"修改失败");
        }
        return ResultMap.setResult(200,result,"修改成功");
    }


    @ApiOperation("管理员删除规则")
    @DeleteMapping("/rule/admin/deleteRule")
    public Map<String,Object> deleteRule(@RequestParam("ruleId") Integer ruleId){
        return ResultMap.setResult(200,ruleService.deleteRule(ruleId),"删除成功");
    }


}
