package com.iw.tms.service;

import com.iw.tms.entity.Account;

public interface AccountService {

    /**
     * account login
     * @param accountMobile
     * @param accountPassword
     * @param remoteAddr
     */
    Account login(String accountMobile, String accountPassword, String remoteAddr);
}
