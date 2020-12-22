package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName GroupStudentReview
 * @Description TODO
 * @Author guangmingdexin
 * @Date 2020/12/22 14:38
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("用户-分组实体")
public class GroupStudentReview {

    @ApiModelProperty("学生 ID")
    private Long id;

    @ApiModelProperty("评审老师 ID")
    private Long reviewId;

    @ApiModelProperty("毕设 ID")
    private Integer designId;
}
