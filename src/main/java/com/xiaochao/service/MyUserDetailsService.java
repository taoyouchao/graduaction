package com.xiaochao.service;

import com.xiaochao.dao.MyUserDetailServiceMapper;
import com.xiaochao.modal.MyUserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: graduation
 * @description: 将dao层查询出来的用户信息和权限封装成UserDetails
 * @author: 小超
 * @create: 2020-12-03 20:39
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    MyUserDetailServiceMapper myUserDetailServiceMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //用户基础数据加载
        MyUserDetails myUserDetails=myUserDetailServiceMapper.findByUserName(s);
        if (myUserDetails==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //用户角色列表
        List<String> roleCodes = myUserDetailServiceMapper.findRoleByUserName(s);

        //根据角色列表加载当前用户具有的权限
        List<String> authorities = myUserDetailServiceMapper.findAuthorByRoleCodes(roleCodes);

        roleCodes= roleCodes.stream()
                .map(rc -> "ROLE_" + rc)
                .collect(Collectors.toList());
        authorities.addAll(roleCodes);

        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(
                String.join(",",authorities)
        ));
        return myUserDetails;
    }
}
