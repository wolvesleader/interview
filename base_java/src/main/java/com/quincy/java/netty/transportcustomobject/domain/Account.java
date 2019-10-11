package com.quincy.java.netty.transportcustomobject.domain;

import java.io.Serializable;

/**
 * author:quincy
 * Date:2019-05-26
 */
public class Account implements Serializable {

    private String accountId;
    private String accountPwd;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", accountPwd='" + accountPwd + '\'' +
                '}';
    }
}
