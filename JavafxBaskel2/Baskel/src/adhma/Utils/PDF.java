/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhma.Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author 21692
 */
public class PDF {
    
    public void sendPDF () throws FileNotFoundException, DocumentException
    
    {
        String file_name = "E:\\generate_pdf\\facture2.pdf";
       // String path = file.getAbsolute
       Document document= new Document();
       
       PdfWriter.getInstance(document, new FileOutputStream(file_name));
       
       document.open();
       
       
       //paragraohe content
       Paragraph para= new Paragraph("TEST TES TEST SIKE");
       
       document.add(para);
       
       document.close();
    }
    
}
