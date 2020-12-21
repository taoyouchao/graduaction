package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @program: graduation
 * @description: 教师类
 * @author: 小超
 * @create: 2020-12-09 19:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("教师对象")
public class Teacher {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("教师姓名")
    private String nickName;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("角色")
    private Role role;

    @ApiModelProperty("专业")
    private Major major;

    @ApiModelProperty("毕业设计")
    private Design design;

    @ApiModelProperty("学院")
    private College college;

    @ApiModelProperty("职称")
    private String jobTitle;

    @ApiModelProperty("学历")
    private String education;

}
