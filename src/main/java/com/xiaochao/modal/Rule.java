package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-21 21:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("规则实体类")
public class Rule {

    @ApiModelProperty("规则id")
    private Integer ruleId;

    @ApiModelProperty("规则描述")
    private String ruleDes;

    @ApiModelProperty("分数")
    private Float grade;

    @ApiModelProperty("所属毕业设计id")
    private Integer designId;
}
