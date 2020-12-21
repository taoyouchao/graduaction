package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 专业
 * @author: 小超
 * @create: 2020-12-09 09:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("专业实体类")
public class Major {
    @ApiModelProperty("专业id")
    private int majorId;
    @ApiModelProperty("专业名称")
    private String majorName;
    @ApiModelProperty("专业所属院系")
    private College college;
}
