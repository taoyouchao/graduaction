package com.xiaochao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-19 15:24
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("vo用户实体")
public class VoUser {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String nickName;

    @ApiModelProperty("状态")
    private String statue;

}
