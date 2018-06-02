package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.*;

import com.kaishengit.tms.service.TicketStoreService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    //自动注入用户工具类AccountService  角色权限工具类RolePermissionService
    @Autowired
    private TicketStoreService ticketStoreService;

    /**
     * 判断角色与权限
     * @date 2018/4/17
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 判断登录
     * @date 2018/4/17
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();
        if (userMobile!=null){
            StoreAccount storeAccount = ticketStoreService.findStoreAccountByName(userMobile);
            if (storeAccount == null){
                throw new UnknownAccountException("找不到该账号:"+userMobile);
            } else {
                if (StoreAccount.ACCOUNT_STATE_NORMAL.equals(storeAccount.getStoreState())){

                    logger.info("{}登录成功{}",storeAccount,usernamePasswordToken.getHost());
                    //查找售票点对象
                    TicketStore ticketStore = ticketStoreService.findTicketStoreById(storeAccount.getId());
                    //保存登录日志
                    StoreLoginLog storeLoginLog = new StoreLoginLog();
                    storeLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    storeLoginLog.setLoginTime(new Date());
                    storeLoginLog.setStoreAccountId(storeAccount.getId());

                    ticketStoreService.saveStoreAccountLoginLog(storeLoginLog);

                    //将ticketStore放入shiro

                    return new SimpleAuthenticationInfo(ticketStore,storeAccount.getStorePassword(),getName());

                } else {
                    throw new LockedAccountException("账号被禁用或锁定:"+storeAccount.getStoreState());
                }
            }
        }
        return null;
    }
}
