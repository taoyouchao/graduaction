package com.xiaochao.service;

import com.xiaochao.dao.DesignDao;
import com.xiaochao.modal.Design;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description: 毕业设计service
 * @author: 小超
 * @create: 2020-12-09 09:58
 **/
@Service
public class DesignService {

    @Resource
    private DesignDao designDao;


    public List<Design> getDesignByUserName(String username, Integer statue){
        return designDao.getDesignByUserName(username,statue);
    }

    public Design getDesign(Integer userId){
        return designDao.getDesign(userId);
    }

    public Integer insertDesign(Design design){
        return designDao.insertDesign(design);
    }

    public Integer updateDesign(Design design){
        return designDao.updateDesign(design);
    }

}
