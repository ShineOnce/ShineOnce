package com.axkea.review.core.examine;

import com.alibaba.fastjson.JSONObject;
import com.axkea.review.common.ExamineEvent;
import com.axkea.review.common.RedisKey;
import com.axkea.review.common.http.msg.QiNiuMsgBuilder;
import com.axkea.review.config.RedisKeyConfig;
import com.axkea.review.core.examine.api.QiNiuApiBuilder;
import com.qiniu.common.QiniuException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/26 23:20
 **/
@Component
public class QiNiuExamineHandler extends AbstractExamineHandler{

    @Resource
    QiNiuApiBuilder apiBuilder;

    @Override
    public boolean examineContent(ExamineEvent context) {
        QiNiuMsgBuilder builder = new QiNiuMsgBuilder()
                .data("text", context.getContent())
                .params("scenes", List.of("antispam"));
        try {
            return getRes("http://ai.qiniuapi.com/v3/text/censor",builder.done());
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean examineAudio(ExamineEvent context) {
        return false;
    }

    @Override
    public boolean examineImg(ExamineEvent context) {
        QiNiuMsgBuilder builder = new QiNiuMsgBuilder()
                .data("uri", context.getUrl())
                .params("scenes", List.of( "pulp", "terror", "politician"));
        try {
            return getRes("http://ai.qiniuapi.com/v3/image/censor",builder.done());
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean examineVideo(ExamineEvent context) {
        QiNiuMsgBuilder builder = new QiNiuMsgBuilder()
                .data("uri", context.getUrl())
                .data("id",context.getEventId())
                .params("scenes", List.of("pulp", "terror", "politician"))
                .params("cut_param", Map.of("interval_msecs",5000));
        try {
            RedisKey redisKey = RedisKeyConfig.VIDEO_EXAMINE_CALLBACK;
            String key = redisKey.getKey(context.getEventId());
            JSONObject res = apiBuilder.body(builder)
                    .header()
                    .post("http://ai.qiniuapi.com/v3/text/censor");
            String job = res.getString("job");
            if(StringUtils.hasText(job)){
                stringRedisTemplate.opsForValue().setIfAbsent(key,JSONObject.toJSONString(context));
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public boolean getRes(String apiUrl,String body) throws QiniuException {
        JSONObject res = apiBuilder.body(body)
                .header()
                .post(apiUrl);
        JSONObject result = res.getJSONObject("result");
        String suggestion = result.getString("suggestion");
        return checkRes(suggestion);
    }
    public static boolean checkRes(String res){
         return res.equals("pass")||res.equals("review");
    }
}
