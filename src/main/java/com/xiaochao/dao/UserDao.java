package com.xiaochao.dao;

import com.xiaochao.modal.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public User getUserByUserName(String username);

    public Integer updateUser(User user);


}
