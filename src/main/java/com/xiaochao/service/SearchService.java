package com.xiaochao.service;

import com.xiaochao.dao.GroupDao;
import com.xiaochao.dao.SearchDao;
import com.xiaochao.modal.Design;
import com.xiaochao.modal.Group;
import com.xiaochao.modal.Search;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description: 研究方向service
 * @author: 小超
 * @create: 2020-12-15 20:51
 **/
@Service
public class SearchService {

    @Resource
    private SearchDao searchDao;
    @Resource
    private GroupDao groupDao;

    public Integer insertSearch(Search search, Integer designId){
        searchDao.insertSearch(search, designId);
        return groupDao.insertGroup(search.getSearchId());
    }

    public List<Search> getAllSearch(Integer designId){
        return searchDao.getAllSearch(designId);
    }

    public Integer deleteSearch(Integer designId,Integer searchId){
        return searchDao.deleteSearch(designId,searchId);
    }

}
