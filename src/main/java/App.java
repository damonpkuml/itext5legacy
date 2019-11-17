import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class App {

    public static void main(String[] args) throws IOException, DocumentException {

        System.out.println("damon");
        //最基础测试
//        createStartPdf("start.pdf");
        createStartPdf("results/App.pdf");
        //测试中文字体

//        createFontPdf("测试中文.pdf");

//        将多个pdf文件打包成zip文件
//        createZipPdf("多个pdf打包.zip");

        System.out.println("OK！");

    }

    private static void createStartPdf(String des) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(des));
        document.open();
        float first=pdfWriter.getVerticalPosition(false);   //可以获取此刻在页面的垂直位置
        document.add(new Paragraph("Hello world itext5"));
        float second=pdfWriter.getVerticalPosition(false);
        document.close();
    }

    //中文字体
    private static void createFontPdf(String des) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(des));
        pdfWriter.setCompressionLevel(0);
        document.open();

        //注意java中相对位置
        BaseFont songFont = BaseFont.createFont("src/main/resources/fonts/宋体.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font chinese = new Font(songFont, 16);

        document.add(new Paragraph(new Chunk("pdf文档", chinese)));
        document.close();

    }

    //
    private static void createZipPdf(String des) throws IOException, DocumentException {
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(des));
        for (int i = 0; i < 3; i++) {
            ZipEntry entry = new ZipEntry("hello_" + i + ".pdf");
            zip.putNextEntry(entry);

            Document document = new Document();

            PdfWriter writer = PdfWriter.getInstance(document, zip);
            writer.setCloseStream(false);
            document.open();
            document.add(new Paragraph("Hello " + i));
            document.close();

            zip.closeEntry();
        }
        zip.close();


    }
}

