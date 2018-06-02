package com.iw.web.dto;

import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Component
public class FastDfsUtil {

//    @Value("${fastDFS.server}")
//    public static String FASTDFS;

    /**
     * 上传文件,不带附加信息
     * @param inputStream
     * @param extName
     * @return
     */
    public String uploadFile(InputStream inputStream, String extName) {
        return uploadFile(inputStream, extName, null);
    }

    /**
     * 上传文件,携带附加信息
     * @param inputStream
     * @param extName
     * @param param
     * @return
     */
    public String uploadFile(InputStream inputStream, String extName, Map<String, String> param) {
        try {
            StorageClient storageClient = getStorageClient();

            NameValuePair[] nameValuePairs = null;
            if (param != null) {
                nameValuePairs = new NameValuePair[param.size()];
                int i = 0;
                for (Map.Entry<String, String> entry : param.entrySet()
                        ) {
                    NameValuePair nameValuePair = new NameValuePair();

                    nameValuePair.setName(entry.getKey());
                    nameValuePair.setValue(entry.getValue());

                    nameValuePairs[i] = nameValuePair;
                    i++;
                }
            }

            String[] result = storageClient.upload_appender_file(IOUtils.toByteArray(inputStream), extName, nameValuePairs);


            StringBuffer stringBuffer = new StringBuffer();

            for (String str : result) {
                stringBuffer.append(str).append("/");
            }
            return stringBuffer.toString().substring(0, stringBuffer.toString().lastIndexOf("/"));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FastDFS 上传文件异常");
        } catch (MyException e) {
            e.printStackTrace();
            throw new RuntimeException("FastDFS 上传文件异常");
        }

    }

    /**
     * 获取StorageClient
     * @return
     * @throws IOException
     * @throws MyException
     */
    private StorageClient getStorageClient() throws IOException, MyException {
        //配置tracker
        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "192.168.153.146:22122");
        ClientGlobal.initByProperties(properties);

        //文件上传
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer storageServer = null;

        return new StorageClient(trackerServer, storageServer);
    }


    public void deleteFastDfsFIle(String fileId){
        try {
            StorageClient storageClient = getStorageClient();

            //group1|M00/00/00/wKiZklsSIXiEfhsQAAAAAFQ8qPs568.jpg|
            //截取GroupName,从第一个字符开始,到第一个/ 为:groupName
            String groupName = fileId.substring(0, fileId.indexOf("/"));

            //filePath 从第一个/ 到最后,+1 会从第二个/开始截取,一直到最后就对了.
            String filePath = fileId.substring(fileId.indexOf("/")+1);
            int i = storageClient.delete_file(groupName, filePath);
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        System.out.println("删除文件: " + fileId);
    }
    /**
     * 下载文件
     * @param fileId
     * @return
     */
    public byte[] downloadFile(String fileId) {
        try {
            //配置tracker
            StorageClient storageClient = getStorageClient();


            //group1|M00/00/00/wKiZklsSIXiEfhsQAAAAAFQ8qPs568.jpg|
            //截取GroupName,从第一个字符开始,到第一个/ 为:groupName
            String groupName = fileId.substring(0, fileId.indexOf("/"));

            //filePath 从第一个/ 到最后,+1 会从第二个/开始截取,一直到最后就对了.
            String filePath = fileId.substring(fileId.indexOf("/")+1);

            FileInfo fileInfo = storageClient.get_file_info(groupName,filePath);
            System.out.println(fileInfo.getCrc32());
            System.out.println(fileInfo.getFileSize());
            System.out.println(fileInfo.getSourceIpAddr());
            System.out.println(fileInfo.getCreateTimestamp());

            //获取文件附加属性,设备信息,长宽高等等
            NameValuePair[] metadata = storageClient.get_metadata(groupName, filePath);
            for (NameValuePair b :
                    metadata) {
                System.out.println(b.getName() + b.getValue());
            }
            return storageClient.download_file(groupName, filePath);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FastDFS 下载文件异常");
        } catch (MyException e) {
            e.printStackTrace();
            throw new RuntimeException("FastDFS 下载文件异常");
        }

    }


    //测试方法
    public static void main(String[] args) throws IOException, MyException {
//

        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Object fastDfsUtil = applicationContext.getBean("FastDfsUtil");

        System.out.println(FastDfsUtil.FASTDFS);*/
//        InputStream inputStream = new FileInputStream("d:/img/1.jpg");
//        FastDfsUtil fastDfsUtil = new FastDfsUtil();
//
//        Map<String,String> param = Maps.newHashMap();
//        param.put("witdh","100px");
//        param.put("height","100px");
//
//        String jpg = fastDfsUtil.uploadFile(inputStream, "jpg",param);
//        System.out.println(jpg);

//        FastDfsUtil fastDfsUtil = new FastDfsUtil();
//        byte[] bytes = fastDfsUtil.downloadFile("group1/M00/00/00/wKiZklsSI7qEBwwWAAAAAFQ8qPs104.jpg");
//
//        FileOutputStream fileOutputStream = new FileOutputStream("D:/xx.jpg");
//        fileOutputStream.write(bytes, 0, bytes.length);
//
//        fileOutputStream.flush();
//        fileOutputStream.close();
//
//        FastDfsUtil fastDfsUtil = new FastDfsUtil();
//        fastDfsUtil.deleteFastDfsFIle("group1/M00/00/00/wKiZklsSI7qEBwwWAAAAAFQ8qPs104.jpg");

    }
}
