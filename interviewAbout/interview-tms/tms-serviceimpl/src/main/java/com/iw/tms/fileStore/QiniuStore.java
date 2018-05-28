package com.iw.tms.fileStore;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 7牛文件存储类
 */
@Component
public class QiniuStore {

    //@Value("${qiniu.ak}")
    private String accessKey="534XFeJPh2FSJ2rLWQx8wyACY9UjEF3LbdKto5cC";
    //@Value("${qiniu.sk}")
    private String secretKey = "SO1FMXz6yKIvyRiEeBhnMHnm-7g4wWmhp-54ySOi";
    //@Value("${qiniu.bucket}")
    private String bucket = "houfalv-tms";

    /**
     * 返回上传密码token
     * @return
     */
    public String getUploadToken(){
        Auth auth = Auth.create(accessKey,secretKey);
        return auth.uploadToken(bucket);
    }
}
