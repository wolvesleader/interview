package com.quincy.java.poi.word2pdf;

import com.aspose.words.Document;

import java.io.File;
import java.net.URL;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: Word2Pdf
 * @author: quincy
 * @Date: 2021/6/8 下午6:36
 * @History:
 */

public class Word2Pdf {
    public static void main(String[] args) {

        Word2Pdf word2Pdf = new Word2Pdf();
        word2Pdf.word2PDF();

    }

    public void word2PDF() {
        try {
            URL resource = this.getClass().getClassLoader().getResource("com/quincy/java/poi/word2pdf");

            String path = resource.getPath();

            File file = new File(path,"实战面试题.docx");
            Document document = new Document(file.getPath());
            document.save(path + "/实战面试题.pdf");

            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
