package com.xiaochao.controller;

import com.xiaochao.service.JwtAuthService;
import com.xiaochao.service.UserService;
import com.xiaochao.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-03 20:44
 **/
@RestController
public class UserController {

    @Resource
    JwtAuthService jwtAuthService;
    @Resource
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/designer/login")
    public Map<String, Object> login(@RequestBody Map<String,String> map){
        String username=map.get("username");
        String password=map.get("password");
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return ResultMap.setResult(521,null,"用户名或者密码为空");
        }
        String jwt=null;
        try {
             jwt= jwtAuthService.login(username, password);
        }catch (Exception e){
            return ResultMap.setResult(521,null,"用户名或者密码错误");
        }
        Map<String,String> token=new HashMap<>();
        token.put("tokaen",jwt);
        return ResultMap.setResult(200,token,"登录验证成功");
    }

    @ApiOperation("刷新token")
    @PostMapping(value = "/refreshtoken")
    public Map<String,Object> refresh(@RequestHeader("${jwt.header}") String token){

        return ResultMap.setResult("200", jwtAuthService.refreshToken(token),"刷新token");
    }

    @ApiOperation("根据token获取用户类型")
    @PostMapping(value = "/user_type")
    public String getUserType(@RequestHeader("${jwt.header}") String token){
        if (token.isEmpty()){
            return "token is null";
        }
        String username=jwtAuthService.getUserNameByToken(token);
        return userService.getUserTypeByName(username);
    }

    @ApiOperation("根据token加载用户信息")
    @GetMapping("/userInfo")
    public Map<String, Object> getUserBytoken(@RequestHeader("${jwt.header}") String token){
        if (token.isEmpty()){
            return ResultMap.setResult(200,null,"token is null");
        }
        String userName=jwtAuthService.getUserNameByToken(token);
        return ResultMap.setResult(200,userService.getUserByUserName(userName),"用户信息获取成功");
    }

}
