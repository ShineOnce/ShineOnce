package com.axkea.publish.core.factory.Impl;

import com.axkea.common.api.Result;
import com.axkea.publish.core.constant.Constant;
import com.axkea.publish.core.factory.AbstractSDKFactory;
import com.axkea.publish.pojo.vo.VideoUploadVO;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 14:40
 */
@Component
public class QINIUYUNSDK implements AbstractSDKFactory {
    @Override
    public Result uploadFile(VideoUploadVO videoUploadVO) {
        Response res;
        System.out.println(videoUploadVO.getFile().getResource().getFilename());
        try {
            File file = new File("D://1.mp4");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileCopyUtils.copy(videoUploadVO.getFile().getBytes(), file);
            Configuration cfg = new Configuration(Region.autoRegion());
            cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(Constant.accessKey,Constant.secretKey);
            String uploadToken = auth.uploadToken(Constant.bucketFileName);
            Date date = new Date(System.currentTimeMillis());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            String filename = Constant.VIDEO+videoUploadVO.getUserId()+"/"+format.format(date);
            res = uploadManager.put(file, filename, uploadToken);
            DefaultPutRet defaultPutRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            System.out.println(defaultPutRet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success(res);
    }
}
