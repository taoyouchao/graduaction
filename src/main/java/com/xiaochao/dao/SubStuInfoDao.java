package com.xiaochao.dao;

import com.xiaochao.vo.VoSubjectStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubStuInfoDao {
    public List<VoSubjectStudent> getStuInfoByAdviser(Integer adviserId);

    public Integer upAdviser(@Param("studentId") Integer studentId, @Param("adviserGrade")Float adviserGrade);

    public Integer upReview(@Param("studentId")Integer studentId,@Param("reviewGrade")Float reviewGrade);

    public Integer upReply(@Param("studentId")Integer studentId,@Param("replyGrade")Float replyGrade);
}
