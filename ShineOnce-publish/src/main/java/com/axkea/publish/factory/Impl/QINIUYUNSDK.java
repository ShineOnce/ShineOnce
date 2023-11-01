package com.axkea.publish.factory.Impl;

import com.axkea.common.api.Result;
import com.axkea.publish.factory.AbstractSDKFactory;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    private final String accessKey = "4rdOegZZUy1gurS8qMMI7AZN12p0p62vIaaH08QR";
    private final String secretKey = "WgwuUrvEnjBOLPnMe8mYvi3S8IxTzIN1qp22bGqM";
    private final String bucket = "seven-cow-cloud";

    @Override
    public Result uploadFile(File file,String usrId) {
        Response res;
        try {
            Configuration cfg = new Configuration(Region.autoRegion());
            cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(accessKey,secretKey);
            String uploadToken = auth.uploadToken(bucket);
            Date date = new Date(System.currentTimeMillis());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String filename = "video/"+usrId+"-"+format.format(date);
            res = uploadManager.put(file, filename, uploadToken);
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
        return Result.success(res);
    }
}
