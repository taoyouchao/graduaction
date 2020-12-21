package com.xiaochao.modal;

import com.xiaochao.vo.VoAdviser;
import com.xiaochao.vo.VoStudent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @program: graduation
 * @description: 分组类
 * @author: 小超
 * @create: 2020-12-16 09:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("小组实体类")
public class Group {

    @ApiModelProperty("小组id")
    private Integer groupId;

    @ApiModelProperty("小组编号")
    private String groupNumber;

    @ApiModelProperty("研究方向")
    private Search search;

    @ApiModelProperty("该小组里的指导老师")
    private List<VoAdviser> advisers;

    @ApiModelProperty("该小组里的课题")
    private List<Subject> subjects;

    @ApiModelProperty("该小组里的学生")
    private List<VoStudent> students;

}
