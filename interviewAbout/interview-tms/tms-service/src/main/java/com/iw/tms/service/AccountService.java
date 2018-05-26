package com.iw.tms.service;

import com.iw.tms.entity.Account;

import java.util.HashMap;
import java.util.List;

public interface AccountService {

    /**
     * 账户登陆
     * @param accountMobile
     * @param accountPassword
     * @param remoteAddr
     */
    Account login(String accountMobile, String accountPassword, String remoteAddr);

    /**
     * 添加账户
     * @param account
     * @param rolesId
     */
    void insertAccount(Account account, Integer[] rolesId);

    /**
     * 根据视图层传过来的查询参数查询所有账号并加载对应的角色列表
     * @param requestParam
     * @return
     */
    List<Account> selectAllAccountWithRolesByQueryParam(HashMap<String, Object> requestParam);

    /**
     * 根据id查找对应账号信息
     * @param id
     * @return
     */
    Account selectByAccountId(Integer id);
}
