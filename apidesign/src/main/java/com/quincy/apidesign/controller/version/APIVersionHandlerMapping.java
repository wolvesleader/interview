package com.quincy.apidesign.controller.version;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: APIVersionHandlerMapping
 *
 * @author: quincy
 * Date: 2020/11/26 下午3:44
 * History:
 */

public class APIVersionHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected boolean isHandler(Class<?> beanType) {
        //不用修改
        return super.isHandler(beanType);
    }


    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        Class<?> declaringClass = method.getDeclaringClass();
        //判断类上有没有加APIVersion注解
        APIVersion annotation = AnnotationUtils.findAnnotation(declaringClass, APIVersion.class);
        APIVersion methodAPI = AnnotationUtils.findAnnotation(method, APIVersion.class);
        if (methodAPI != null) {
            annotation = methodAPI;

            String[] urlPatterns = annotation == null ? new String[0] : annotation.value();
            PatternsRequestCondition apiPattern = new PatternsRequestCondition(urlPatterns);
            PatternsRequestCondition oldPattern = mapping.getPatternsCondition();
            PatternsRequestCondition updatedFinalPattern = apiPattern.combine(oldPattern);
            mapping = new RequestMappingInfo(mapping.getName(), updatedFinalPattern, mapping.getMethodsCondition(), mapping.getParamsCondition(), mapping.getHeadersCondition(), mapping.getConsumesCondition(), mapping.getProducesCondition(), mapping.getCustomCondition());
        }
        super.registerHandlerMethod(handler, method, mapping);
    }
}
