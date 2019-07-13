package com.quincy.crawler.douban.diver;

import com.quincy.crawler.common.DbUtils;
import com.quincy.crawler.douban.pojo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * author:quincy
 * Date:2019-07-08
 */
public class DouBanDriver {
    public static void main(String[] args) {
        DouBanDriver douBanDriver = new DouBanDriver();

        /*String domainUrl = "https://book.douban.com/tag/?view=type&icn=index-sorttags-hot#%E6%B5%81%E8%A1%8C";

        Document domainDoc = douBanDriver.getDocument(domainUrl);


        Elements bookTypeUrl = douBanDriver.getBookTypeUrl(domainDoc);*/
        int start = 0;
        //小说
        //爬取分页数据
        //分页规律
        //https://book.douban.com/tag/%E5%8E%86%E5%8F%B2?start=950&type=T
        while (true){
            String url = "https://book.douban.com/tag/编程?start="+start+"&type=T";
            Document doc = douBanDriver.getDocument(url);
            if (doc == null){
                break;
            }
            Elements elements = douBanDriver.getBooKOfPage(doc);
            if (elements != null && elements.size() > 0){
                douBanDriver.find(elements);
                start = start + 20;
            }else{
                break;
            }
        }
    }

    private Elements getBookTypeUrl(Document domainDoc){
        Elements select = domainDoc.select("a[name='文学']");
        return select;
    }

    private  void find(Elements elements){
        for (int i = 0; i < elements.size(); i++) {
           /* try {
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            String href = elements.get(i).attr("href");
            //把链接写入到本地文件中，方便查找错误
            //减少爬虫的速度，以防万一被禁止
            System.out.println(href);
            //再次发送请求获取数据
            Document document = getDocument(href);
            //获取书的id
            String bookid = href.substring("https://book.douban.com/subject/".length(),href.length() - 1);
            Book book = parseDocumentToBean(document, bookid);
            //把书存入到数据库
            System.out.println(book);
            try {
                QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSource());
                String sql = "insert into book (bookId,bookName,bookAuthor,bookOriginalName,bookPublish," +
                        "bookPublishTime,bookBind,price,isbn,image,pageNumber,bookProducers,bookSeries," +
                        "bookTranslator,doubanStar,bookType,bookContentInfo,bookAuthorInfo,bookCatalogue)" +
                        " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                Object[] params = new Object[]{null,book.getBookName(),book.getBookAuthor()
                ,book.getBookOriginalName(),book.getBookPublish(),book.getBookPublishTime()
                ,book.getBookBind(),book.getPrice(),book.getIsbn(),book.getImage()
                ,book.getPageNumber(),book.getBookProducers(),book.getBookSeries()
                ,book.getBookTranslator(),book.getDoubanStar(),book.getBookType()
                ,book.getBookContentInfo(),book.getBookAuthorInfo(),book.getBookCatalogue()};
                queryRunner.insert(sql,new ScalarHandler<>(),params);
            } catch (SQLException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private   Elements getBooKOfPage(Document doc){
        //获取页面有多少本书
        Elements elements = doc.select("li[class=subject-item] div[class=pic] a");
        return elements;
    }


    /**
     * 根据给定的文本查找该文本对应的父元素的下一个兄弟元素
     * @param document
     * @param text
     * @return
     */
    private Element getParentElementByText(Document document,String text){
        String rex = ":containsOwn("+text+")";
        Elements contentEle = document.select(rex);
        if (contentEle != null && contentEle.size() > 0){
            Element parentH2 = contentEle.get(0).parent();
            Element element = parentH2.nextElementSibling();
            return element;
        }
        return null;
    }

    /**
     * 把数据分装为javabean
     * @param document
     * @param bookid
     * @return
     */
    private  Book parseDocumentToBean(Document document,String bookid){
        Book book = new Book();

        book.setBookType("1");
        //书名字
        String bookName = document.select("#wrapper span[property=v:itemreviewed]").text();
        book.setBookName(bookName.trim());

        //图片路径
        String imageUrl = document.select("#mainpic .nbg").attr("href");
        book.setImage(imageUrl.trim());

        //豆瓣评分
        Elements doubanStarEle = document.select("strong[property=v:average]");
        if (doubanStarEle != null && doubanStarEle.size() > 0){
            String doubanStar = doubanStarEle.get(0).text();
            book.setDoubanStar(doubanStar.trim());
        }

        Element eleContent = getParentElementByText(document, "内容简介");
        if (eleContent != null){
            String bookContentInfo = eleContent.text();
            book.setBookContentInfo(bookContentInfo.trim());
        }
        Element eleAuthor = getParentElementByText(document, "作者简介");
        if (eleAuthor != null){
            String bookAuthorInfo = eleAuthor.text();
            book.setBookAuthorInfo(bookAuthorInfo.trim());
        }
       /* Elements contentAndAuthorEle = document.select(".related_info h2");
        for (int i = 0; i < 3; i++) {
            String contentAndAuthorText = contentAndAuthorEle.get(i).text();
            if (contentAndAuthorText.contains("内容简介")){
                String bookContentInfo = contentAndAuthorEle.get(i).nextElementSibling().text();
                book.setBookContentInfo(bookContentInfo.trim());
            }else if(contentAndAuthorText.contains("作者简介")){
                String bookAuthorInfo = contentAndAuthorEle.get(i).nextElementSibling().text();
                book.setBookAuthorInfo(bookAuthorInfo.trim());
            }
        }*/

        //目录介绍
        String id = "#dir_" + bookid + "_full";
        String bookCatalogue = document.select(id).text();
        book.setBookCatalogue(bookCatalogue.trim());

        /*Elements authorAnd = document.select("#wrapper #info a");
        for (int i = 0; i < authorAnd.size(); i++) {
            Element element = authorAnd.get(0);
            String text = element.text();
            book.setBookAuthor(text.trim());
        }*/
        Elements select = document.select("#wrapper #info span[class=pl]");
        for (int i = 0; i < select.size(); i++) {
            String text = select.get(i).text();
            if ("作者:".contains(text)){
                String authorText = select.get(i).nextElementSibling().text();
                book.setBookAuthor(authorText.trim());
            }else if("出版社:".contains(text)){
                Node node = select.get(i).nextSibling();
                if (node != null){
                    String bookPublish = node.toString().trim();
                    book.setBookPublish(bookPublish);
                }
            }else if("出版年:".contains(text)){
                Node node = select.get(i).nextSibling();
                if (node != null){
                    String bookPublishTime = node.toString().trim();
                    book.setBookPublishTime(bookPublishTime);
                }
            }else if("页数:".contains(text)){
                Node node = select.get(i).nextSibling();
                if (node != null){
                    String pageNumber = node.toString().trim();
                    book.setPageNumber(pageNumber);
                }
            }else if("定价:".contains(text)){
                Node node = select.get(i).nextSibling();
                if (node != null){
                    String price = node.toString().trim();
                    book.setPrice(price);
                }
            }else if("装帧:".contains(text)){
                Node node = select.get(i).nextSibling();
                if (node != null){
                    String bookBind = node.toString().trim();
                    book.setBookBind(bookBind);
                }
            }else if("ISBN:".contains(text)){
                Node node = select.get(i).nextSibling();
                if (node != null){
                    String isbn = node.toString().trim();
                    book.setIsbn(isbn);
                }
            }else if("原作名:".contains(text)){
                Node node = select.get(i).nextSibling();
                if (node != null){
                    String bookOriginalName = node.toString().trim();
                    book.setBookOriginalName(bookOriginalName);
                }
            }else if("译者:".contains(text)){
                Element bookTranslatorEle = select.get(i).nextElementSibling();
                if (bookTranslatorEle != null){
                    String bookTranslator = bookTranslatorEle.text().trim();
                    book.setBookTranslator(bookTranslator);
                }
            }else if("出品方:".contains(text)){
                Element bookProducersEle = select.get(i).nextElementSibling();
                if (bookProducersEle != null){
                    String bookProducers = bookProducersEle.text().trim();
                    book.setBookProducers(bookProducers);
                }
            }else if("丛书:".contains(text)){
                Element bookSeriesEle = select.get(i).nextElementSibling();
                if (bookSeriesEle != null){
                    String bookSeries = bookSeriesEle.text().trim();
                    book.setBookSeries(bookSeries);
                }
            }
        }
        return book;
    }

    //根据指定的url获取页面Document
    private  Document getDocument(String url){

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
                "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};

        int i = r.nextInt(14);
        try {
            /*HttpGet httpGet = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse res = httpClient.execute(httpGet);
            HttpEntity entity = res.getEntity();
            String html = EntityUtils.toString(entity, Charset.forName("utf-8"));*/
            //218.204.204.90
            Connection proxy = Jsoup.connect(url).proxy("47.102.216.176", 3128);
            Document doc = proxy.userAgent(ua[i])//.timeout(6000)
                    .post();
            //Document doc = Jsoup.parse(html);
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
