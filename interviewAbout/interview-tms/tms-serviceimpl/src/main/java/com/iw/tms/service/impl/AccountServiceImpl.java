package com.iw.tms.service.impl;

import com.iw.tms.entity.Account;
import com.iw.tms.entity.AccountExample;
import com.iw.tms.entity.AccountLoginLog;
import com.iw.tms.exception.ServiceException;
import com.iw.tms.mapper.AccountLoginLogMapper;
import com.iw.tms.mapper.AccountMapper;
import com.iw.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    /**
     * account login
     * 将控制器传回来的密码加密与数据库进行比对
     * 根据账号状态判断登陆结果
     * @param accountMobile     账户手机号
     * @param accountPassword   密码
     * @param remoteAddr        用户ip
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
        if (accounts!=null && !accounts.isEmpty()){
            account = accounts.get(0);
            if (Account.STATE_NORMAL.equals(account.getAccountState())){
                if (account.getAccountPassword().equals(DigestUtils.md5Hex(accountPassword))){

                    //登陆成功,添加登陆记录
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(remoteAddr);
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLogMapper.insert(accountLoginLog);
                    logger.info("{} 登陆了综合管理系统",account);
                    return account;
                }else {
                    throw new ServiceException("账号或密码错误");
                }
            }else {
                throw new ServiceException("账号已被锁定");
            }
        }else{
            throw new ServiceException("账号不存在");
        }
    }
}
