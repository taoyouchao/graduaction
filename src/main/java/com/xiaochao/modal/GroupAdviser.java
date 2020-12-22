package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName UserGroup
 * @Description TODO
 * @Author guangmingdexin
 * @Date 2020/12/22 14:05
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("指导教师-分组实体")
public class GroupAdviser {

    @ApiModelProperty("组号")
    private String groupNo;

    @ApiModelProperty("指导教师 ID")
    private Long adviserId;

    @ApiModelProperty("毕设 ID")
    private Integer designId;
}
