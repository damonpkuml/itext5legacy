/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/23375618/how-to-add-an-icon-to-an-itext-pdfpcell
 */
package sandbox.tables;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IconDescriptionTable {
    public static final String DEST = "results/tables/icon_description.pdf";
    public static final String IMG = "src/main/resources/images/bulb.gif";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new IconDescriptionTable().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{ 1, 9 });
        Image img = Image.getInstance(IMG);
        table.addCell(new PdfPCell(img, true));
        table.addCell(new Phrase("A light bulb icon"));table.completeRow();

        document.add(table);
        document.close();
    }
}