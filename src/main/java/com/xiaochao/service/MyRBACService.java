package com.xiaochao.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-06 19:21
 **/
@Component("rbacService")
public class MyRBACService {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails){
            //用户所有可以访问的资源
            UserDetails userDetails=(UserDetails) principal;
            //本次用户要访问的资源
            SimpleGrantedAuthority simpleGrantedAuthority=
                    new SimpleGrantedAuthority(request.getRequestURI());
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);
        }
        return false;
    }





}
