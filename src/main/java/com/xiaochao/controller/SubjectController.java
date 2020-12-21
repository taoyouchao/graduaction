package com.xiaochao.controller;


import com.xiaochao.modal.Subject;
import com.xiaochao.modal.SubjectFile;
import com.xiaochao.service.FileInfoService;
import com.xiaochao.service.SubjectService;
import com.xiaochao.utils.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @program: graduation
 * @description: 课题控制器
 * @author: 小超
 * @create: 2020-12-20 14:56
 **/
@RestController
public class SubjectController {
    @Resource
    private SubjectService subjectService;
    @Resource
    private FileInfoService fileInfoService;

    @ApiOperation("老师查询自己发布的所有课题")
    @GetMapping("/subject/teacher/GetAllSubject")
    public Map<String,Object> getSubByAdviser(@RequestParam("designId") Integer designId,
                                              @RequestParam("adviserId") Integer adviserId){
        if (designId==null||adviserId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,subjectService.getSubByAdviser(designId, adviserId),"获取数据成功");
    }

    @ApiOperation("管理员查询老师发布的所有课题")
    @GetMapping("/subject/admin/GetAllSubject")
    public Map<String,Object> getAllSubject(@RequestParam("designId") Integer designId){
        if (designId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,subjectService.getAllSubject(designId),"获取所有课题成功");
    }

    @ApiOperation("教师发布课题")
    @PostMapping("/subject/teacher/releaseSubject")
    public Map<String,Object> releaseSubject(@RequestBody Subject subject){
        if (subject==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer maxStu = subjectService.getMaxStu(subject.getAdviserId());
        if (maxStu<=0){
            return ResultMap.setResult(200,null,"发布课题以达上限");
        }
        Integer integer = subjectService.insertSubject(subject);
        if (integer==0){
            return ResultMap.setResult(200,integer,"老师发布课题失败");
        }
        subjectService.updateMaxStu(subject.getAdviserId(),maxStu-1);
        return ResultMap.setResult(200,integer,"老师发布课题成功");
    }

    @ApiOperation("管理员和教师都可删除课题")
    @DeleteMapping("/subject/teacher/deleteSubject")
    public Map<String,Object> deleteSubject(@RequestParam("subId") Integer subId,@RequestParam("teacherId")Integer  teacherId){
        if (subId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer maxStu = subjectService.getMaxStu(teacherId);
        Integer integer = subjectService.deleteSubject(subId);
        if (integer==0){
            return ResultMap.setResult(200,integer,"老师删除课题失败");
        }
        subjectService.updateMaxStu(teacherId,maxStu+1);
        return ResultMap.setResult(200,integer,"老师删除课题成功");
    }

    @ApiOperation("学生选题")
    @PostMapping("/subject/student/chose")
    public Map<String,Object> choseSub(@RequestParam("subId") Integer subId,
                                       @RequestParam("studentId") Integer studentId){

        Integer result = subjectService.choseSubject(subId, studentId);
        if (result==0){
            return ResultMap.setResult(200,result,"学生选题失败");
        }
        return ResultMap.setResult(200,result,"学生选题成功");
    }

    @ApiOperation("学生修改选题")
    @PutMapping("/subject/student/updateChose")
    public Map<String,Object> updateChose(@RequestParam("stuId") Integer stuId,
                                          @RequestParam("subId") Integer subId){

        Integer result = subjectService.choseSubject(subId, stuId);
        if (result==0){
            return ResultMap.setResult(200,result,"学生修改选题失败");
        }
        subjectService.updateChose(stuId);
        return ResultMap.setResult(200,result,"学生修改选题成功");
    }


    @ApiOperation("用户上传课题附件")
    @PostMapping("/user/upSubjectFile")
    public Map<String,Object> upSubjectFile(@RequestParam("file") MultipartFile multipartFile,
                                     @RequestParam("subjectId")Integer subjectId,
                                     @RequestParam("userId")Integer userId,
                                     @RequestParam("userType")String userType){
        String rootPath="E:\\2020第一学期\\graduation\\subjectFile\\";
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
                SubjectFile subjectFile=new SubjectFile(null, multipartFile.getOriginalFilename(), rootPath,subjectId,userId,userType);
                Integer integer = fileInfoService.insertSubFile(subjectFile);
                if (integer==null||integer==0){
                    return ResultMap.setResult(200,null,"文件上传失败");
                }
                //保存到文件夹
                multipartFile.transferTo(new File(storagePath));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultMap.setResult(200,null,"上传文件成功");
    }

    @ApiOperation("用户获取到自己已经上传了的课题附件信息")
    @GetMapping("/user/subject/getFileListInfo")
    public Map<String,Object> getFileListInfo(@RequestParam("subjectId") Integer subjectId,
                                              @RequestParam("userId")Integer userId){
        List<SubjectFile> fileInfos=fileInfoService.selectSubFileList(subjectId, userId);
        return ResultMap.setResult(200,fileInfos,"获取用户上传的文件信息成功");
    }

    @ApiOperation("获取到不同身份发布的课题附件列表信息")
    @GetMapping("/user/subject/getFileByType")
    public Map<String,Object> getFileOther(@RequestParam("subjectId") Integer subjectId,
                                           @RequestParam("userType")String userType){
        if (subjectId==null||userType==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        List<SubjectFile> fileInfos=fileInfoService.getSubFileOther(subjectId, userType);
        return ResultMap.setResult(200,fileInfos,"获取到发布任务者上传的附件列表信息成功");
    }

    @ApiOperation("用户下载课题下的附件")
    @GetMapping("/user/subject/DownLoadFile")
    public Map<String,Object> downLoadFile(@RequestParam("fileId")Integer fileId,
                                           HttpServletResponse response){
        SubjectFile fileInfo = fileInfoService.selectSubFile(fileId);
        OutputStream os = null;
        InputStream is= null;
        String rootPath=fileInfo.getRootPath();
        String fileName=fileInfo.getFileName();
        try {
            os=response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            File f=new File(rootPath+fileName);
            // 这里URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName );


            is=new FileInputStream(f);
            if (is==null){
                System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return ResultMap.setResult(200,fileName,"下载文件失败，请检查文件"+fileName+"是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();

        } catch (IOException e) {
            e.printStackTrace();
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

}
