package com.iw.tms.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

public class ShiroRealm implements Realm {

    /**
     * 返回名字
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * 支持
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return false;
    }

    /**
     * 认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
