package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controller.AccountantMenu;
import utility.ConnectionManager;

public class ExportPdf {
	AccountantMenu am = new AccountantMenu();
	public void ExportPdf(int id) throws ClassNotFoundException, SQLException, NumberFormatException, IOException, DocumentException {
		// TODO Auto-generated method stub
		System.out.println("working");
		int fees = 0;
		int paid = 0;
		int dues = 0;
		int roll_id = 0;
		int rec_id = 0;
		int year = 0;
		String name = null;
		String branch = null;
		Date date = null;
		Connection con = null;
		Statement stmt = null;
		
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
	    stmt = con.createStatement();
	    String sqlget = "SELECT * FROM FEES";
	    ResultSet rs = stmt.executeQuery(sqlget);
	    
	    while(rs.next()){
	    	year = rs.getInt("ACADEMIC_YEAR");
	    	 rec_id= rs.getInt("RECEIPT_ID");
	    	 name =  rs.getString("NAME");
	    	 branch =  rs.getString("BRANCH");
	    	 fees = rs.getInt("FEES");
	    	 dues = rs.getInt("DUE");
	    	 paid  = rs.getInt("PAID");
	    	 roll_id  = rs.getInt("ROLL_NO");
	    	 date = rs.getDate("DATE_OF_RECEIPT");
	    	if(id == roll_id) {
	    		System.out.println("Fee Report Generated Successfully!");
	    		
	    		break;
	    	}	 
	    }
	    String rec = Integer.toString(rec_id);
	    String y = Integer.toString(year);
	    String fee = Integer.toString(fees);
	    String due = Integer.toString(dues);
	    String pay = Integer.toString(paid);
	    String roll = Integer.toString(roll_id);
	    String s_date = date.toString();
	    if(id !=roll_id) {
	    	System.out.println("Student Roll_no. does not match....!");
	    	am.accountantSection();
	    }
	
	    Document document = new Document();

        try {
        	
        	
            PdfWriter.getInstance(document,
                new FileOutputStream("Student_Fee_Report.pdf"));
            
            document.open();
            Font font1 = new Font(Font.FontFamily.HELVETICA  , 25, Font.BOLD);
            Image image = Image.getInstance("NSUT_logo.png");
           // image.scaleAbsolute(50f, 80f);//Scale image's width and height
            
            image.scaleToFit(150, 250);
            
            
            float[] columnWidths = {5f, 10f};
            PdfPTable table = new PdfPTable(columnWidths); // 2 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Receipt_ID"));
            PdfPCell cell2 = new PdfPCell(new Paragraph(rec));
            PdfPCell cell3 = new PdfPCell(new Paragraph("ROLL_NO"));
            PdfPCell cell4 = new PdfPCell(new Paragraph(roll));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Name"));
            PdfPCell cell6 = new PdfPCell(new Paragraph(name));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Branch"));
            PdfPCell cell8 = new PdfPCell(new Paragraph(branch));
            PdfPCell cell9 = new PdfPCell(new Paragraph("Academic Year"));
            PdfPCell cell10 = new PdfPCell(new Paragraph(y));
            PdfPCell cell11= new PdfPCell(new Paragraph("Fees"));
            PdfPCell cell12 = new PdfPCell(new Paragraph(fee));
            PdfPCell cell13 = new PdfPCell(new Paragraph("Paid"));
            PdfPCell cell14 = new PdfPCell(new Paragraph(pay));
            PdfPCell cell15 = new PdfPCell(new Paragraph("Due"));
            PdfPCell cell16 = new PdfPCell(new Paragraph(due));
            PdfPCell cell17 = new PdfPCell(new Paragraph("Date Of Receipt"));
            PdfPCell cell18 = new PdfPCell(new Paragraph(s_date));
            
            table.setSpacingBefore(10f);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            table.addCell(cell9);
            table.addCell(cell10);
            table.addCell(cell11);
            table.addCell(cell12);
            table.addCell(cell13);
            table.addCell(cell14);
            table.addCell(cell15);
            table.addCell(cell16);
            table.addCell(cell17);
            table.addCell(cell18);
            
            //table.setSpacingBefore(10f);
            document.add(image);
            Paragraph pg = new Paragraph(" Student Fee Report ", font1);
            pg.setAlignment(Element.ALIGN_CENTER);
            document.add(pg);
            //document.add(new Paragraph(" Student Fee Report ", font1));
            document.add(table);

            document.close();
        } catch(Exception e){

        }
	}
}
