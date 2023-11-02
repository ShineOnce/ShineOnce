package com.axkea.publish.factory;

import com.axkea.common.api.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 14:00
 */
public interface AbstractSDKFactory {

    Result uploadFile(File file, String usrId);

}
