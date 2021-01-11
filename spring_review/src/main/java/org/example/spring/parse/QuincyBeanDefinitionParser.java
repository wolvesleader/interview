package org.example.spring.parse;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * FileName: QuincyBeanDefinitionParser
 *
 * @author: quincy
 * Date: 2021/1/11 下午1:52
 * History:
 */

public class QuincyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Person.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String age = element.getAttribute("age");
        if (StringUtils.hasText(id)){
            builder.addPropertyValue("id",id);
        }
        if (StringUtils.hasText(name)){
            builder.addPropertyValue("name",name);
        }
        if (StringUtils.hasText(age)){
            builder.addPropertyValue("age",Integer.valueOf(age));
        }
    }
}
