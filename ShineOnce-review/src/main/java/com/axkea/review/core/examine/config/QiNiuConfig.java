package com.axkea.review.core.examine.config;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Genius
 * @date 2023/10/26 20:18
 **/
@Configuration
public class QiNiuConfig {

    @Value("${review.qiniu.ak}")
    private String accessKey;

    @Value("${review.qiniu.sk}")
    private String secretKey;

    public Auth auth(){
        return Auth.create(accessKey, secretKey);
    }
}
