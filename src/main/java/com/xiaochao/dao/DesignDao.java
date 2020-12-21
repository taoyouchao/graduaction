package com.xiaochao.dao;

import com.xiaochao.modal.Design;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignDao {

    /**
    * @Description: 通过用户名获取到该用户的参与的毕业设计信息
    * @Param: [userName, statue]
    * @return: com.xiaochao.modal.Design
    * @Date: 2020/12/12
    */
    public List<Design> getDesignByUserName(@Param("username") String userName, @Param("statue") Integer statue);

    public Design getDesign(Integer userId);

    public Integer insertDesign(@Param("design")Design design);

    public Integer updateDesign( Design design);

}
