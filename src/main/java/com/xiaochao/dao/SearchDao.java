package com.xiaochao.dao;

import com.xiaochao.modal.Search;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDao {

    /**
    * @Description: 获取所有研究方向
    * @Param: [designId 毕业设计id]
    * @return: java.util.List<com.xiaochao.modal.Search>
    * @Date: 2020/12/12
    */
    public List<Search> getAllSearch(Integer designId);

    /**
    * @Description: 添加研究方向
    * @Param: [search 研究方向, designId 毕业设计id]
    * @return: void
    * @Date: 2020/12/12
    */
    public Integer insertSearch(@Param("search") Search search,@Param("designId") Integer designId);

    /**
    * @Description: 删除研究方向
    * @Param: [designId 毕业设计id, searchId 研究方向]
    * @return: void
    * @Date: 2020/12/12
    */
    public Integer deleteSearch(@Param("designId") Integer designId,@Param("searchId") Integer searchId);

}
