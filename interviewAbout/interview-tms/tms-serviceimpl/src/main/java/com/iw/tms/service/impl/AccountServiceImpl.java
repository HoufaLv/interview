package com.iw.tms.service.impl;

import com.iw.tms.entity.*;
import com.iw.tms.exception.ServiceException;
import com.iw.tms.mapper.AccountLoginLogMapper;
import com.iw.tms.mapper.AccountMapper;
import com.iw.tms.mapper.AccountRolesMapper;
import com.iw.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;
    @Autowired
    private AccountRolesMapper accountRolesMapper;

    /**
     * account login
     * 将控制器传回来的密码加密与数据库进行比对
     * 根据账号状态判断登陆结果
     *
     * @param accountMobile   账户手机号
     * @param accountPassword 密码
     * @param remoteAddr      用户ip
     */
    @Override
    public Account login(String accountMobile, String accountPassword, String remoteAddr) {
        //查出来的账号
        Account account;
        AccountLoginLog accountLoginLog = new AccountLoginLog();

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(accountMobile);

        List<Account> accounts = accountMapper.selectByExample(accountExample);

        //根据账号是否存在,账号状态,账号密码进行对比
        if (accounts != null && !accounts.isEmpty()) {
            account = accounts.get(0);
            if (Account.STATE_NORMAL.equals(account.getAccountState())) {
                if (account.getAccountPassword().equals(DigestUtils.md5Hex(accountPassword))) {

                    //登陆成功,添加登陆记录
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(remoteAddr);
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLogMapper.insert(accountLoginLog);
                    logger.info("{} 登陆了综合管理系统", account);
                    return account;
                } else {
                    throw new ServiceException("账号或密码错误");
                }
            } else {
                throw new ServiceException("账号已被锁定");
            }
        } else {
            throw new ServiceException("账号不存在");
        }
    }

    /**
     * 添加账户
     *
     * @param account
     * @param rolesId
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertAccount(Account account, Integer[] rolesId) {
        //账号合法性判断
        /*AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(account.getAccountMobile());

        List<Account> accounts = accountMapper.selectByExample(accountExample);
        if (accounts.size()!=0 || !accounts.isEmpty()){
            throw new ServiceException("账号已经存在");
        }*/

        //添加账户
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account.setAccountState(Account.STATE_NORMAL);
        //设置默认密码
        account.setAccountPassword(DigestUtils.md5Hex("000000"));

        accountMapper.insertSelective(account);
        logger.info("添加账户",account);
        Account currAccount = accountMapper.selectByPrimaryKey(account.getId());

        //处理关联关系
        for (Integer id : rolesId) {
            AccountRolesKey accountRolesKey = new AccountRolesKey();
            accountRolesKey.settAccountId(currAccount.getId());
            accountRolesKey.settRolesId(id);
            accountRolesMapper.insertSelective(accountRolesKey);
            logger.info("添加账户和角色的对应关系");
        }
    }

    /**
     * 根据视图层传过来的查询参数查询所有账号并加载对应的角色列表
     *
     * @param requestParam
     * @return
     */
    @Override
    public List<Account> selectAllAccountWithRolesByQueryParam(HashMap<String, Object> requestParam) {
        return accountMapper.selectAllAccountWithRolesByQueryParam(requestParam);
    }

    /**
     * 根据id查找对应账号信息
     *
     * @param id
     * @return
     */
    @Override
    public Account selectByAccountId(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新账户信息
     * 事务
     * @param account
     * @param rolesIds
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateAccount(Account account, Integer[] rolesIds) {
        //添加更新时间等信息
        account.setUpdateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);

        //删除原有的账号--角色关系
        //根据账号id 查出来账号和角色的对应关系,然后删除
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andTAccountIdEqualTo(account.getId());

        accountRolesMapper.deleteByExample(accountRolesExample);

        //新增账号--角色关系
        //如果传过来了角色id,则更新
        if (rolesIds!=null){
            for (Integer id : rolesIds) {
                AccountRolesKey accountRolesKey = new AccountRolesKey();
                accountRolesKey.settAccountId(account.getId());
                accountRolesKey.settRolesId(id);
                accountRolesMapper.insertSelective(accountRolesKey);
            }
        }

        logger.info("修改账号:{}",account);
    }
}
