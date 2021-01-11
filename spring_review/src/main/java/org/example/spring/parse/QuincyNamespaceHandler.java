package org.example.spring.parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * FileName: QuincyNamespaceHandler
 *
 * @author: quincy
 * Date: 2021/1/11 下午1:51
 * History:
 */

public class QuincyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("person", new QuincyBeanDefinitionParser());
    }
}
