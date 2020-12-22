package com.xiaochao.controller;

import com.xiaochao.service.GroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName GroupController
 * @Description TODO
 * @Author guangmingdexin
 * @Date 2020/12/21 10:39
 * @Version 1.0
 **/

@RestController
public class GroupController {

    @Resource
    private GroupService groupService;



    @ApiOperation("发起分组")
    @PostMapping("/groups")
    public Map<String,Object> createGroups(Integer designId) {

        return groupService.createGroups(designId);
    }

    @ApiOperation("模拟选题")
    @PostMapping("/auto_select")
    public void autoSelect(Integer designId) {
        System.out.println("designId: " + designId);
        groupService.autoSelect(designId);
    }

}
