package com.example.gqflib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by 郭青枫 on 2018/8/12.
 */

public class Test {
    public static void main(String[] args) {

        // double i=Math.pow(1920, 2);
        // double b=Math.pow(1200, 2);
        // double c=Math.sqrt(i+b);
        // System.out.println(c/8.0+"");
        generateXmlFile(1080,1920);

    }

    private static void generateXmlFile(int wid, int hei) {
        StringBuffer bufferLdpi = new StringBuffer();
        StringBuffer bufferMdpi = new StringBuffer();
        StringBuffer bufferHdpi = new StringBuffer();
        StringBuffer bufferXhdpi = new StringBuffer();
        StringBuffer bufferXxhdpi = new StringBuffer();
        StringBuffer bufferXxxhdpi = new StringBuffer();
        appenContent(bufferLdpi, wid, hei, 0.75f);
        appenContent(bufferMdpi, wid, hei, 1f);
        appenContent(bufferHdpi, wid, hei, 1.5f);
        appenContent(bufferXhdpi, wid, hei, 2f);
        appenContent(bufferXxhdpi, wid, hei, 3f);
        appenContent(bufferXxxhdpi, wid, hei, 4f);
        String path = "";
        if (hei != 0) {
            path = "-" + wid + "x" + hei;
        }

       printData(new File("F:\\values\\values-ldpi" + path), bufferLdpi);
        //printData(new File("ys"), bufferLdpi);
//        printData(new File("D:\\values\\values-mdpi" + path), bufferMdpi);
//        printData(new File("D:\\values\\values-hdpi" + path), bufferHdpi);
//        printData(new File("D:\\values\\values-xhdpi" + path), bufferXhdpi);
//        printData(new File("D:\\values\\values-xxhdpi" + path), bufferXxhdpi);
//        printData(new File("D:\\values\\values-xxxhdpi" + path), bufferXxxhdpi);

    }

    private static void appenContent(StringBuffer buffer, int wid, int hei,
                                     float density) {
        //float dimen=0.5625f;
        float dimen=0.5917f;//设计稿高度/1920
        //		Dpi :
        //
        //			计算直角边像素数量： 1920^2+1080^2=2202^2（勾股定理）。
        //			计算 DPI：2202 / 4.95 = 445。
        //			得到这个设备的 DPI 为 445 （每英寸的距离中有 445 个像素）。
        //			Density
        //
        //			上面得到每英寸中有 440 像素，那么 density 为每平方英寸中的像素数量，应该为： 445^2=198025。


        if(hei==0){
            dimen = 1f / density/dimen;
        }else{
            dimen = 1f / density * hei / 1136;//1136设计稿高度
        }
        buffer.append("<resources>\n");
        for (int i = 1; i < 501; i++) {
           // if(i<=50||(i>50&&i<=100&&i%5==0)||(i%10==0&&i>100)) {
//                buffer.append("<dimen name=\"trans_" + i + "px\">");
//                buffer.append(dimen * i + "dp");
//                buffer.append("</dimen>\n");
                                buffer.append("<dimen name=\"trans_" + i + "px\">");
                                buffer.append(i + "px");
                                buffer.append("</dimen>\n");
           // }
        }
        for (int i = 1; i < 201; i++) {
           // if(i<=50||(i>50&&i<=100&&i%5==0)||(i%10==0&&i>100)) {
//                buffer.append("<dimen name=\"text_trans_" + i + "px\">");
//                buffer.append(dimen * i + "sp");
//                buffer.append("</dimen>\n");
                buffer.append("<dimen name=\"text_trans_" + i + "px\">");
                buffer.append(i + "px");
                buffer.append("</dimen>\n");
            //}
        }
        buffer.append("</resources>");
    }

    private static void printData(File file, StringBuffer buffer) {
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(
                    file.getPath() + file.separator + "dimen.xml")));
            pw.print(buffer.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
