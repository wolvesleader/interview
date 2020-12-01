package com.quincy.apidesign.idempotence;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Idempotence
 *
 * @author: quincy
 * Date: 2020/12/1 下午7:49
 * History:
 */
public interface Idempotence {

    public boolean check(String idempotenceId);

    void save(String idempotenceId);
}
