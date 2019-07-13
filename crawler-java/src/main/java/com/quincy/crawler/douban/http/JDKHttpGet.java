package com.quincy.crawler.douban.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 *  使用JDK原生的api进行网络请求---get方式
 *
 */
public class JDKHttpGet {
	
	public static void main(String[] args) throws Exception {
		// 1. 网络资源地址 url  http://www.itast.cn
		String zy = "http://www.itcast.cn";
		// 2. 发起一个网络请求  client-server(tomcat\nginx)
		// Jdk URL 
		URL url = new URL(zy);
		URLConnection conn = url.openConnection();
		// 3. 发送数据  http://www.itast.cn？useranem=zhangsan&password=lisi
		
		// 4. 得到一个响应的数据，输入流（html文档的二进制文件，字符流）
		InputStream inputStream = conn.getInputStream();
		// 5. 打印结果（bufferreader 一行一行的读取）
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
		
		String line = null;
		while((line = bufferedReader.readLine())!=null){
			System.out.println(line);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
