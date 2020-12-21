package com.xiaochao.dao;

import com.xiaochao.modal.Search;
import com.xiaochao.vo.VoAdviser;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao {

    Integer insertGroup(Integer searchId);


    /**
     * 统计小组数量
     *
     * @param designId 毕设 ID
     * @return 小组数量
     */
    Integer countGroup(Integer designId);

    /**
     *
     * 获取指定学生的研究方向
     *
     * @param studendId 学号 ID
     * @return 该学生的研究方向
     */
    Search getSearchBysId(Integer studendId);

    /**
     * 获取指定学生的指导教师
     *
     * @param studentId 学号 ID
     * @return 学生的指定教师
     */
    VoAdviser getTeacherBysId(Integer studentId);

}
