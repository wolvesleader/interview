package com.quincy.crawler.douban.http;

import com.quincy.crawler.douban.pojo.Ip;
import com.quincy.crawler.utils.IpPool;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: HttpClient
 * @author: quincy
 * @Date: 2021/2/9 下午8:49
 * @History:
 */

public class HttpClientUtils {





    public static void getIp() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("https://proxyapi.horocn.com/api/v2/proxies?order_id=RRC21691212369570157&num=10&format=text&line_separator=win&user_token=10773e076c278999d61fb74d9ea6d74a");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                InputStream in = responseEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
                String temp = "";
                String s = "";
                while ((temp = br.readLine()) != null) {
                    String[] split = temp.split(":");
                    Ip ip = new Ip();
                    ip.setIp(split[0]);
                    ip.setPort(Integer.valueOf(split[1]));
                    IpPool.ipArrayList.add(ip);
                }

                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
