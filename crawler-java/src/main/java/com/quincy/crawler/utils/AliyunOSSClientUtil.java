package com.quincy.crawler.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wuming on 2018/2/6.
 */
public class AliyunOSSClientUtil {



    public static String uploadNetworkFile(String path,String folder,String name) throws Exception {
      /*  OSS ossClient = new OSSClientBuilder().build(OSSClientConstants.FILEENDPOINT, OSSClientConstants.ACCESSKEYID, OSSClientConstants.ACCESSKEYSECRET);
        InputStream inputStream = new URL(path).openStream();
        ossClient.putObject(OSSClientConstants.PICTUREBUCKET, folder+name, inputStream);
        ossClient.shutdown();*/
        return name;
    }

    /**
     * 上传文件
     * @param folder OSS中的目录名称 如：book/;userself/...
     * @param name 想要保存的名字
     * @param file 上传文件
     * @return 返回图片访问地址
     */
    public static String uploadingFile(String folder,String name,File file){
        // 创建OSSClient实例
       /* OSSClient ossClient = new OSSClient(OSSClientConstants.FILEENDPOINT, OSSClientConstants.ACCESSKEYID, OSSClientConstants.ACCESSKEYSECRET);
        //上传
        ossClient.putObject(OSSClientConstants.PICTUREBUCKET, folder+name, file);
        ossClient.shutdown();
        return OSSClientConstants.PICTUREURL_PREFIX+folder+name;*/
        return null;
    }

    /**
     * 文件下载
     * @param name 想要下载文件名字
     * @param filename 文件的保存名字
     * @throws IOException
     */
    public static void downloadFile(String name,String filename) throws IOException {
        // 创建OSSClient实例
       /* OSSClient ossClient = new OSSClient(OSSClientConstants.FILEENDPOINT, OSSClientConstants.ACCESSKEYID, OSSClientConstants.ACCESSKEYSECRET);
        // 下载object到文件
        ossClient.getObject(new GetObjectRequest(OSSClientConstants.PICTUREBUCKET, name), new File(filename));
        // 关闭client
        ossClient.shutdown();*/
    }

    /**
     * 视频断点上传
     * @param folder OSS中的目录名称 如：book/;userself/...
     * @param name 想要保存的名字
     * @param filename 文件的全名
     * @throws Throwable
     */
    public static String uploadingVideo(String folder,String name,String filename) throws Throwable {
        // 创建OSSClient实例
        /*OSSClient ossClient = new OSSClient(OSSClientConstants.VIDEOENDPOINT, OSSClientConstants.ACCESSKEYID, OSSClientConstants.ACCESSKEYSECRET);
        // 设置断点续传请求
        UploadFileRequest uploadFileRequest = new UploadFileRequest(OSSClientConstants.VIDEOBUCKET, folder+name);
        // 指定上传的本地文件
        uploadFileRequest.setUploadFile(filename);
        // 指定上传并发线程数
        uploadFileRequest.setTaskNum(5);
        // 指定上传的分片大小
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
        // 开启断点续传
        uploadFileRequest.setEnableCheckpoint(true);
        // 断点续传上传
        ossClient.uploadFile(uploadFileRequest);
        // 关闭client
        ossClient.shutdown();
        return OSSClientConstants.VIDEOURL_PREFIX+folder+name;*/
        return null;
    }

    /**
     * 视频断点下载
     * @param name 想要下载文件名字
     * @param filename 文件的保存名字
     * @throws Throwable
     */
    public static void downloadVideo(String name,String filename) throws Throwable {
        // 创建OSSClient实例
       /* OSSClient ossClient = new OSSClient(OSSClientConstants.VIDEOENDPOINT, OSSClientConstants.ACCESSKEYID, OSSClientConstants.ACCESSKEYSECRET);
        // 下载请求，10个任务并发下载，启动断点续传
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(OSSClientConstants.VIDEOBUCKET, name);
        downloadFileRequest.setDownloadFile(filename);
        downloadFileRequest.setTaskNum(10);
        downloadFileRequest.setEnableCheckpoint(true);
        // 下载文件
        DownloadFileResult downloadRes = ossClient.downloadFile(downloadFileRequest);
        // 下载成功时，会返回文件的元信息
        downloadRes.getObjectMetadata();
        // 关闭client
        ossClient.shutdown();*/
    }

    /**
     * 流方式上传图片
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 返回"" 图片地址url
     */
    public static String uploadFileWithStream(InputStream instream, String folder,String fileName) {
        /*OSSClient ossClient = new OSSClient(OSSClientConstants.FILEENDPOINT, OSSClientConstants.ACCESSKEYID, OSSClientConstants.ACCESSKEYSECRET);
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(OSSClientConstants.PICTUREBUCKET, folder + fileName, instream, objectMetadata);
        } catch (IOException e) {
            e.getMessage();
            return null;
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return OSSClientConstants.PICTUREURL_PREFIX+folder+fileName;*/
        return null;
    }
    /**
     * 流方式上传视频
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 返回"" 视频地址url
     */
    public static String uploadVidWithStream(InputStream instream, String folder,String fileName){
        /*OSSClient ossClient = new OSSClient(OSSClientConstants.VIDEOENDPOINT, OSSClientConstants.ACCESSKEYID, OSSClientConstants.ACCESSKEYSECRET);
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(OSSClientConstants.VIDEOBUCKET, folder + fileName, instream, objectMetadata);
        } catch (IOException e) {
            e.getMessage();
            return null;
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return OSSClientConstants.VIDEOURL_PREFIX+folder+fileName;*/
        return null;
    }

    /**
     * 判断OSS服务文件上传时文件的类型contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (FilenameExtension.equalsIgnoreCase(".avi")) {
            return "video/x-msvideo";
        }
        if (FilenameExtension.equalsIgnoreCase(".mp4")) {
            return "application/octet-stream";
        }
        return "image/jpg";
    }

    public static void main(String[] args) throws Throwable {
        //System.out.println(uploadingFile("cover/","ystest.jpg",new File("https://img3.doubanio.com/view/subject/l/public/s29053580.jpg")));
        String url = "https://img3.doubanio.com/view/subject/l/public/s29053580.jpg";
        int i = url.lastIndexOf("/");
        String substring = url.substring(i+1);
        System.out.println(substring);
        // System.out.println(uploadingVideo("userself/","ysvideo8.mp4","/Users/ruoquan/Desktop/1588767009339109.mp4"));
        //File f = new File("/Users/ruoquan/Desktop/1588767009339109.mp4");
        //System.out.println(new MimetypesFileTypeMap().getContentType(f));
    }
}
