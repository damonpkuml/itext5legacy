/**
 * Example written by Bruno Lowagie and Nishanthi Grashia in answer to the following question:
 * http://stackoverflow.com/questions/28073190/itext-maintain-identing-if-paragraph-takes-new-line-in-pdfpcell
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleTable4 {
    public static final String DEST = "results/tables/simple_table4.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable4().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        Paragraph wrong = new Paragraph("This is wrong, because an object that was originally a paragraph is reduced to a phrase due to the fact that it's put into a cell that uses text mode.");
        //1. 文本模式下(直接构造函数传入paragraph)，再对paragraph不起作用
        wrong.setIndentationLeft(20);
        wrong.setFirstLineIndent(10);
        wrong.setLeading(20);
        PdfPCell wrongCell = new PdfPCell(wrong);
        //有效
        wrongCell.setPadding(0);
        table.addCell(wrongCell);
        Paragraph right = new Paragraph("This is right, because we create a paragraph with an indentation to the left and as we are adding the paragraph in composite mode, all the properties of the paragraph are preserved.");
//        Paragraph right = new Paragraph("hello itextpdf");
        //2 组合模式（addElement）下才有用，可以设置paragraph样式，不能从层面控制内容
        right.setIndentationLeft(20);
        right.setFirstLineIndent(10);
        right.setLeading(20);
        right.setAlignment(Element.ALIGN_CENTER);
        PdfPCell rightCell = new PdfPCell();
        //下面无效，组合模式
        rightCell.addElement(right);
        rightCell.setPadding(0);
        table.addCell(rightCell);
        document.add(table);
        document.close();
    }

}