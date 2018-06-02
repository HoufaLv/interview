package com.kaishengit.tms.service.impl;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;
    @Autowired
    private AccountRolesMapper accountRolesMapper;


    /**
     * 系统登录
     *
     * @param accountMobile 手机号, password 密码, requestIp 登录ip
     * @param password
     * @param requestIp
     * @return 登录成功返回com.kaishengit.tms.entity.Account对象，登录失败返回null
     * @throws ServiceException 如果登录失败，则通过异常抛出具体的错误原因
     * @date 2018/4/12
     */
    /*@Override
    public Account login(String accountMobile, String password, String requestIp) throws ServiceException {
        //根据手机号码查找对应的账号
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(accountMobile);

        List<Account> accountList = accountMapper.selectByExample(accountExample);
        Account account = null;

        if ( !accountList.isEmpty()){
            account = accountList.get(0);
            //匹配密码
            if (account.getAccountPassword().equals(DigestUtils.md5Hex(password))){
                //判断状态
                if (Account.STATE_NORMAL.equals(account.getAccountState())){
                    //添加登录日志
                    AccountLoginLog loginLog = new AccountLoginLog();
                    loginLog.setAccountId(account.getId());
                    loginLog.setLoginIp(requestIp);
                    loginLog.setLoginTime(new Date());

                    accountLoginLogMapper.insertSelective(loginLog);
                    logger.info("{}登录系统",account);
                    return account;
                } else if(Account.STATE_DISABLE.equals(account.getAccountState())){
                    throw new ServiceException("账号被禁用");
                } else {
                    throw new ServiceException("账号被锁定");
                }
            } else {
                throw new ServiceException("账号或密码错误");
            }
        } else {
            throw new ServiceException("账号或密码错误");
        }
    }*/

    /**
     * 根据url传来的参数查询所有账号并加载对应的角色列表
     *
     * @param requestParam
     * @return java.util.List<com.kaishengit.tms.entity.Account>
     * @date 2018/4/16
     */
    @Override
    public List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam) {
        return accountMapper.findAllWithRolesByQueryParam(requestParam);
    }

    /**
     * 新增账号
     *
     * @param account  账号对象, rolesIds 账号拥有的角色ID数字
     * @param rolesIds
     * @return void
     * @date 2018/4/16
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveAccount(Account account, Integer[] rolesIds) {
        account.setCreateTime(new Date());
        //账号默认密码为手机号码后六位
        String password;
        if (account.getAccountMobile().length()<=6){
            password = account.getAccountMobile();
        } else {
            password = account.getAccountMobile().substring(6);
        }

        //对密码进行MD5加密
        password = DigestUtils.md5Hex(password);
        account.setAccountPassword(password);

        //账号默认状态
        account.setAccountState(Account.STATE_NORMAL);

        accountMapper.insertSelective(account);

        //添加帐号和角色对应关系表
        if (rolesIds!=null){
            for (Integer roleId:rolesIds){
                AccountRolesKey accountRolesKey = new AccountRolesKey();
                accountRolesKey.setAccountId(account.getId());
                accountRolesKey.setRolesId(roleId);

                accountRolesMapper.insert(accountRolesKey);
            }
        }

        logger.info("保存账号 {}",account);

    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return com.kaishengit.tms.entity.Account
     * @date 2018/4/16
     */
    @Override
    public Account findById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改账号
     *
     * @param account  账号对象, rolesIds 账号拥有的角色ID数组
     * @param rolesIds
     * @return void
     * @date 2018/4/16
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateAccount(Account account, Integer[] rolesIds) {
        //添加账号的修改时间
        account.setUpdateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);

        //删除原有的账号-角色关系表
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andAccountIdEqualTo(account.getId());
        accountRolesMapper.deleteByExample(accountRolesExample);

        //新增账号-角色关系
        if (rolesIds!=null){
            for (Integer rolesId:rolesIds){
                AccountRolesKey accountRolesKey = new AccountRolesKey();
                accountRolesKey.setRolesId(rolesId);
                accountRolesKey.setAccountId(account.getId());
                accountRolesMapper.insertSelective(accountRolesKey);
            }
        }

        logger.info("修改账号{}",account);
    }

    /**
     * 根据用户id删除用户
     *
     * @param id
     * @return void
     * @date 2018/4/17
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delAccountById(Integer id) {
        //1.根据Id查找用户
        Account account = accountMapper.selectByPrimaryKey(id);

        //2.删除用户角色关联关系表
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andAccountIdEqualTo(id);
        accountRolesMapper.deleteByExample(accountRolesExample);

        //3.删除用户登录日志关联关系表
        AccountLoginLogExample accountLoginLogExample = new AccountLoginLogExample();
        accountLoginLogExample.createCriteria().andAccountIdEqualTo(id);
        accountLoginLogMapper.deleteByExample(accountLoginLogExample);

        //4.删除用户表
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andIdEqualTo(id);
        accountMapper.deleteByExample(accountExample);

        logger.info("删除用户{}",account);

    }

    /**
     * 保存账号登录记录
     *
     * @param accountLoginLog
     * @return void
     * @date 2018/4/17
     */
    @Override
    public void saveAccountLoginLog(AccountLoginLog accountLoginLog) {
        accountLoginLogMapper.insertSelective(accountLoginLog);
    }

    /**
     * 根据用户手机号查找账户
     *
     * @param userMobile
     * @return com.kaishengit.tms.entity.Account
     * @date 2018/4/17
     */
    @Override
    public Account findAccountByMobile(String userMobile) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(userMobile);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if (!accountList.isEmpty()){
            return accountList.get(0);
        }
        return null;
    }


}
