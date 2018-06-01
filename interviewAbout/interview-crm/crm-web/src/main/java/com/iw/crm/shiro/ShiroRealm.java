package com.iw.crm.shiro;

import com.iw.crm.entity.Account;
import com.iw.crm.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 判断角色权限和登陆
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /**
     * 判断权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 判断登陆,在这个里面完成验证功能
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取UsernamePasswordToken来完成登陆验证,在UsernamePassword 中包含了前端传过来的账户密码等信息,去数据库中获取行信息进行比对
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();

        if (userMobile != null) {
            Account account = accountService.selectByMobile(userMobile);
            if (account!=null) {

                //最后return new SimpleAuthenticationInfo,传入验证主体和验证条件,将会在内部进行自动判断
                return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
            }else {
                throw new UnknownAccountException("找不到账号");
            }
        } else {
            throw new IncorrectCredentialsException("账号密码错误");
        }
    }
}
