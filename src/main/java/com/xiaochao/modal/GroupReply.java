package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName GroupReply
 * @Description TODO
 * @Author guangmingdexin
 * @Date 2020/12/22 14:31
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("用户-分组实体")
public class GroupReply {

    @ApiModelProperty("组号")
    private String groupNo;

    @ApiModelProperty("指导教师 ID")
    private Long replyId;

    @ApiModelProperty("毕设 ID")
    private Integer designId;
}
