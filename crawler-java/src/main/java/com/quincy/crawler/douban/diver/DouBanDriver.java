package com.quincy.crawler.douban.diver;

import com.quincy.crawler.dao.BookDao;
import com.quincy.crawler.douban.http.HttpClientUtils;
import com.quincy.crawler.douban.pojo.Book;
import com.quincy.crawler.douban.pojo.BookList;
import com.quincy.crawler.douban.pojo.BookType;
import com.quincy.crawler.douban.pojo.Ip;
import com.quincy.crawler.utils.AliyunOSSClientUtil;
import com.quincy.crawler.utils.BookUtils;
import com.quincy.crawler.utils.IpPool;

import com.quincy.crawler.utils.ThreadPoolUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
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
 *
 * 常见的ip代理
 * 贝壳
 * 站大爷
 * 蜻蜓代理
 * 快代理
 * 阿步云
 * https://mp.weixin.qq.com/s/s8I5ml8pYFGBJFuxXd7yEA
 */
public class DouBanDriver {

    private BookDao bookDao = new BookDao();

    private static Logger logger = LoggerFactory.getLogger(DouBanDriver.class);

    public static void main(String[] args) {
        DouBanDriver douBanDriver = new DouBanDriver();
        douBanDriver.startup(douBanDriver);

    }



    public void startup(DouBanDriver douBanDriver)  {
        ArrayList<BookType> bookTypes = new ArrayList<>(15);


        BookType bookType = new BookType("1.1","小说");
        BookType bookType2 = new BookType("1.2","外国文学");
        BookType bookType3 = new BookType("1.3","文学");
        BookType bookType4 = new BookType("1.4","经典");
        BookType bookType5 = new BookType("1.5","中国文学");
        //爬取到480
        BookType bookType6 = new BookType("1.6","随笔");
        BookType bookType7 = new BookType("1.7","日本文学");
        BookType bookType8 = new BookType("1.8","散文");
        BookType bookType9 = new BookType("1.9","村上春树");
        BookType bookType10 = new BookType("1.10","诗歌");
        BookType bookType11 = new BookType("1.11","童话");
        BookType bookType12 = new BookType("1.12","名著");
        /*bookTypes.add(bookType);bookTypes.add(bookType2);bookTypes.add(bookType3);
        bookTypes.add(bookType4);bookTypes.add(bookType5);bookTypes.add(bookType6);*/
        //bookTypes.add(bookType7);bookTypes.add(bookType8);bookTypes.add(bookType9);
        //bookTypes.add(bookType10);bookTypes.add(bookType11);bookTypes.add(bookType12);

       BookType bookType13 = new BookType("1.13","儿童文学");
       //古典文学?start=600&type=T
        BookType bookType14 = new BookType("1.14","古典文学");
        BookType bookType15 = new BookType("1.15","余华");
         BookType bookType16 = new BookType("1.16","王小波");
        BookType bookType17 = new BookType("1.17","杂文");
        BookType bookType18 = new BookType("1.18","当代文学");
        BookType bookType19 = new BookType("1.19","张爱玲");
         BookType bookType20 = new BookType("1.20","外国名著");
         BookType bookType21 = new BookType("1.21","钱钟书");
         BookType bookType22 = new BookType("1.22","鲁迅");
         BookType bookType23 = new BookType("1.23","茨威格");
         BookType bookType24 = new BookType("1.24","米兰·昆德拉");
         BookType bookType25 = new BookType("1.25","杜拉斯");
         BookType bookType26 = new BookType("1.26","港台");
         BookType bookType27 = new BookType("2.2","推理");
        BookType bookType28 = new BookType("2.3","绘本");
         BookType bookType29 = new BookType("2.4","东野圭吾");
         BookType bookType30 = new BookType("2.5","青春");
         BookType bookType31 = new BookType("2.6","悬疑");
         BookType bookType32 = new BookType("2.7","科幻");
         BookType bookType33 = new BookType("2.8","言情");
        //BookType bookType28 = new BookType("2.9","推理小说");
         BookType bookType34 = new BookType("2.10","奇幻");
         BookType bookType35 = new BookType("2.11","武侠");
         BookType bookType36 = new BookType("2.12","日本漫画");
         BookType bookType37 = new BookType("2.13","耽美");
         BookType bookType38 = new BookType("2.14","网络小说");
         BookType bookType39 = new BookType("2.15","科幻小说");
         BookType bookType40 = new BookType("2.16","韩寒");
         BookType bookType41 = new BookType("2.17","三毛");
         BookType bookType42 = new BookType("2.18","亦舒");
         BookType bookType43 = new BookType("2.19","阿加莎·克里斯蒂");
         BookType bookType44 = new BookType("2.20","金庸");
         BookType bookType45 = new BookType("2.21","安妮宝贝");
         BookType bookType46 = new BookType("2.22","穿越");
         BookType bookType47 = new BookType("2.23","轻小说");
         BookType bookType48 = new BookType("2.24","魔幻");
         BookType bookType49 = new BookType("2.25","郭敬明");
         BookType bookType50 = new BookType("2.26","青春文学");
         BookType bookType51 = new BookType("2.27","几米");
         BookType bookType52 = new BookType("2.28","J.K.罗琳");
         BookType bookType53 = new BookType("2.29","幾米");
         BookType bookType54 = new BookType("2.30","张小娴");
         BookType bookType55 = new BookType("2.31","校园");
         BookType bookType56 = new BookType("2.32","古龙");
         BookType bookType57 = new BookType("2.33","高木直子");
         BookType bookType58 = new BookType("2.34","沧月");
         BookType bookType59 = new BookType("2.35","余秋雨");
         BookType bookType60 = new BookType("2.36","落落");

        BookType bookType61 = new BookType("2.20","历史");
        BookType bookType62 = new BookType("2.21","心理学");
        BookType bookType63 = new BookType("2.22","哲学");
        BookType bookType64 = new BookType("2.23","轻小说");
        BookType bookType65 = new BookType("2.24","社会学");
        BookType bookType66 = new BookType("2.25","传记");
        BookType bookType67 = new BookType("2.26","设计");
        BookType bookType68 = new BookType("2.27","政治学");
        BookType bookType69 = new BookType("2.28","宗教");
        BookType bookType70 = new BookType("2.29","建筑");
        BookType bookType71 = new BookType("2.30","电影");
        BookType bookType72 = new BookType("2.31","数学");
        BookType bookType73 = new BookType("2.32","中国历史");
        BookType bookType74 = new BookType("2.33","回忆录");
        BookType bookType75 = new BookType("2.34","思想");
        BookType bookType76 = new BookType("2.35","人物传记");
        BookType bookType77 = new BookType("2.36","国学");
        BookType bookType78 = new BookType("2.36","艺术史");

        BookType bookType79 = new BookType("2.36","人文");
        BookType bookType80 = new BookType("2.36","音乐");
        BookType bookType81 = new BookType("2.36","绘画");
        BookType bookType82 = new BookType("2.36","戏剧");
        BookType bookType83 = new BookType("2.42","西方哲学");
        BookType bookType84 = new BookType("2.43","近代史");
        BookType bookType85 = new BookType("2.44","二战");
        BookType bookType86 = new BookType("2.45","军事");
        BookType bookType87 = new BookType("2.46","佛教");
        BookType bookType88 = new BookType("2.47","考古");
        BookType bookType89 = new BookType("2.48","自由主义");
        BookType bookType90 = new BookType("2.49","美术");


        //bookTypes.add(bookType15);bookTypes.add(bookType16);

       /* bookTypes.add(bookType17); bookTypes.add(bookType18);
        bookTypes.add(bookType19); bookTypes.add(bookType20);
        bookTypes.add(bookType21); bookTypes.add(bookType22);
        bookTypes.add(bookType23); bookTypes.add(bookType24);
*/
        //bookTypes.add(bookType25); bookTypes.add(bookType26);
        //bookTypes.add(bookType27); bookTypes.add(bookType28);
        //bookTypes.add(bookType29); bookTypes.add(bookType30);
       // bookTypes.add(bookType31); bookTypes.add(bookType32);
        //bookTypes.add(bookType33); bookTypes.add(bookType34);
        //bookTypes.add(bookType35); bookTypes.add(bookType36);
        //bookTypes.add(bookType37);
        //bookTypes.add(bookType38);bookTypes.add(bookType39);
        //bookTypes.add(bookType40);bookTypes.add(bookType41);
        //bookTypes.add(bookType42);bookTypes.add(bookType43);

        //bookTypes.add(bookType44);bookTypes.add(bookType45);
        //bookTypes.add(bookType46);bookTypes.add(bookType47);
        //bookTypes.add(bookType48);bookTypes.add(bookType49);
        //bookTypes.add(bookType50);bookTypes.add(bookType51);
        /*bookTypes.add(bookType61);bookTypes.add(bookType62);
        bookTypes.add(bookType63);bookTypes.add(bookType64);
        bookTypes.add(bookType65);bookTypes.add(bookType66);
        bookTypes.add(bookType67);bookTypes.add(bookType68);
        bookTypes.add(bookType69);bookTypes.add(bookType70);
        bookTypes.add(bookType71);bookTypes.add(bookType72);
        bookTypes.add(bookType73);bookTypes.add(bookType74);
        bookTypes.add(bookType75);bookTypes.add(bookType76);*/

        //bookTypes.add(bookType77);bookTypes.add(bookType78);
        //bookTypes.add(bookType79);bookTypes.add(bookType80);
        //bookTypes.add(bookType81);bookTypes.add(bookType82);
        //bookTypes.add(bookType83);



        BookType bookType91 = new BookType("3.1","爱情");
        BookType bookType92 = new BookType("3.2","成长");
        BookType bookType93 = new BookType("3.3","生活");
        BookType bookType94 = new BookType("3.4","心理");
        BookType bookType95 = new BookType("3.5","旅行");
        BookType bookType96 = new BookType("3.6","女性");
        BookType bookType97 = new BookType("3.7","励志");
        BookType bookType98 = new BookType("3.8","教育");
        BookType bookType99 = new BookType("3.9","摄影");
        BookType bookType100 = new BookType("3.10","职场");
        BookType bookType101 = new BookType("3.11","美食");
        BookType bookType102 = new BookType("3.12","游记");
        BookType bookType103 = new BookType("3.7","灵修");
        BookType bookType104 = new BookType("3.8","健康");
        BookType bookType105 = new BookType("3.9","情感");
        BookType bookType106 = new BookType("3.10","人际关系");
        BookType bookType107 = new BookType("3.11","两性");
        BookType bookType108 = new BookType("3.12","养生");
        BookType bookType109= new BookType("3.9","手工");
        BookType bookType110 = new BookType("3.10","人际关系");
        BookType bookType111 = new BookType("3.11","家居");



        BookType bookType112 = new BookType("3.12","自助游");
        BookType bookType113 = new BookType("4.1","经济学");
        BookType bookType114 = new BookType("4.2","管理");
        BookType bookType115 = new BookType("4.3","经济");
        BookType bookType116= new BookType("4.4","商业");
        BookType bookType117 = new BookType("4.5","金融");
        BookType bookType118 = new BookType("4.6","投资");
        BookType bookType119 = new BookType("4.7","营销");
        BookType bookType120= new BookType("4.8","理财");


        BookType bookType121 = new BookType("4.9","创业");
        BookType bookType122 = new BookType("4.10","股票");
        BookType bookType123 = new BookType("4.11","广告");
        BookType bookType124= new BookType("4.12","企业史");
        BookType bookType125 = new BookType("4.13","策划");
        BookType bookType126 = new BookType("5.1","科普");


        BookType bookType127 = new BookType("5.2","互联网");
        BookType bookType128 = new BookType("5.3","科学");
        BookType bookType129= new BookType("5.4","编程");
        BookType bookType130 = new BookType("5.5","交互设计");
        BookType bookType131 = new BookType("5.6","算法");
        BookType bookType132 = new BookType("5.7","用户体验");
        BookType bookType133 = new BookType("5.8","科技");
        BookType bookType134= new BookType("5.9","web");
        BookType bookType135 = new BookType("5.10","交互");

        BookType bookType136 = new BookType("5.11","通信");
        BookType bookType137 = new BookType("5.12","UE");
        BookType bookType138 = new BookType("5.13","神经网络");
        BookType bookType139= new BookType("5.14","UCD");
        BookType bookType140 = new BookType("5.15","程序");

        bookTypes.add(bookType127);bookTypes.add(bookType128);
        bookTypes.add(bookType129);bookTypes.add(bookType130);
        bookTypes.add(bookType131);bookTypes.add(bookType132);
        bookTypes.add(bookType133);bookTypes.add(bookType134);
        bookTypes.add(bookType135);bookTypes.add(bookType136);
        bookTypes.add(bookType137);bookTypes.add(bookType138);
        bookTypes.add(bookType139);bookTypes.add(bookType140);



        for (int i = 0; i < bookTypes.size(); i++) {
            BookType result = bookTypes.get(i);
            load(douBanDriver, result);
        }

    }

    private void load(DouBanDriver douBanDriver,BookType bookType){
        BookList bookList = new BookList();
        bookList.setBookType(bookType);
        Ip availableIp = IpPool.getAvailableIp();
        while (true) {
            logger.info("当前爬取到第几页[{}]", bookList.getCurrentPage());
            if (bookList.getCurrentPage() >= 1000){
                //System.exit(0);
                break;
            }
            System.out.println(bookList.getBookListUrl());
            Document bookListDoc = douBanDriver.parseListUrlToDocument(bookList,availableIp);
            if (checkNotBook(bookListDoc)){
                break;
            }
            if (bookListDoc != null ) {
                douBanDriver.parseBookList(bookListDoc, bookList);
                douBanDriver.parseBookDetailToDocument(bookList);
            }else {
                availableIp = IpPool.getAvailableIp();
            }
        }
    }

    /**
     * 检测是否爬取到最后一页
     * @param bookListDoc
     * @return
     */
    private boolean checkNotBook(Document bookListDoc){
        if (bookListDoc != null) {
            Elements lastPage = bookListDoc.select("p[class=pl2]");
            if (lastPage != null && lastPage.size() > 0
                  && "没有找到符合条件的图书".equals(lastPage.get(0).text())){
               return true;
            }
        }
        return false;
    }



    private String getISBN(Document document){
        Element last = document.select("#wrapper #info span[class=pl]").last();
        if (last != null){
            Node node = last.nextSibling();
            if (node != null){
              return  node.toString().trim();
            }
        }
        return null;
    }

    private  void parseBookDetailToDocument(BookList bookList) {
        Elements elements = bookList.getElements();
        if (elements.size() == 0){
            return;
        }
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
            String isbn = getISBN(document);
            if (isbn == null){
                continue;
            }
            //查询数据库
            boolean byIsbn = bookDao.findByIsbn(isbn);
            if (!byIsbn){
                logger.error("存在的ISBN为[{}]",isbn);
                continue;
            }

            Book book = BookUtils.parseBookDetailToBean(document, bookId,bookList);
            if (book.getBookName() == null || "".equals(book.getBookName().trim())){
                //设置线程池，开启线程，向数据库中插入数据
                logger.error("book爬取异常[{}]",href);
                ThreadPoolUtils.execute(href);
                continue;
            }

            String image = book.getImage();
            //上传图片
            if (image != null && !"null".equals(image) && !"".equals(image.trim())){
                try {
                    int lastIndexOf = image.lastIndexOf("/");
                    String imageName = image.substring(lastIndexOf+1);
                    String path = AliyunOSSClientUtil.uploadNetworkFile(image, "cover/", imageName);
                    book.setImage(path);
                } catch (Exception e) {
                    //e.printStackTrace();
                    logger.error("book图片路径异常[{}]",image);

                }
            }

            //采用线程池把书存入到数据库
            System.out.println(book);
            ThreadPoolUtils.executeBook(book);
        }
        if (elements.size() > 0){
            bookList.setCurrentPage(bookList.getCurrentPage() + 20);
        }

    }

    private void parseBookList(Document document,BookList bookList){
        Elements elements = document.select("li[class=subject-item] div[class=pic] a");
        if (elements.size() > 0){
           bookList.setElements(elements);
        }else {
            //获取一下看是否为没有找到符合条件的图书
            Elements elementsNUll = new Elements();
            bookList.setElements(elementsNUll);
            //把有问题的path存入到数据库
            //ThreadPoolUtils.execute(bookList.getBookListUrl());
            logger.error("elements元素不是正确的格式【{}】",elements);
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
