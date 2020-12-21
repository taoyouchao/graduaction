package com.xiaochao.service;

import com.xiaochao.dao.TaskDao;
import com.xiaochao.modal.Task;
import com.xiaochao.vo.VoUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-18 10:10
 **/
@Service
public class TaskService  {

    @Resource
    private TaskDao taskDao;

    public List<Task> queryTaskListByDesignId(Integer designId) {
        return taskDao.queryTaskListByDesignId(designId);
    }


    public List<Task> queryTaskListByType(Integer designId, String taskType,Integer userId,Integer statue) {
        return taskDao.queryTaskListByType(designId, taskType,userId,statue);
    }


    public Integer insertTaskUser(Task task) {
        //插入任务
        Integer result=taskDao.insertByDesignId(task);
        if (result==0){
            return result;
        }
        if (task.getTaskType().equals("teacher")){
            result=taskDao.insertTaskUser(task, 2);
        }
        else if (task.getTaskType().equals("student")){
            result=taskDao.insertTaskUser(task, 3);
        }
        //插入任务后将任务通知给教师或学生
        return result;
    }


    public Integer deleteByDesignId(Integer taskId) {
        return taskDao.deleteByDesignId(taskId);
    }



    public Integer updateByDesignId(Task task, Integer taskId) {
        return taskDao.updateByDesignId(task, taskId);
    }


    public List<VoUser> getTaskUser(Integer taskId){
        return taskDao.findTaskUser(taskId);
    }
    public Integer upTaskStatue(Integer taskId,Integer userId,Integer statue){
        return taskDao.updateTaskStatue(taskId, userId, statue);
    }


}
