package com.quincy.apidesign.interfacesecurity1;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: MysqlCredentialStorage
 *
 * @author: quincy
 * Date: 2020/11/29 下午6:21
 * History:
 */

public class MysqlCredentialStorage implements CredentialStorage {
    @Override
    public String getPasswordByAppId(String appId) {
        return "DAJIAHAO";
    }
}
