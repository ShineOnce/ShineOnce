package com.axkea.publish.core.config;

import com.axkea.publish.core.constant.Constant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/2 10:24
 */
@Component
@ConfigurationProperties(prefix = "oss.qiniuyun")
@Data
@RefreshScope
public class QiNiuYunConfig {

    private String accessKey;
    private String secretKey;
    private String bucketFileName;
    private String domainFile;

    @Bean
    public void init(){
        Constant.accessKey = this.accessKey;
        Constant.secretKey = this.secretKey;
        Constant.bucketFileName = this.bucketFileName;
        Constant.domainFile = this.domainFile;
    }

}
