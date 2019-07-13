package com.quincy.crawler.douban.pojo;

/**
 * author:quincy
 * Date:2019-07-08
 */
public class Book {
    private Long bookId;//书id
    private String bookName;//书名
    private String bookAuthor;//作者
    private String bookOriginalName;//原作名
    private String bookPublish;//出版社
    private String bookPublishTime;//出版时间
    private String bookBind;//装订
    private String price;//原价
    private String isbn;//isbn序列号
    private String image;//图片
    private String pageNumber;//页数
    private String bookProducers;//出品方
    private String bookSeries;//丛书
    private String bookTranslator;//译者
    private String doubanStar;//豆瓣评分
    private String bookType;
    private String bookTypeName;

    private String bookContentInfo;//内容介绍
    private String bookAuthorInfo;//作者介绍
    private String bookCatalogue;//目录


    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getDoubanStar() {
        return doubanStar;
    }

    public void setDoubanStar(String doubanStar) {
        this.doubanStar = doubanStar;
    }

    public String getBookTranslator() {
        return bookTranslator;
    }

    public void setBookTranslator(String bookTranslator) {
        this.bookTranslator = bookTranslator;
    }

    public String getBookOriginalName() {
        return bookOriginalName;
    }

    public void setBookOriginalName(String bookOriginalName) {
        this.bookOriginalName = bookOriginalName;
    }

    public String getBookProducers() {
        return bookProducers;
    }

    public void setBookProducers(String bookProducers) {
        this.bookProducers = bookProducers;
    }

    public String getBookSeries() {
        return bookSeries;
    }

    public void setBookSeries(String bookSeries) {
        this.bookSeries = bookSeries;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish;
    }

    public String getBookPublishTime() {
        return bookPublishTime;
    }

    public void setBookPublishTime(String bookPublishTime) {
        this.bookPublishTime = bookPublishTime;
    }

    public String getBookBind() {
        return bookBind;
    }

    public void setBookBind(String bookBind) {
        this.bookBind = bookBind;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBookContentInfo() {
        return bookContentInfo;
    }

    public void setBookContentInfo(String bookContentInfo) {
        this.bookContentInfo = bookContentInfo;
    }

    public String getBookAuthorInfo() {
        return bookAuthorInfo;
    }

    public void setBookAuthorInfo(String bookAuthorInfo) {
        this.bookAuthorInfo = bookAuthorInfo;
    }

    public String getBookCatalogue() {
        return bookCatalogue;
    }

    public void setBookCatalogue(String bookCatalogue) {
        this.bookCatalogue = bookCatalogue;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookOriginalName='" + bookOriginalName + '\'' +
                ", bookPublish='" + bookPublish + '\'' +
                ", bookPublishTime='" + bookPublishTime + '\'' +
                ", bookBind='" + bookBind + '\'' +
                ", price='" + price + '\'' +
                ", isbn='" + isbn + '\'' +
                ", image='" + image + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", bookProducers='" + bookProducers + '\'' +
                ", bookSeries='" + bookSeries + '\'' +
                ", bookTranslator='" + bookTranslator + '\'' +
                ", doubanStar='" + doubanStar + '\'' +
                ", bookType='" + bookType + '\'' +
                ", bookContentInfo='" + bookContentInfo + '\'' +
                ", bookAuthorInfo='" + bookAuthorInfo + '\'' +
                ", bookCatalogue='" + bookCatalogue + '\'' +
                '}';
    }
}
