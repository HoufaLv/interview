package com.iw.crm.service;

import com.iw.crm.entity.Account;

/**
 * Account 业务层
 */
public interface AccountService {


    /**
     * 根据userMobile 查询账户是否存在
     * @param userMobile
     * @return
     */
    Account selectByMobile(String userMobile);

    /**
     * 添加部门s
     * @param deptName
     */
    void insertDept(String deptName);
}
