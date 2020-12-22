package com.xiaochao.controller;

import com.xiaochao.modal.Design;
import com.xiaochao.service.DesignService;
import com.xiaochao.utils.JwtTokenUtil;
import com.xiaochao.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @program: graduation
 * @description: 课程设计Controller
 * @author: 小超
 * @create: 2020-12-09 10:02
 **/
@RestController
public class DesignController {

    @Resource
    private DesignService designService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation("获取未归档的毕业设计")
    @GetMapping("/getDesign")
    public Map<String,Object> getDesignByToken(@RequestHeader("${jwt.header}") String token,@RequestParam("statue")Integer statue){
        if (StringUtils.isEmpty(token)){
            return ResultMap.setResult(205,null,"token为空");
        }else if (jwtTokenUtil.isTokenExpired(token)){
            return ResultMap.setResult(204,null,"token过期");
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return  ResultMap.setResult(200,designService.getDesignByUserName(username,statue),"毕业设计信息");
    }

    @ApiOperation("获取未归档的毕业设计")
    @GetMapping("/user/getDesign")
    public Map<String,Object> getDesignByUserId(@RequestParam("userId") Integer userId){
        if (userId==null){
            return ResultMap.setResult(200,null,"userId为空");
        }
        return ResultMap.setResult(200,designService.getDesign(userId),"获取成功");
    }

    @ApiOperation("创建毕业设计")
    @PostMapping("/design/insertDesign")
    public Map<String,Object> insertDesign(@RequestBody Design design ){
        if (design==null){
           return ResultMap.setResult(200,null,"毕业设计为空");
        }
        design.setStatue(false);
        design.setStartTime(new Date());
        Integer result=designService.insertDesign(design);
        if (result==0){
            return ResultMap.setResult(200,null,"新增失败");
        }

        return ResultMap.setResult(200,result,"新增成功");
    }



    @ApiOperation("更新毕业设计")
    @PutMapping("/design/updateDesign")
    public Map<String,Object> updateDesign(@RequestBody Design design){
        if (design==null){
            return ResultMap.setResult(200,null,"毕业设计为空");
        }
        Integer result=designService.updateDesign(design);
        if (result==0){
            return ResultMap.setResult(200,null,"更新失败");
        }
        return ResultMap.setResult(200,result,"更新成功");
    }


}
