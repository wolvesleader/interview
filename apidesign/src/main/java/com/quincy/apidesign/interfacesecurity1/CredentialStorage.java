package com.quincy.apidesign.interfacesecurity1;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: CredentialStorage
 *
 * @author: quincy
 * Date: 2020/11/29 下午6:10
 * History:
 */

public interface CredentialStorage {


    /**
     * 更加appId查询用户
     * 可以通过实现来扩展从数据库、缓存、文件等存储系统中获取数据
     * @param appId
     * @return
     */
    String getPasswordByAppId(String appId);
}
