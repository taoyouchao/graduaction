package com.xiaochao.service;

import com.xiaochao.dao.MyUserDetailServiceMapper;
import com.xiaochao.dao.UserDao;
import com.xiaochao.modal.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-07 21:59
 **/
@Service
public class UserService {
    @Resource
    private MyUserDetailServiceMapper myUserDetailServiceMapper;
    @Resource
    private UserDao userDao;

    public String getUserTypeByName(String username){
        return myUserDetailServiceMapper.findRoleByUserName(username).get(0);
    }

    public User getUserByUserName(String username){
        return userDao.getUserByUserName(username);
    }

    public Integer updateUser(User user){
        return userDao.updateUser(user);
    }
}
