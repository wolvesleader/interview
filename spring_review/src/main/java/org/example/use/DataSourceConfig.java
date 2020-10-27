package org.example.use;

import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: DataSourceConfig
 *
 * @author: quincy
 * Date: 2020/10/27 上午9:35
 * History:
 */
@DataSource("dataSourceConfig")
@Component
public class DataSourceConfig {
    /**
     * 如果是springboot在yml文件中配置了username属性
     * 可以在这里通过@Value("${classDriver}")注解获取到值
     *
     */

    private String username = "miao";

    private String password = "qing";

    private String diverClass = "jdbc://mysql:localhost:3308";

    @Key(method = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Key(method = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Key(method = "diverClass")
    public String getDiverClass() {
        return diverClass;
    }

    public void setDiverClass(String diverClass) {
        this.diverClass = diverClass;
    }
}
