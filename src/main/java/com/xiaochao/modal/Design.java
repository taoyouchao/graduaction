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
 * @description: 毕业设计实体类
 * @author: 小超
 * @create: 2020-12-09 09:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("毕业设计实体类")
public class Design {

    @ApiModelProperty("毕业设计id")
    private int designId;
    @ApiModelProperty("毕业设计标题")
    private String designTitle;
    @ApiModelProperty("年级")
    private String grade;
    @ApiModelProperty("毕业设计描述")
    private String designDes;
    @ApiModelProperty("毕业设计开始时间")
    private Date startTime;
    @ApiModelProperty("毕业设计结束时间")
    private Date endTime;
    @ApiModelProperty("开设毕业设计的学院")
    private College college;
    @ApiModelProperty("毕业设计状态")
    private Boolean statue;
    @ApiModelProperty("指导成绩占比")
    private Float advising;
    @ApiModelProperty("评审成绩占比")
    private Float review;
    @ApiModelProperty("答辩成绩占比")
    private Float replay;
    @ApiModelProperty("创建该毕业设计的管理员id")
    private Integer userId;


}
