package com.xiaochao.controller;

import com.alibaba.excel.EasyExcel;
import com.xiaochao.modal.Teacher;
import com.xiaochao.service.TeacherService;
import com.xiaochao.utils.TeacherExcelDataListener;
import com.xiaochao.utils.ResultMap;
import com.xiaochao.vo.ExcelTeacher;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-09 20:49
 **/
@RestController
public class TeacherController {

    @Resource
    private TeacherService teacherService;


    @ApiOperation("根据毕业设计id获取教师列表")
    @GetMapping("/teachers")
    public Map<String,Object> getTeachersByDesignId(@RequestParam("designId") Integer designId){
        if (designId==null){
            return ResultMap.setResult(200,null,"毕业设计id为空");
        }
        return ResultMap.setResult(200,teacherService.getTeachersByDesignId(designId),"获取到teacherList");
    }

    @ApiOperation("删除教师")
    @DeleteMapping("/teacher/deleteTeacher")
    public Map<String,Object> deleteTeacher(@RequestParam("teacherId") Integer teacherId){
        Integer result=teacherService.deleteTeacher(teacherId);
        if (result==0){
            return ResultMap.setResult(200,null,"删除失败");
        }else {
            return ResultMap.setResult(200,result,"删除成功");
        }
    }

    @ApiOperation("管理员根据毕业设计id上传教师信息")
    @PostMapping("/teacher/upExcel")
    public Map<String,Object> uploadExcelTeacher(@RequestParam("file")MultipartFile multipartFile){
        String rootPath="E:\\2020第一学期\\graduation\\files2\\";
        File fileDir=new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
        if (multipartFile!=null){
            //以原来的名称命名,覆盖掉旧的，这里也可以使用UUID之类的方式命名，这里就没有处理了
            String storagePath = rootPath+multipartFile.getOriginalFilename();
            System.out.println("上传的文件：" + multipartFile.getName() + "," + multipartFile.getContentType() + "," + multipartFile.getOriginalFilename()
                    +"，保存的路径为：" + storagePath);
            //保存到文件夹
                multipartFile.transferTo(new File(storagePath));
            //保存到数据库
            EasyExcel.read(storagePath, ExcelTeacher.class, new TeacherExcelDataListener(teacherService)).sheet().doRead();

        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultMap.setResult(200,null,"上传文件成功");
    }

    @ApiOperation("管理员根据毕业设计id导出已经上传的教师信息")
    @GetMapping("/teacher/downloadExcel")
    public Map<String,Object> downLoadExcelTeacher(@RequestParam("fileName") String fileName,HttpServletResponse response){
        OutputStream os = null;
        InputStream is= null;
        String rootPath="E:\\2020第一学期\\graduation\\taskFile\\";

        try {
            os=response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
             fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(rootPath+fileName+".xlsx", ExcelTeacher.class).sheet("teacher模板").doWrite(teacherService.getAllTeacherByDesignId(1));
            //读取流
            File f = new File(rootPath+fileName+".xlsx");
            is = new FileInputStream(f);
            if (is == null) {
                System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return ResultMap.setResult(200,null,"下载文件失败，请检查文件"+fileName+"是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();


        } catch (IOException e) {
            e.printStackTrace();
            return ResultMap.setResult(300,null,"下载文件失败"+e.getMessage());
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @ApiOperation("教师获取用户个人信息")
    @GetMapping("/teacher/getSelfInfo")
    public Map<String,Object> getTeacher(@RequestParam("teacherId")Integer teacherId){
        return ResultMap.setResult(200,teacherService.getTeacher(teacherId),"返回的个人信息");
    }

    @ApiOperation("教师修改用户信息")
    @PutMapping("/teacher/upMySelf")
    public Map<String,Object> upDateTeacher(@RequestBody Teacher teacher){
        return ResultMap.setResult(200,teacherService.updateTeacher(teacher),"更新用户信息");
    }

    


}
