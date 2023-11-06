package com.axkea.publish.core.service.Impl;

import com.axkea.publish.core.constant.Constant;
import com.axkea.publish.core.service.VideoService;
import com.axkea.publish.mapper.VideoMapper;
import com.axkea.common.pojo.Video;
import com.axkea.publish.pojo.dto.VideoCardDTO;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 22:04
 */
@Component
public class VideoServiceImpl implements VideoService {

    @Resource
    VideoMapper videoMapper;

    @Override
    public List<Video> getVideoByUserId(String userId) {
        Auth auth = Auth.create(Constant.accessKey,Constant.secretKey);
        Configuration cfg = new Configuration(Region.autoRegion());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String prefix = Constant.VIDEO+"/"+userId;
        BucketManager.FileListIterator iterator = bucketManager.createFileListIterator(Constant.bucketFileName, prefix);
        while (iterator.hasNext()){
            FileInfo[] fileInfos = iterator.next();
            for (FileInfo fileInfo : fileInfos) {
                System.out.println(fileInfo.key);
            }
        }
        return null;
    }

}
