package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: graduation
 * @description: 学院
 * @author: 小超
 * @create: 2020-12-09 09:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("学院")
public class College {
    @ApiModelProperty("学院id")
    private int collegeId;
    @ApiModelProperty("学院名称")
    private String collegeName;
}
