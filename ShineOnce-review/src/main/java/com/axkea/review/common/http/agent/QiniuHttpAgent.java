package com.axkea.review.common.http.agent;

import com.qiniu.http.Client;
import org.springframework.stereotype.Component;

/**
 * @author welsir
 * @date 2023/10/27 17:48
 **/
@Component
public class QiniuHttpAgent implements ProxyAgent<Client> {

    @Override
    public Client agentClient() {
        return new Client();
    }
}
