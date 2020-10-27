package org.example.beanlife;

import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Bean
 *
 * @author: quincy
 * Date: 2020/10/26 下午9:17
 * History:
 */

@Component
public class Bean {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
