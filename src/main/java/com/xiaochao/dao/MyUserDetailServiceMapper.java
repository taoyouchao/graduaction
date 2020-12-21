package com.xiaochao.dao;

import com.xiaochao.modal.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserDetailServiceMapper {


    @Select("SELECT username,`password`,enabled \n" +
            "FROM `user` u \n" +
            "WHERE u.username = #{userId}")
    MyUserDetails findByUserName(@Param("userId") String userId);

    @Select("SELECT role_code\n" +
            "FROM role r\n" +
            "LEFT JOIN user_role ur ON r.id=ur.role_id\n" +
            "LEFT JOIN `user` u ON u.id=ur.user_id\n" +
            "WHERE u.username= #{userId}")
    List<String> findRoleByUserName(@Param("userId") String userId);


    @Select({
            "<script>",
            "SELECT url",
            "FROM menu m ",
            "LEFT JOIN role_menu rm ON m.id=rm.menu_id ",
            "LEFT JOIN role r ON r.id=rm.role_id ",
            "WHERE r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
                "#{roleCode}",
            "</foreach>",
            "</script>"
            })
    List<String> findAuthorByRoleCodes(@Param("roleCodes") List<String> roleCodes);


}
