package com.axkea.review.common.http.agent;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/10/27 03:50
 **/
@Component
public class OkHttpAgent implements ProxyAgent<OkHttpClient>{

    @Override
    public OkHttpClient agentClient() {
        OkHttpClient.Builder builder;
        builder =  new OkHttpClient.Builder();
        return builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
    }
}
