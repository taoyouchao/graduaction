package com.xiaochao.service;

import com.xiaochao.dao.FileInfoDao;
import com.xiaochao.modal.FileInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description: 任务的附件信息上传
 * @author: 小超
 * @create: 2020-12-20 00:48
 **/
@Service
public class FileInfoService {

    @Resource
    private FileInfoDao fileInfoDao;

    public Integer upFile(FileInfo fileInfo){
        return fileInfoDao.insertFile(fileInfo);
    }

    public FileInfo getFileInfo(Integer fileId){
        return fileInfoDao.selectFile(fileId);
    }

    public List<FileInfo> getFileInfoList(Integer taskId,Integer userId){
        return fileInfoDao.selectFileList(taskId, userId);
    }
    public List<FileInfo> getFileOther(Integer taskId,String userType){
        return fileInfoDao.getFileOther(taskId, userType);
    }




}
