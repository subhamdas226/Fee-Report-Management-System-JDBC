package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

import controller.AccountantMenu;
import model.StudentPojo;
import service.ExcelGeneration;
import utility.ConnectionManager;

public class StudentDetailsDao {
	ArrayList<StudentPojo> sp = new ArrayList<StudentPojo>();
	FeeDetailsDao fdd = new FeeDetailsDao();
	ExcelGeneration exc = new ExcelGeneration();
	AccountantMenu am = new AccountantMenu();
	public void addStudent(StudentPojo st) throws ClassNotFoundException, SQLException, NumberFormatException, IOException, DocumentException {
		// TODO Auto-generated method stub
		int id = st.getId();
		String name = st.getName();
		String dob = st.getDob();
		String address = st.getAddress();
		String branch = st.getBranch();
		int year = st.getYear();
		String gender = st.getGender();
		String contact = st.getContact();
		
		ConnectionManager cm = new ConnectionManager();
		
		String sql = "insert into Student (ROLL_NO,NAME,DOB,ADDRESS,BRANCH,ACADEMIC_YEAR,GENDER,CONTACT)VALUES(?,?,?,?,?,?,?,?)";
		
		//CREATE STATEMENT OBJECT
			PreparedStatement stmt = cm.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, dob);
			stmt.setString(4, address);
			stmt.setString(5, branch);
			stmt.setInt(6, year);
			stmt.setString(7, gender);
			stmt.setString(8, contact);
			
			
			try {
				stmt.executeUpdate();
				
				exc.excelGenerate(id);
				System.out.println("\n Sucessfully Student personal Details has been Stored.\n");
				cm.getConnection().close();
			}
			catch(Exception e) {
				System.out.println("\n Unique Constraint Violated. Same Roll Number cannot be alloted to Different Students.\n");
				am.accountantSection();
			}
		
	}

	public void viewStudent() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
		//Execute a query
	    //System.out.println("Creating statement...");
	    stmt = con.createStatement();
	    
	    String sql = "SELECT * FROM STUDENT ORDER BY ROLL_NO ";
	    ResultSet rs = stmt.executeQuery(sql);
	    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	   // System.out.println("| Roll_no |     Name     |    DOB    |  Address  | Branch |  Year   |  Gender |    Contact  |");
	    System.out.printf("%-3s %-4s %-3s %-12s %-3s %-10s %-3s %-10s %-3s %-12s %-3s %-8s %-3s %-8s %-3s %-16s %s","|","Roll_no","|","Name","|","DOB","|","Address","|","Branch","|","YEAR","|","Gender","|","Contact","|");
	    System.out.print("\n");
	    // Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("ROLL_NO");
	         String name  = rs.getString("NAME");
	         String dob = rs.getString("DOB");
	         String address = rs.getString("ADDRESS");	         
	         String branch  = rs.getString("BRANCH");
	         int year  = rs.getInt("ACADEMIC_YEAR");
	         String gender  = rs.getString("GENDER");
	         String contact  = rs.getString("CONTACT");
	        
	        

	         //Display values
	         try {
	        	 System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	         //System.out.println("|  "+id+"    |   "+name+"     |   "+dob+"   |   "+address+"   |  "+branch+"  | "+year+"   | "+gender+"  |  "+contact+" |    ");
	        	 System.out.printf("%-3s %-7d %-3s %-12s %-3s %-10s %-3s %-10s %-3s %-12s %-3s %-8d %-3s %-8s %-3s %-16s %s","|",id,"|",name,"|",dob,"|",address,"|",branch,"|",year,"|",gender,"|",contact,"|");
	        	 System.out.print("\n");
	         }
	         catch(Exception e) {
	        	 System.out.print("\n");
	         }
	      }
	      	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	      rs.close();
	}

	public void deleteStudent(int id) throws ClassNotFoundException, SQLException, NumberFormatException, IOException, DocumentException {
		// TODO Auto-generated method stub
		int roll = 0;
		Connection con = null;
		Statement stmt = null;
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
		  stmt = con.createStatement();
		    String sqlget = "SELECT * FROM STUDENT ORDER BY ROLL_NO";
		    ResultSet rs = stmt.executeQuery(sqlget);
		    
		    while(rs.next()){
		    	 roll = rs.getInt("ROLL_NO");
		    	 if(id == roll) {
		    		 break;
		    	 } 
		    }
		    if(id !=roll) {
		    	System.out.println("Student Roll_no. does not match....!");
		    	am.accountantSection();
		    }
		    
		    fdd.deleteStudentFeeDtails(id);
		    String sqlDelStuDetail = "delete from STUDENT where  ROLL_NO=? ";
		    
		    
		    PreparedStatement stm = con.prepareStatement(sqlDelStuDetail);
		    stm.setInt(1,roll);
		    stm.executeUpdate();
		    exc.excelGenerate(id);
	        System.out.println("Database updated successfully ");
	        cm.getConnection().close();
	}

	

}
