package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 角色实体类
 * @author: 小超
 * @create: 2020-12-07 20:25
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("角色实体类")
public class Role {

    @ApiModelProperty("角色id")
    private int id;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("角色描述")
    private String roleDesc;

}
