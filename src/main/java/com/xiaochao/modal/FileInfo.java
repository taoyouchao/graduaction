package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 附件信息
 * @author: 小超
 * @create: 2020-12-18 16:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("附件信息实体类")
public class FileInfo {

    @ApiModelProperty("文件id")
    private Integer fileId;

    @ApiModelProperty("文件名")
    private String fileName;

    @ApiModelProperty("文件存放路径")
    private String rootPath;

    @ApiModelProperty("所属哪个任务下")
    private Integer taskId;

    @ApiModelProperty("附件所属用户")
    private Integer userId;

    @ApiModelProperty("什么用户类型上传的文件")
    private String userType;
}
