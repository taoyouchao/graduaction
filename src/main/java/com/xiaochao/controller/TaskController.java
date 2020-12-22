package com.xiaochao.controller;

import com.xiaochao.modal.FileInfo;
import com.xiaochao.modal.Task;
import com.xiaochao.service.FileInfoService;
import com.xiaochao.service.TaskService;
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
 * @description: 任务Controller
 * @author: 小超
 * @create: 2020-12-18 14:36
 **/
@RestController
public class TaskController {

    @Resource
    private TaskService taskService;
    @Resource
    private FileInfoService fileInfoService;

    @ApiOperation("管理员发布任务")
    @PostMapping("/task/releaseTask")
    public Map<String,Object> releaseTask(@RequestBody Task task){
        if (task==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer integer = taskService.insertTaskUser(task);
        if (integer==null){
            return ResultMap.setResult(200,integer,"发布任务失败");
        }
        return ResultMap.setResult(200,integer,"发布任务成功");
    }

    @ApiOperation("管理员查询任务")
    @GetMapping("/task/adminQuery")
    public Map<String,Object> queryTasks(@RequestParam("designId") Integer designId){
        if (designId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,taskService.queryTaskListByDesignId(designId),"查询成功");
    }

    @ApiOperation("教师或学生查询以不同状态任务")
    @GetMapping("/task/roleQuery")
    public Map<String,Object> queryTasksByRole(@RequestParam("designId") Integer designId,
                                               @RequestParam("roleType") String roleType,
                                               @RequestParam("uerId") Integer userId,
                                               @RequestParam("statue") Integer statue){
        if (designId==null|| roleType==null||userId==null||statue==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,taskService.queryTaskListByType(designId, roleType,userId,statue),"查询成功");
    }

    @ApiOperation("查询任务下面的成员状态")
    @GetMapping("/task/userQuery")
    public Map<String,Object> findAllTaskUser(@RequestParam("taskId") Integer taskId){
        if (taskId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,taskService.getTaskUser(taskId),"获取任务列表成员成功");
    }

    @ApiOperation("删除任务")
    @DeleteMapping("/task/deleteTask")
    public Map<String,Object> deleteTask(@RequestParam("taskId") Integer taskId){
        if (taskId==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        Integer result = taskService.deleteByDesignId(taskId);
        if (result==0){
            return ResultMap.setResult(200,null,"删除失败");
        }
        return ResultMap.setResult(200,result,"删除成功");
    }


    @ApiOperation("用户上传任务附件")
    @PostMapping("/user/upTaskFile")
    public Map<String,Object> upFile(@RequestParam("file")MultipartFile multipartFile,
                                     @RequestParam("taskId")Integer taskId,
                                     @RequestParam("userId")Integer userId,
                                     @RequestParam("userType")String userType){
        String rootPath="E:\\2020第一学期\\graduation\\taskFile\\";
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
            FileInfo fileInfo = new FileInfo(null, multipartFile.getOriginalFilename(), rootPath, taskId, userId,userType);

            Integer integer = fileInfoService.upFile(fileInfo);
            if (integer==null||integer==0){
                return ResultMap.setResult(200,null,"文件上传失败");
            }
            //保存到文件夹
                multipartFile.transferTo(new File(storagePath));
            //修改任务状态为已经提交，已经完成
                taskService.upTaskStatue(taskId,userId,2);

        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultMap.setResult(200,null,"上传文件成功");
    }

    @ApiOperation("修改任务状态")
    @PutMapping("/user/changeStatue")
    public Map<String,Object> updateTaskStatue(@RequestParam("taskId")Integer tasId,
                                               @RequestParam("userId") Integer userId,
                                               @RequestParam("statue")Integer statue){
        if (tasId==null||userId==null||statue==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        return ResultMap.setResult(200,taskService.upTaskStatue(tasId, userId, statue),"修改状态成功");
    }

    @ApiOperation("用户获取到自己已经上传了的附件信息")
    @GetMapping("/user/getFileListInfo")
    public Map<String,Object> getFileListInfo(@RequestParam("taskId") Integer taskId,
                                              @RequestParam("userId")Integer userId){
        List<FileInfo> fileInfos=fileInfoService.getFileInfoList(taskId, userId);
        return ResultMap.setResult(200,fileInfos,"获取用户上传的文件信息成功");
    }

    @ApiOperation("获取到发布任务者上传的附件列表信息")
    @GetMapping("/user/getFileByType")
    public Map<String,Object> getFileOther(@RequestParam("taskId")Integer taskId,
                                           @RequestParam("userType")String userType){
        if (taskId==null||userType==null){
            return ResultMap.setResult(200,null,"参数为空");
        }
        List<FileInfo> fileInfos=fileInfoService.getFileOther(taskId, userType);
        return ResultMap.setResult(200,fileInfos,"获取到发布任务者上传的附件列表信息成功");
    }

    @ApiOperation("用户下载任务下的附件")
    @GetMapping("/user/DownLoadTaskFile")
    public Map<String,Object> downLoadFile(@RequestParam("fileId")Integer fileId,
                                           HttpServletResponse response){
        FileInfo fileInfo = fileInfoService.getFileInfo(fileId);
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
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
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
