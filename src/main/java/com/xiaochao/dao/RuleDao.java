package com.xiaochao.dao;

import com.xiaochao.modal.Rule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleDao {

    /**
    * @Description: 上传规则
    * @Param: [rule]
    * @return: java.lang.Integer
    * @Date: 2020/12/21
    */
    public Integer upRule(Rule rule);

    /**
    * @Description: 根据毕业设计id获取所有规则
    * @Param: [designId]
    * @return: java.util.List<com.xiaochao.modal.Rule>
    * @Date: 2020/12/21
    */
    public List<Rule> getDesignAllRule(Integer designId);

    /**
    * @Description: 修改规则
    * @Param: [rule]
    * @return: java.lang.Integer
    * @Date: 2020/12/21
    */
    public Integer updateRule(Rule rule);

    /**
    * @Description: 删除规则
    * @Param: [ruleId]
    * @return: java.lang.Integer
    * @Date: 2020/12/21
    */
    public Integer deleteRule(Integer ruleId);

}
