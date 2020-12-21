package com.xiaochao.utils;

import ch.qos.logback.classic.Logger;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.xiaochao.service.StudentService;
import com.xiaochao.service.TeacherService;
import com.xiaochao.vo.ExcelStudent;
import com.xiaochao.vo.ExcelTeacher;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-14 16:44
 **/
public class StudentExcelDataListener extends AnalysisEventListener<ExcelStudent> {

    private static final Logger LOGGER =(Logger) LoggerFactory.getLogger(StudentExcelDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<ExcelStudent> students = new ArrayList<>();

    private StudentService studentService;

    public StudentExcelDataListener(StudentService studentService) {
        this.studentService = studentService;
    }




    @Override
    public void invoke(ExcelStudent data, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        students.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (students.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            students.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", students.size());
        for (ExcelStudent student : students) {
            LOGGER.info("插入前返回的主键是{}", student.getId());
        }
        studentService.insertExcelStudents(students);
        for (ExcelStudent student : students) {
            LOGGER.info("返回的主键是{}", student.getId());
        }

    }


}
