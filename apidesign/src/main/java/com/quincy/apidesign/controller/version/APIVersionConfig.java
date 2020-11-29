package com.quincy.apidesign.controller.version;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: APIVersionConfig
 *
 * @author: quincy
 * Date: 2020/11/26 下午3:58
 * History:
 */
@Configuration
public class APIVersionConfig implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new APIVersionHandlerMapping();
    }
}
