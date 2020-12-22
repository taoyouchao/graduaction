package com.xiaochao.vo;

import com.xiaochao.modal.FileInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-18 16:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("vo任务实体")
public class VoTask {
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
    @ApiModelProperty("附件信息")
    private List<FileInfo> fileInfos;

}
