package com.xiaochao.dao;

import com.xiaochao.modal.FileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoDao {

    public Integer insertFile(FileInfo fileInfo);

    public FileInfo selectFile(Integer fileId);

    public List<FileInfo> selectFileList(@Param("taskId") Integer taskId,@Param("userId") Integer userId);

    public List<FileInfo> getFileOther(@Param("taskId") Integer taskId,@Param("userType")String userType);

}
