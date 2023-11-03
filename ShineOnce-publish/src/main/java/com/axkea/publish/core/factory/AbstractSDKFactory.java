package com.axkea.publish.core.factory;

import com.axkea.common.api.Result;
import com.axkea.publish.pojo.vo.VideoUploadVO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 14:00
 */
public interface AbstractSDKFactory {

    Result uploadFile(VideoUploadVO videoUploadVO);

}
