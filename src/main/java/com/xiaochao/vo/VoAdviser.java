package com.xiaochao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 指导老师
 * @author: 小超
 * @create: 2020-12-20 16:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("vo指导老师实体")
public class VoAdviser {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("职工号")
    private String username;

    @ApiModelProperty("姓名")
    private String nickName;

}
