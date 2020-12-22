package com.xiaochao.dao;

import com.xiaochao.modal.Task;
import com.xiaochao.vo.VoUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao {

    /**
    * @Description: 管理员查询所有任务
    * @Param: [dsignId]     毕业设计id
    * @return: java.util.List<com.xiaochao.modal.Task>
    * @Date: 2020/12/12
    */
    public List<Task> queryTaskListByDesignId(Integer dsignId);

    /**
    * @Description: 根据任务类型查询任务列表
    * @Param: [designId 毕业设计id, taskType 任务类型]
    * @return: java.util.List<com.xiaochao.modal.Task>
    * @Date: 2020/12/12
    */
    public List<Task> queryTaskListByType(@Param("designId") Integer designId,
                                          @Param("taskType") String taskType,
                                          @Param("userId") Integer userId,
                                          @Param("statue")Integer statue);

    /**
    * @Description: 插入任务
    * @Param: [task  任务, designId 毕业设计id]
    * @return: void
    * @Date: 2020/12/12
    */
    public Integer insertByDesignId( Task task);

    /**
    * @Description: 发布任务后通知所有学生或者老师
    * @Param: [task, roleId]
    * @return: void
    * @Date: 2020/12/13
    */
    public Integer insertTaskUser(@Param("task") Task task,@Param("roleId") Integer roleId);

    /**
    * @Description: 删除任务
    * @Param: [taskId]
    * @return: void
    * @Date: 2020/12/12
    */
    public Integer deleteByDesignId(@Param("taskId") Integer taskId);

    /**
    * @Description: 更新任务
    * @Param: [task 任务, taskId  任务id]
    * @return: void
    * @Date: 2020/12/12
    */
    public Integer updateByDesignId(@Param("task") Task task, @Param("taskId") Integer taskId);

    /**
    * @Description: 根据任务id返回用户列表状态
    * @Param: [taskId]
    * @return: com.xiaochao.vo.VoUser
    * @Date: 2020/12/19
    */
    public List<VoUser> findTaskUser(Integer taskId);

    /**
    * @Description: 修改任务状态
    * @Param: [taskId, userId, statue]
    * @return: java.lang.Integer
    * @Date: 2020/12/20
    */
    public Integer updateTaskStatue(@Param("taskId") Integer taskId,
                                    @Param("userId") Integer userId,
                                    @Param("statue") Integer statue);

}
