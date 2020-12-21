package com.xiaochao.service;

import com.xiaochao.dao.RuleDao;
import com.xiaochao.modal.Rule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-21 23:05
 **/
@Service
public class RuleService {

    @Resource
    private RuleDao ruleDao;
    /**
     * @Description: 上传规则
     * @Param: [rule]
     * @return: java.lang.Integer
     * @Date: 2020/12/21
     */
    public Integer upRule(Rule rule){
        return ruleDao.upRule(rule);
    }

    /**
     * @Description: 根据毕业设计id获取所有规则
     * @Param: [designId]
     * @return: java.util.List<com.xiaochao.modal.Rule>
     * @Date: 2020/12/21
     */
    public List<Rule> getDesignAllRule(Integer designId){
        return ruleDao.getDesignAllRule(designId);
    }

    /**
     * @Description: 修改规则
     * @Param: [rule]
     * @return: java.lang.Integer
     * @Date: 2020/12/21
     */
    public Integer updateRule(Rule rule){
        return ruleDao.updateRule(rule);
    }

    /**
     * @Description: 删除规则
     * @Param: [ruleId]
     * @return: java.lang.Integer
     * @Date: 2020/12/21
     */
    public Integer deleteRule(Integer ruleId){
        return ruleDao.deleteRule(ruleId);
    }


}
