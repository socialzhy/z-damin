package com.z.admin.generation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhy
 * @description mysql配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class MysqlProperty {
    private String url;
    private String username;
    private String password;
}
