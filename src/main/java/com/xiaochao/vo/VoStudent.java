package com.xiaochao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 学生vo实体
 * @author: 小超
 * @create: 2020-12-20 16:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("vo学生实体")
public class VoStudent {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("学号")
    private String username;

    @ApiModelProperty("姓名")
    private String nickName;



}
