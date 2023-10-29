package com.axkea.review.review.core.examine.api;

import com.alibaba.fastjson.JSONObject;
import com.axkea.review.common.http.agent.QiniuHttpAgent;
import com.axkea.review.core.examine.config.QiNiuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author welsir
 * @date 2023/10/26 20:21
 **/
@Component
public class QiNiuApiBuilder extends AbstractApiBuilder{

    @Resource
    private QiNiuConfig qiNiuConfig;

    @Resource
    private QiniuHttpAgent qiniuHttpAgent;

    private String bodyStr;

    private StringMap headers;

    @Override
    public ApiBuilder body(String body) {
        this.bodyStr = body;
        return this;
    }

    @Override
    public QiNiuApiBuilder header(){
        return this;
    }

    @Override
    public JSONObject post(String url) throws QiniuException {
        Client client = qiniuHttpAgent.agentClient();
        this.headers = qiNiuConfig.auth().authorizationV2(url, "POST", bodyStr.getBytes(), "application/json");
        Response resp = client.post(url, bodyStr.getBytes(), this.headers, Client.JsonMime);
        return JSONObject.parseObject(resp.bodyString());
    }

    @Override
    public JSONObject get(String url) {
        return null;
    }
}
