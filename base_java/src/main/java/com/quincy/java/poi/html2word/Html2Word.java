package com.quincy.java.poi.html2word;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.aspose.words.SaveOutputParameters;

import java.io.File;
import java.net.URL;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: Html2Word
 * @author: quincy
 * @Date: 2021/6/8 下午6:57
 * @History:
 */

public class Html2Word {
    public static void main(String[] args) {

        Html2Word html2Word = new Html2Word();
        html2Word.html2Word();
    }

    public void html2Word() {
        try {
            URL resource = this.getClass().getClassLoader().getResource("com/quincy/java/poi/html2pdf");

            String path = resource.getPath();

            File file = new File(path,"宙斯开发者中心.html");
            Document document = new Document(file.getPath());

            SaveOutputParameters save = document.save(path + "/宙斯开发者中心.docx", SaveFormat.DOCX);



            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
