package sandbox.tables;

/**
 * Example written by Bruno Lowagie and Nishanthi Grashia in answer to the following question:
 * http://stackoverflow.com/questions/27871574/appears-space-between-cells-without-border-itextpdf
 */

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
给文本加上背景色
 */
public class ColoredBackground {
    public static final String DEST = "results/tables/colored_background.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColoredBackground().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table;
        PdfPCell cell;
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        table = new PdfPTable(16);
        for(int aw = 0; aw < 16; aw++){
            cell = new PdfPCell(new Phrase("hi", font));
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        document.add(table);
        document.close();
    }

}

