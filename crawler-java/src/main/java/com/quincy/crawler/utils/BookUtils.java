package com.quincy.crawler.utils;

import com.quincy.crawler.douban.pojo.Book;
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
    public static Book parseBookDetailToBean(Document document, String bookId){

        if (document == null) {
            return null;
        }

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
