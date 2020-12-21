package com.xiaochao.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @program: graduation
 * @description: excel批量导入教师信息对象
 * @author: 小超
 * @create: 2020-12-10 14:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExcelTeacher {

    @ExcelIgnore
    private Long id;
    @ExcelProperty("职工号")
    private String username;
    @ExcelProperty("教师名称")
    private String teacherName;
    @ExcelProperty("密码")
    private String password="$2a$10$njUukwP3kVHaoqwlH0yYCOavTKdtnyGi6woAGLt03y0zKQ6SI0OVC";
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("邮箱")
    private String email;
    @ExcelProperty("专业编号")
    private Integer majorId;
    @ExcelProperty("职称")
    private String jobTitle;
    @ExcelProperty("学历")
    private String education;
    @ExcelIgnore
    private Date creatTime=new Date();
    @ExcelProperty("参与毕设编号")
    private Integer designId;
    @ExcelProperty("发布课题数量")
    private Integer maxStu;

}
