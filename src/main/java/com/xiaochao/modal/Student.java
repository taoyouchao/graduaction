package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 学生实体类
 * @author: 小超
 * @create: 2020-12-09 21:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("学生对象")
public class Student {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("学生姓名")
    private String nickName;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("身份")
    private Role role;

    @ApiModelProperty("专业")
    private Major major;

    @ApiModelProperty("学院")
    private College college;

    @ApiModelProperty("是否满足参与毕设条件")
    private Boolean designCondition;

}
