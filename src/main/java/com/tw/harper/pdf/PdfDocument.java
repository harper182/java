package com.tw.harper.pdf;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hbowang on 7/12/16.
 */
public class PdfDocument {
    public static void main(String[] args) throws IOException {
        InputStream input = null;
        File pdfFile = new File("/Users/hbowang/git/lkms/test.pdf");
        PDDocument document = null;
        try {
            input = new FileInputStream(pdfFile);
            //加载 pdf 文档
            document = PDDocument.load(input);

            /** 文档属性信息 **/
            PDDocumentInformation info = document.getDocumentInformation();
            System.out.println("标题:" + info.getTitle());
            System.out.println("主题:" + info.getSubject());
            System.out.println("作者:" + info.getAuthor());
            System.out.println("关键字:" + info.getKeywords());

            System.out.println("应用程序:" + info.getCreator());
            System.out.println("pdf 制作程序:" + info.getProducer());

            System.out.println("作者:" + info.getTrapped());


            /** 文档页面信息 **/
            PDDocumentCatalog cata = document.getDocumentCatalog();
            List pages = cata.getAllPages();
            int count = 1;
            for (int i = 0; i < pages.size(); i++) {
                PDPage page = (PDPage) pages.get(i);
                if (null != page) {
                    PDResources res = page.findResources();

                    //获取页面图片信息
                    Map imgs = res.getImages();
                    if (null != imgs) {
                        Set keySet = imgs.keySet();
                        Iterator it = keySet.iterator();
                        while (it.hasNext()) {
                            Object obj = it.next();
                            PDXObjectImage img = (PDXObjectImage) imgs.get(obj);
                            img.write2file("tests" + count);
                            count++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != input)
                input.close();
            if (null != document)
                document.close();
        }
    }
}
