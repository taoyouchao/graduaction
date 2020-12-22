package com.xiaochao.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 课题实体类
 * @author: 小超
 * @create: 2020-12-14 15:23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("课题实体类")
public class Subject {

    @ApiModelProperty("课题id")
    private Integer subjectId;
    @ApiModelProperty("指导老师id")
    private Integer adviserId;
    @ApiModelProperty("指导老师姓名")
    private String adviserName;
    @ApiModelProperty("课题标题")
    private String subTitle;
    @ApiModelProperty("课题研究方向")
    private Search search;
    @ApiModelProperty("课题描述")
    private String description;
    @ApiModelProperty("课题难度等级")
    private Integer level;
    @ApiModelProperty("从属于哪个毕业设计")
    private Integer designId;
    @ApiModelProperty("是否可选")
    private Boolean checked;
    @ApiModelProperty("选题的学生id")
    private Integer studentId;

}
