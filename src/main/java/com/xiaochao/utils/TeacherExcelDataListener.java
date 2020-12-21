package com.xiaochao.utils;

import ch.qos.logback.classic.Logger;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.xiaochao.service.TeacherService;
import com.xiaochao.vo.ExcelTeacher;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class TeacherExcelDataListener extends AnalysisEventListener<ExcelTeacher> {
    private static final Logger LOGGER =(Logger) LoggerFactory.getLogger(TeacherExcelDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<ExcelTeacher> teachers = new ArrayList<ExcelTeacher>();

    private TeacherService teacherService;

    public TeacherExcelDataListener(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @Override
    public void invoke(ExcelTeacher data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        teachers.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (teachers.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            teachers.clear();
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
        LOGGER.info("{}条数据，开始存储数据库！", teachers.size());
        for (ExcelTeacher teacher : teachers) {
            LOGGER.info("插入前返回的主键是{}", teacher.getId());
        }
        teacherService.insertExcelTeachers(teachers);
        for (ExcelTeacher teacher : teachers) {
            LOGGER.info("返回的主键是{}", teacher.getId());
        }

    }


}