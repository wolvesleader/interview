package com.quincy.crawler.utils;

import com.quincy.crawler.douban.pojo.Book;
import com.quincy.crawler.douban.pojo.BookList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: BookUtils
 * @author: quincy
 * @Date: 2021/2/9 下午8:55
 * @History:
 */

public class BookUtils {


    /**
     *
     * 1.文学
     *  1.1 小说
     *  =================
     *  1.2外国文学
     *  1.3文学
     *  1.4经典
     *  1.5中国文学
     *  1.6随笔
     *  1.7日本文学
     *  1.8散文
     *  1.9村上春树
     *  1.10诗歌
     *  1.11童话
     *  1.12名著
     *  1.13儿童文学
     *  1.14古典文学
     *  1.15余华
     *  1.16王小波
     *  1.17杂文
     *  1.18当代文学
     *  1.19张爱玲
     *  1.20外国名著
     *  1.21钱钟书
     *  1.22鲁迅
     *  1.23诗词
     *  1.24茨威格
     *  1.25米兰·昆德拉
     *  1.26杜拉斯
     *  1.27港台
     *
     *

     * 2.流行
     *  2.1 漫画
     *      2.2推理(1292462)	绘本(1114100)	东野圭吾(818767)
     *      青春(804472)	悬疑(793234)	科幻(740123)	言情(608497)
     *      推理小说(461207)	奇幻(427491)	武侠(382324)	日本漫画(364925)
     *      耽美(349412)	网络小说(277935)	科幻小说(274694)	韩寒(270781)
     *      三毛(264100)	亦舒(246103)	阿加莎·克里斯蒂(220660)	金庸(195061)
     *      安妮宝贝(177474)	穿越(173415)	轻小说(160259)	魔幻(159851)
     *      郭敬明(159640)	青春文学(150052)	几米(121622)	J.K.罗琳(117509)
     *      幾米(105390)	张小娴(98733)	校园(89270)	古龙(85910)
     *      高木直子(78368)	沧月(68794)	余秋雨(63450)	落落(58491)
     * 3.文化
     *  3.1 历史
     *
     历史(2949870)	心理学(1913303)	哲学(1678104)	社会学(1168478)
     传记(1035547)	文化(992372)	艺术(744750)	社会(699467)
     政治(556443)	设计(496244)	政治学(354577)	宗教(344460)
     建筑(328833)	电影(320730)	数学(298925)	中国历史(297347)
     回忆录(258597)	思想(224521)	人物传记(198810)	国学(194951)
     艺术史(184667)	人文(170445)	音乐(156782)	绘画(151437)
     戏剧(146536)	西方哲学(133553)	近代史(118531)	二战(114539)
     军事(102878)	佛教(98420)	考古(71488)	自由主义(60478)
     美术(55128)
     * 4.生活
     *  4.1 爱情
     * 5.经管
     *  5.1经济学
     * 6.科技
     *  6.1科普
     *  6.2互联网
     *  6.3编程
     *  6.4科学
     *  6.5交互设计
     *  6.6算法
     *  6.7用户体验
     *  6.8科技
     *  6.9web
     *  6.10交互
     *  6.11通信
     */
    //static String  bookType = "1.7";


    /**
     * 根据给定的文本查找该文本对应的父元素的下一个兄弟元素
     * @param document
     * @param text
     * @return
     */
    private static Element getParentElementByText(Document document,String text){
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
     * @param bookId
     * @return
     */
    public static Book parseBookDetailToBean(Document document, String bookId, BookList bookList){

        if (document == null) {
            return null;
        }

        Book book = new Book();
        book.setBookType(bookList.getBookType().getBookType());
        //书名字
        String bookName = document.select("#wrapper span[property=v:itemreviewed]").text();

        book.setBookName(bookName.trim());

        //图片路径
        String imageUrl = document.select("#mainpic .nbg").attr("href");
        book.setImage(imageUrl.trim());


        //豆瓣评分

        Elements douBanStarEle = document.select("strong[property=v:average]");
        if (douBanStarEle != null && douBanStarEle.size() > 0){
            String douBanStar = douBanStarEle.get(0).text();
            book.setDoubanStar(douBanStar.trim());
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

        //目录介绍
        String id = "#dir_" + bookId + "_full";
        String bookCatalogue = document.select(id).text();
        book.setBookCatalogue(bookCatalogue.trim());


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
                    price = price.replace("元","");
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
}
