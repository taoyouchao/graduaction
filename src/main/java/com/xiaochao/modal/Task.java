package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @program: graduation
 * @description: 任务
 * @author: 小超
 * @create: 2020-12-11 21:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("任务实体")
public class Task {
    @ApiModelProperty("任务id")
    private Integer taskId;
    @ApiModelProperty("任务标题")
    private String taskTitle;
    @ApiModelProperty("任务内容")
    private String taskContent;
    @ApiModelProperty("发布时间")
    private Date releaseTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("任务类型")
    private String taskType;

    private Integer designId;

}
