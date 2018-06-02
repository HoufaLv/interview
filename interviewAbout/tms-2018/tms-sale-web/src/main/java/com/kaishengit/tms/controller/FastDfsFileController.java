package com.kaishengit.tms.controller;

import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.fileStore.FastDfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/file")
public class FastDfsFileController {

    @Autowired
    private FastDfsUtil fastDfsUtil;

    /**
     * Ajax文件上传
     * @date 2018/4/30
     * @param  file
     * @return com.kaishengit.tms.dto.ResponseBean
     */
    @PostMapping("/upload")
    public @ResponseBody ResponseBean uploadFile(MultipartFile file){

        if (!file.isEmpty()){
            try {
                InputStream inputStream = file.getInputStream();
                String fileName = file.getOriginalFilename();
                String extName = fileName.substring(fileName.lastIndexOf(".")+1);

                String fileId = fastDfsUtil.uploadFile(inputStream,extName);
                return ResponseBean.success(fileId);
            } catch (IOException ex){
                ex.printStackTrace();
                return ResponseBean.error("文件上传异常");
            }
        } else {
            return ResponseBean.error("请选择上传的文件");
        }
    }

    @GetMapping("/download")
    public void downloadFile(String fileId, HttpServletResponse response) throws IOException {
        byte[] bytes = fastDfsUtil.downloadFile(fileId);
        if(bytes != null) {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }
    }
}
