package com.xiaochao.vo;

import com.xiaochao.modal.College;
import com.xiaochao.modal.Major;
import com.xiaochao.modal.Search;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: graduation
 * @description: 教师查看自己指导的学生信息
 * @author: 小超
 * @create: 2020-12-21 15:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("学生课题实体")
public class VoSubjectStudent {
    @ApiModelProperty("学生id")
    private Integer studentId;
    @ApiModelProperty("学生姓名")
    private String studentName;
    @ApiModelProperty("专业")
    private Major major;
    @ApiModelProperty("学院")
    private College college;
    @ApiModelProperty("课题id")
    private Integer subjectId;
    @ApiModelProperty("课题名称")
    private String subjectTitle;
    @ApiModelProperty("研究方向")
    private Search search;
    @ApiModelProperty("课题难度")
    private Integer level;
    @ApiModelProperty("学生课题状态")
    private String subStatue;
    @ApiModelProperty("指导成绩")
    private Float adviserGrade;
    @ApiModelProperty("评审成绩")
    private Float reviewGrade;
    @ApiModelProperty("答辩成绩")
    private Float replyGrade;

}
