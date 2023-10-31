package com.axkea.review.review.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author welsir
 * @date 2023/10/26 22:01
 **/

@Data
@Configuration
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    @Value("${review.logger.handler}")
    private String loggerHandler;
    @Value("${review.examine.handler}")
    private String examineHandler;

}
