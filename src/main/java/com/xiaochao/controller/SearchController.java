package com.xiaochao.controller;

import com.xiaochao.modal.Search;
import com.xiaochao.service.SearchService;
import com.xiaochao.utils.ResultMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @program: graduation
 * @description: 研究方向Controller
 * @author: 小超
 * @create: 2020-12-15 20:55
 **/
@RestController
public class SearchController {

    @Resource
    private SearchService searchService;

    @PostMapping("/insertSearch")
    public Map<String,Object> insertSearch(@RequestBody Search search,@RequestParam("designId") Integer designId){
        if (designId==null||search==null){
            return ResultMap.setResult(200,null,"接收数据为空");
        }
        Integer integer = searchService.insertSearch(search, designId);
        if (integer==0){
            return ResultMap.setResult(200,null,"插入数据失败");
        }
        return ResultMap.setResult(200,integer,"插入研究方向成功");
    }

    @GetMapping("/getAllSearch")
    public Map<String,Object> getAllSearch(@RequestParam("designId") Integer designId){
        if (designId==null){
            return ResultMap.setResult(200,null,"接收毕业设计id为空");
        }
        return ResultMap.setResult(200,searchService.getAllSearch(designId),"获取所有研究方向成功");
    }

    @DeleteMapping("/deleteSearch")
    public Map<String,Object> deleteSearch(@RequestParam("searchId") Integer searchId,@RequestParam("designId") Integer designId){
        if (searchId==null||designId==null){
            return ResultMap.setResult(200,null,"接收数据为空");
        }
        Integer integer = searchService.deleteSearch(designId, searchId);
        if (integer==null||integer==0){
            ResultMap.setResult(200,null,"删除失败");
        }
        return ResultMap.setResult(200,integer,"删除成功");
    }



}
