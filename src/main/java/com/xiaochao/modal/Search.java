package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 研究方向
 * @author: 小超
 * @create: 2020-12-12 19:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("研究方向实体")
public class Search {
    @ApiModelProperty("研究方向id")
    private Integer searchId;
    @ApiModelProperty("研究方向类型")
    private String searchType;
}
