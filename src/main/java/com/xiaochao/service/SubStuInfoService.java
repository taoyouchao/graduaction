package com.xiaochao.service;

import com.xiaochao.dao.SubStuInfoDao;
import com.xiaochao.vo.VoSubjectStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-21 17:02
 **/
@Service
public class SubStuInfoService {
    @Resource
    private SubStuInfoDao subStuInfoDao;

    public List<VoSubjectStudent> getStuInfoByAdviser(Integer adviserId){
        return subStuInfoDao.getStuInfoByAdviser(adviserId);
    }

    public Integer upAdviser( Integer studentId,Float adviserGrade){
        return subStuInfoDao.upAdviser(studentId, adviserGrade);
    }

    public Integer upReview(Integer studentId,Float reviewGrade){
        return subStuInfoDao.upAdviser(studentId, reviewGrade);
    }

    public Integer upReply(Integer studentId,Float replyGrade){
        return subStuInfoDao.upReply(studentId, replyGrade);
    }





}
