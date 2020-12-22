package com.xiaochao.service;

import com.xiaochao.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-04 16:38
 **/
@Service
public class JwtAuthService {

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    UserDetailsService userDetailsService;
    @Resource
    JwtTokenUtil jwtTokenUtil;


    /**
     * 登录认证换取JWT令牌
     * @return JWT
     */
    public String login(String username,String password)throws Exception{
        try {
            UsernamePasswordAuthenticationToken upToken=new UsernamePasswordAuthenticationToken(username,password);
            Authentication authenticate = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }catch (AuthenticationException e){
            System.out.println("用户名或密码错误");
            throw new  Exception("用户名或密码错误");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    public String refreshToken(String oldToken){
        if (!jwtTokenUtil.isTokenExpired(oldToken)){
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }

    public String getUserNameByToken(String token){
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
