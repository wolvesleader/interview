package com.quincy.java.poi.html2pdf;

import com.aspose.words.Document;
import com.aspose.words.SaveOptions;

import java.io.File;
import java.net.URL;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: Html2Pdf
 * @author: quincy
 * @Date: 2021/6/8 下午6:57
 * @History:
 */

public class Html2Pdf {
    public static void main(String[] args) {
         Html2Pdf html2Pdf = new Html2Pdf();
         html2Pdf.html2PDF();
    }

    public void html2PDF() {
        try {
            URL resource = this.getClass().getClassLoader().getResource("com/quincy/java/poi/html2pdf");

            String path = resource.getPath();

            File file = new File(path,"宙斯开发者中心.html");
            Document document = new Document(file.getPath());

            document.save(path + "/宙斯开发者中心.pdf");

            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
