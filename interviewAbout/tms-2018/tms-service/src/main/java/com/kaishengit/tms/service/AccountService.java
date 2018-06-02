package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 *系统账号业务类接口
 * @author drm
 * @date 2018/4/12
 */
public interface AccountService {

    /**  
     *系统登录
     * @date 2018/4/12
     * @param  accountMobile 手机号, password 密码, requestIp 登录ip
     * @return 登录成功返回com.kaishengit.tms.entity.Account对象，登录失败返回null
     * @throws ServiceException 如果登录失败，则通过异常抛出具体的错误原因
     */ 
    //Account login(String accountMobile,String password,String requestIp)throws ServiceException;

    /**
     * 根据url传来的参数查询所有账号并加载对应的角色列表
     * @date 2018/4/16
     * @param [requestParam]
     * @return java.util.List<com.kaishengit.tms.entity.Account>
     */
    List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam);

    /**
     * 新增账号
     * @date 2018/4/16
     * @param account 账号对象, rolesIds 账号拥有的角色ID数字
     * @return void
     */
    void saveAccount(Account account, Integer[] rolesIds);

    /**
     * 根据id查找用户
     * @date 2018/4/16
     * @param [id]
     * @return com.kaishengit.tms.entity.Account
     */
    Account findById(Integer id);

    /**
     * 修改账号
     * @date 2018/4/16
     * @param account 账号对象, rolesIds 账号拥有的角色ID数组
     * @return void
     */
    void updateAccount(Account account, Integer[] rolesIds);

    /**
     * 根据用户id删除用户
     * @date 2018/4/17
     * @param [id]
     * @return void
     */
    void delAccountById(Integer id);

    /**
     * 保存账号登录记录
     * @date 2018/4/17
     * @param accountLoginLog
     * @return void
     */
    void saveAccountLoginLog(AccountLoginLog accountLoginLog);

    /**
     * 根据手机号查找账户
     * @date 2018/4/17
     * @param userMobile
     * @return com.kaishengit.tms.entity.Account
     */
    Account findAccountByMobile(String userMobile);
}
