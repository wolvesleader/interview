package com.quincy.crawler.douban.diver;

import com.quincy.crawler.douban.http.HttpClientUtils;
import com.quincy.crawler.douban.pojo.Book;
import com.quincy.crawler.douban.pojo.BookList;
import com.quincy.crawler.douban.pojo.Ip;
import com.quincy.crawler.utils.BookUtils;
import com.quincy.crawler.utils.IpPool;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;


/**
 * author:quincy
 * Date:2019-07-08
 * @author quincy
 * 1.爬取完一页之后就会停止
 * 2.如果书名都没有需要开启线程，把该条数据的信息存入到数据库中
 * 3.如果解析书籍列表长度==0需要把url存入到数据库中
 * 4.把书价格12元中的元去掉
 * 5.需要把图片保存到我们的服务器上
 */
public class DouBanDriver {

    private static Logger logger = LoggerFactory.getLogger(DouBanDriver.class);

    public static void main(String[] args) {
        DouBanDriver douBanDriver = new DouBanDriver();
        douBanDriver.startup(douBanDriver);
    }




    public void startup(DouBanDriver douBanDriver){
        BookList bookList = new BookList();
        Ip availableIp = IpPool.getAvailableIp();
        while (true) {
            logger.info("当前爬取到第几页[{}]", bookList.getCurrentPage());
            System.out.println(bookList.getBookListUrl());
            Document bookListDoc = douBanDriver.parseListUrlToDocument(bookList,availableIp);
            if (bookListDoc != null) {
                douBanDriver.parseBookList(bookListDoc, bookList);
                douBanDriver.parseBookDetailToDocument(bookList);
            }else {
                availableIp = IpPool.getAvailableIp();
            }
        }
    }


    private  void parseBookDetailToDocument(BookList bookList){
        Elements elements = bookList.getElements();
        Ip ip = IpPool.getAvailableIp();
        for (int i = 0; i < elements.size(); i++) {
            String href = elements.get(i).attr("href");
            System.out.println(ip.getIp() + ":" + href);
            Document document = parseBookDetailUrlToDocument(href,ip);
            if (document == null){
                i --;
                ip = IpPool.getAvailableIp();
                continue;
            }
            //获取书的id
            String bookId = href.substring("https://book.douban.com/subject/".length(),href.length() - 1);
            Book book = BookUtils.parseBookDetailToBean(document, bookId);
            if (book.getBookName() == null || "".equals(book.getBookName().trim())){
                //设置线程池，开启线程，向数据库中插入数据
                logger.error("book爬取异常[{}]",href);
            }
            //采用线程池把书存入到数据库
            System.out.println(book);
        }

        if (elements.size() > 0){
            bookList.setCurrentPage(bookList.getCurrentPage() + 20);
            bookList.setBookListUrl(bookList.getCurrentPage()+"");
        }

    }

    private void parseBookList(Document document,BookList bookList){
        Elements elements = document.select("li[class=subject-item] div[class=pic] a");
        if (elements.size() > 0){
           bookList.setElements(elements);
        }else {
            //
            logger.error("elements元素不是正确的格式【{}】",document);
        }
    }

    private  Document parseListUrlToDocument(BookList bookList,Ip ip){
        try {
            //Ip ip = getIp();
            Connection proxy = Jsoup.connect(bookList.getBookListUrl()).proxy(ip.getIp(),ip.getPort());
            Document document = proxy.userAgent(getUserAgent()).timeout(2000).post();
            return document;
        } catch (IOException e) {
            logger.info("出现连接异常===============");
            //1.ip不可用回出现异常
            //2.之前爬取了一部分，出现异常了
            //parseListUrlToDocument(bookList);
        }
        return null;
    }

    private Document parseBookDetailUrlToDocument(String url,Ip ip){

        try {
            Connection proxy = Jsoup.connect(url).proxy(ip.getIp(),ip.getPort());
            String userAgent = getUserAgent();
            Document document = proxy.userAgent(userAgent).timeout(5000).maxBodySize(Integer.MAX_VALUE).post();
            return document;
        } catch (IOException e) {
            logger.info("parseBookDetailUrlToDocument出现连接异常===============");
            //1.ip不可用回出现异常
            //2.之前爬取了一部分，出现异常了
            //Ip ipNew = getIp();
            //parseBookDetailUrlToDocument(url,ip);
        }
        return null;
    }

    private String getUserAgent(){
        Random r = new Random();
        String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
                //"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36",
                //"Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X; zh-CN) AppleWebKit/537.51.1 (KHTML, like Gecko) Mobile/17D50 UCBrowser/12.8.2.1268 Mobile AliApp(TUnionSDK/0.1.20.3)",
                //"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36",
                //"Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 SP-engine/2.14.0 main%2F1.0 baiduboxapp/11.18.0.16 (Baidu; P2 13.3.1) NABar/0.0",
                //"Mozilla/5.0 (iPhone; CPU iPhone OS 12_4_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.10(0x17000a21) NetType/4G Language/zh_CN"
        };
        int i = r.nextInt(14);
        return ua[i];
    }

}
