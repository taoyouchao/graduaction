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
 * @description: 用户类
 * @author: 小超
 * @create: 2020-12-07 20:14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("用户类实体")
public class User {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户是否有效")
    private Boolean enabled;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("角色")
    private Role role;

    @ApiModelProperty("专业")
    private Major major;

    @ApiModelProperty("毕业设计")
    private Design design;

    @ApiModelProperty("学院")
    private College college;


}
