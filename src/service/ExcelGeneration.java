package service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import utility.ConnectionManager;

public class ExcelGeneration {
	public void excelGenerate(int id) throws ClassNotFoundException, SQLException, IOException {
		
		int roll_id = 0;	
		int year = 0;
		String address = null;
		String contact = null;
		String gender = null;
		String name = null;
		String branch = null;
		String dob = null;
		
		Connection con = null;
		Statement stmt = null;
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
	    stmt = con.createStatement();
	    String sqlget = "SELECT * FROM STUDENT";
	    ResultSet rs = stmt.executeQuery(sqlget);
	    
	    String[] col = {"ROLL_NO","NAME","DOB","ADDRESS","BRANCH","ACADEMIC_YEAR","GENDER","CONTACT"};
	    
	    //create blank workbook
 		@SuppressWarnings("resource")
		Workbook wb = new HSSFWorkbook(); 
 		
 		//create sheet
 		org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("Student"); 
 		
 		//creating Row
 		Row row_header = sheet.createRow(0);
 		
 		for(int i=0;i<col.length; i++) {
 			Cell cell = row_header.createCell(i);
 			cell.setCellValue(col[i]);
 			
 		}
 		int rowNumber=1;
	    while(rs.next()){
	    	roll_id  = rs.getInt("ROLL_NO");
	         name  = rs.getString("NAME");
	         dob = rs.getString("DOB");
	         address = rs.getString("ADDRESS");	         
	         branch  = rs.getString("BRANCH");
	         year  = rs.getInt("ACADEMIC_YEAR");
	         gender  = rs.getString("GENDER");
	         contact  = rs.getString("CONTACT");
	         
	     	
	 		
	 		
				Row row = sheet.createRow(rowNumber++);
			    row.createCell(0).setCellValue(roll_id);
			    row.createCell(1).setCellValue(name);
			    row.createCell(2).setCellValue(dob);
			    row.createCell(3).setCellValue(address);
			    row.createCell(4).setCellValue(branch);
			    row.createCell(5).setCellValue(year);
			    row.createCell(6).setCellValue(gender);
			    row.createCell(7).setCellValue(contact);
			
			
			
			OutputStream fileOut = new FileOutputStream("Student_Record.xls");
			
			wb.write(fileOut);
			fileOut.close();			         		 
	    }
	    System.out.println("Successfully Student Record has been Created.");
	}
}
