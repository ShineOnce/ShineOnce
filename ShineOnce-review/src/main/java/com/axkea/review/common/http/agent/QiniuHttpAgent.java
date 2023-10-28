package com.axkea.review.common.http.agent;

import com.axkea.review.core.examine.config.QiNiuConfig;
import com.qiniu.http.Client;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/27 17:48
 **/
@Component
public class QiniuHttpAgent implements ProxyAgent<Client> {

    @Override
    public Client agentClient() {
        return new Client();
    }
}
