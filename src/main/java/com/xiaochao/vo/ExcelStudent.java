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
 * @description:
 * @author: 小超
 * @create: 2020-12-10 21:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExcelStudent {

    @ExcelIgnore
    private Long id;
    @ExcelProperty("学号")
    private String username;
    @ExcelProperty("学生姓名")
    private String studentName;
    @ExcelProperty("密码")
    private String password="$2a$10$njUukwP3kVHaoqwlH0yYCOavTKdtnyGi6woAGLt03y0zKQ6SI0OVC";
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("邮箱")
    private String email;
    @ExcelProperty("专业编号")
    private Integer majorId;
    @ExcelIgnore
    private Date creatTime=new Date();
    @ExcelProperty("参与毕设编号")
    private Integer designId;

}
