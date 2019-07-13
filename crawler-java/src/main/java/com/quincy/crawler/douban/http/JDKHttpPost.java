package com.quincy.crawler.douban.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *  使用JDK原生的api进行网络请求---post方式
 *
 *  区别一：post请求的请求体中包含了提交的参数：useranem=zhangsan&password=lisi
 *  区别二： 打开 conn.setDoOutput(true);
 *  区别三：在老版本中，需要设置请求方式conn.setRequestMethod("POST");
 */
public class JDKHttpPost {
	
	public static void main(String[] args) throws Exception {
		// 1. 网络资源地址 url  http://www.itast.cn
		String zy = "http://www.itcast.cn";
		// 2. 发起一个网络请求  client-server(tomcat\nginx)
		// Jdk URL 
		URL url = new URL(zy);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		//User-Agent:Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
		
		
		// 请求方式的设置
		conn.setRequestMethod("POST");
		
		// 打开dooutput
		conn.setDoOutput(true);
		
		
		// 3. 发送数据  useranem=zhangsan&password=lisi
		OutputStream outputStream = conn.getOutputStream();
		outputStream.write("useranem=zhangsan&password=lisi".getBytes());
		outputStream.flush();
		outputStream.close();
		
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
