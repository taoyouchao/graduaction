package com.xiaochao.dao;

import com.alibaba.excel.EasyExcel;
import com.xiaochao.modal.Search;
import com.xiaochao.modal.Task;
import com.xiaochao.utils.TeacherExcelDataListener;
import com.xiaochao.service.TeacherService;
import com.xiaochao.vo.ExcelTeacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-11-18 15:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserTest {

    @Resource
    TeacherService teacherService;
    @Resource
    TeacherDao teacherDao;
    @Resource
    TaskDao taskDao;
    @Resource
    SearchDao searchDao;

    @Test
    public void getAllUser(){
        String password = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
    }

    public List<ExcelTeacher> data(){
        return teacherService.getAllTeacherByDesignId(1) ;
    }

    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link ExcelTeacher}
     * <p>2. 直接写即可
     */
    @Test
    public void simpleWrite() {

        // 写法1
        String fileName = "E:\\2020第一学期\\graduation\\files\\ "+ "teacher_info.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ExcelTeacher.class).sheet("teacher模板").doWrite(data());

    }

    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1
        String fileName = "E:\\2020第一学期\\graduation\\files\\ "+ "teacher_info.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelTeacher.class, new TeacherExcelDataListener(teacherService)).sheet().doRead();

    }

    @Test
    public void TaskTest(){
        List<Task> tasks = taskDao.queryTaskListByDesignId(1);
        System.out.println(tasks);
        Task task=tasks.get(0);
        task.setTaskId(null);
        taskDao.insertByDesignId(task);
        taskDao.insertTaskUser(task,3);
        task.setTaskTitle("教师发布课题");
        taskDao.updateByDesignId(tasks.get(0),tasks.get(0).getTaskId());
//        taskDao.deleteByDesignId(tasks.get(2).getTaskId());
    }

    @Test
    public void searchTest(){
        List<Search> searches=searchDao.getAllSearch(1);
        Search search=searches.get(0);
        search.setSearchId(null);
        searchDao.insertSearch(search,1);
        searchDao.getAllSearch(1);

    }

}
