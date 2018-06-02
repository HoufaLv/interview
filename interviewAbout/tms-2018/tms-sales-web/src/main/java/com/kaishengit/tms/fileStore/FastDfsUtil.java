package com.kaishengit.tms.fileStore;


import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;

import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * FastDfs相关
 * @author durunmin
 */
@Component
public class FastDfsUtil {

    @Value("${fastdfs.tracker.server}")
    private String trackerServerAddr;

    public String uploadFile(InputStream inputStream, String extName) {
        return uploadFile(inputStream,extName,null);
    }

    public String uploadFile(InputStream inputStream, String extName, Map<String,String> param) {
        try {
            StorageClient storageClient = getStorageClient();

            NameValuePair[] nameValuePairs = null;
            if(param != null) {
                nameValuePairs = new NameValuePair[param.size()];
                int i = 0;
                for(Map.Entry<String,String> entry : param.entrySet()) {
                    NameValuePair nameValuePair = new NameValuePair();
                    nameValuePair.setName(entry.getKey());
                    nameValuePair.setValue(entry.getValue());

                    nameValuePairs[i] = nameValuePair;
                    i++;
                }
            }

            String[] result = storageClient.upload_file(IOUtils.toByteArray(inputStream), extName, nameValuePairs);

            StringBuffer stringBuffer = new StringBuffer();
            for (String str : result) {
                stringBuffer.append(str).append("/");
            }
            return stringBuffer.toString().substring(0,stringBuffer.toString().lastIndexOf("/"));
        } catch (IOException | MyException ex) {
            throw new RuntimeException("FastDFS上传文件异常",ex);
        }
    }


    public byte[] downloadFile(String fileId) {
        try {
            StorageClient storageClient = getStorageClient();

            //group1/M00/00/00/wKiHrFrhj5aAMg5zAAFxuB-_T9A852.jpg
            String groupName = fileId.substring(0,fileId.indexOf("/"));
            String filePath = fileId.substring(fileId.indexOf("/")+1);

           /* FileInfo fileInfo = storageClient.get_file_info(groupName,filePath);
            System.out.println(fileInfo.getFileSize());
            System.out.println(fileInfo.getCrc32());
            System.out.println(fileInfo.getCreateTimestamp());
            System.out.println(fileInfo.getSourceIpAddr());*/

           /*NameValuePair[] nameValuePairs = storageClient.get_metadata(groupName,filePath);
           for(NameValuePair nameValuePair : nameValuePairs) {
               System.out.println(nameValuePair.getName() + " -> " + nameValuePair.getValue());
           }*/

            return storageClient.download_file(groupName,filePath);
        } catch (IOException | MyException ex) {
            throw new RuntimeException("FastDFS下载文件异常",ex);
        }
    }

    private StorageClient getStorageClient() throws IOException, MyException {
        Properties properties = new Properties();
        //指定Tracker服务器的地址
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS,trackerServerAddr);

        ClientGlobal.initByProperties(properties);

        //创建tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        //根据客户端获取Tracker服务器对象
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer storageServer = null;
        return new StorageClient(trackerServer, storageServer);
    }

    public void deleteFile(String fileId) {
        try {
            StorageClient storageClient = getStorageClient();

            String groupName = fileId.substring(0,fileId.indexOf("/"));
            String filePath = fileId.substring(fileId.indexOf("/")+1);

            storageClient.delete_file(groupName,filePath);
        } catch (IOException | MyException ex) {
            throw new RuntimeException("FastDFS删除文件异常:" + fileId);
        }

    }

    /*public static void main(String[] args) throws IOException, MyException {

        FastDfsUtil util = new FastDfsUtil();
        util.deleteFile("group1/M00/00/00/wKiHrFrhk_yAasgCAAFiWXdeenc051.jpg");*/
        /*byte[] bytes = util.downloadFile("group1/M00/00/00/wKiHrFrhk_yAasgCAAFiWXdeenc014.jpg");

        FileOutputStream fileOutputStream = new FileOutputStream("D:/xxx.jpg");
        fileOutputStream.write(bytes);

        fileOutputStream.flush();
        fileOutputStream.close();*/


        /*FastDfsUtil util = new FastDfsUtil();
        InputStream inputStream = new FileInputStream("D:/temp/img/6247083138/4.jpg");

        Map<String,String> param = Maps.newHashMap();
        param.put("width","100");
        param.put("high","300");
        param.put("device","sony");

        String result = util.uploadFile(inputStream,"jpg",param);
        System.out.println(result);*/

  /*  }*/

}
