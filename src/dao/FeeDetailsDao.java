package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.DocumentException;

import controller.AccountantMenu;
import model.StudentPojo;
import utility.ConnectionManager;

public class FeeDetailsDao {
	AccountantMenu am = new AccountantMenu();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void addStudentFeeDetails(StudentPojo st) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int roll = st.getId();
		String name = st.getName();
		String branch = st.getBranch();
		int year = st.getYear();
		int fees = 40000;
		int paid = st.getPaidfees();
		int due = fees - paid ; 
		long millis = System.currentTimeMillis();
		Date Date_of_receipt = new Date(millis);
		  
		ConnectionManager cm =new ConnectionManager();
		
		String sql = "INSERT INTO FEES (ROLL_NO, NAME, BRANCH, ACADEMIC_YEAR, FEES, PAID, DUE, DATE_OF_RECEIPT) VALUES(?,?,?,?,?,?,?,?)";
		
		//CREATE PREPARED STATEMENT
		PreparedStatement stmt = cm.getConnection().prepareStatement(sql);
		stmt.setInt(1,roll);
		stmt.setString(2,name);
		stmt.setString(3,branch);
		stmt.setInt(4,year);
		stmt.setInt(5,fees);
		stmt.setInt(6,paid);
		stmt.setInt(7,due);
		stmt.setDate(8,Date_of_receipt);
		
		System.out.println("\n Sucessfully Student Fee Details has been Stored.\n");
		stmt.executeUpdate();
		cm.getConnection().close();
	}

	public void viewFeeDetails() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
		//Execute a query
	    //System.out.println("Creating statement...");
	    stmt = con.createStatement();
	    
	    String sql = "SELECT * FROM FEES ORDER BY ROLL_NO";
	    ResultSet rs = stmt.executeQuery(sql);
	    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
	    System.out.printf("%-3s %-10s %-3s %-7s %-3s %-12s %-3s %-12s %-3s %-8s %-3s %-8s %-3s %-8s %-3s %-8s %-3s %-12s %s","|","Receipt_ID","|","Roll_no","|","Name","|","Branch","|","Year","|","Fees","|","Paid","|","Due","|","Date","|");
	    //System.out.println("| Receipt_ID | Roll_no |    Name    | Branch | Year | Fees  | Paid  |  Due |    Date    |");
	    System.out.print("\n");
	    // Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("RECEIPT_ID");
	         int roll  = rs.getInt("ROLL_NO");
	         String name = rs.getString("NAME");
	         String branch = rs.getString("BRANCH");	         
	         int year  = rs.getInt("ACADEMIC_YEAR");
	         int fees  = rs.getInt("FEES");
	         int paid  = rs.getInt("PAID");
	         int due  = rs.getInt("DUE");
	         Date date = rs.getDate("DATE_OF_RECEIPT");
	         String s_date = date.toString();

	         //Display values
	         try {
	        	 System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
	         System.out.printf("%-3s %-10s %-3s %-7s %-3s %-12s %-3s %-12s %-3s %-8s %-3s %-8s %-3s %-8s %-3s %-8s %-3s %-12s %s","|",id,"|",roll,"|",name,"|",branch,"|",year,"|",fees,"|",paid,"|",due,"|",s_date,"|");
	         //System.out.println("|    "+id+"     | "+roll+"   |    "+name+"    | "+branch+" | "+year+" | "+fees+"  | "+paid+"  |  "+due+" |    "+date+" |");
	         System.out.print("\n");
	         }
	         catch(Exception e) {
	        	 System.out.print("\n");
	         }
	      }
	      System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
	      rs.close();

	}

	public void updateFeeDetails(int id) throws SQLException, ClassNotFoundException, NumberFormatException, IOException, DocumentException {
		// TODO Auto-generated method stub
		int fees = 0;
		int paid = 0;
		int dues = 0;
		int r_id = 0;
		Connection con = null;
		Statement stmt = null;
		
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
	    stmt = con.createStatement();
	    String sqlget = "SELECT * FROM FEES ORDER BY ROLL_NO";
	    ResultSet rs = stmt.executeQuery(sqlget);
	    
	    while(rs.next()){
	    	 fees = rs.getInt("FEES");
	    	 dues = rs.getInt("DUE");
	    	 paid  = rs.getInt("PAID");
	    	 r_id  = rs.getInt("ROLL_NO");
	    	if(id == r_id) {
	    		System.out.println("Fees is : "+fees);
	    		System.out.println("Paid amount is : "+paid);
	    		System.out.println("Due is : "+dues);
	    		break;
	    	}	 
	    }
	    if(id !=r_id) {
	    	System.out.println("Student Roll_no. does not match....!");
	    	am.accountantSection();
	    }
	    System.out.println("Enter Due Amount : ");
	    int due = Integer.parseInt(br.readLine());
	    int up_due = dues-due;
	    paid = fees - up_due;
	    if(paid>40000) {
	    	System.out.println("Paid Amount cannot be more than total fees..!");
	    	am.accountantSection();
	    }
	    
	    String sqlupdate = "update FEES set DUE=? , PAID=? where ROLL_NO=?  ";
	    
	    PreparedStatement stm = con.prepareStatement(sqlupdate); 
	        stm.setInt(1, up_due);
	        stm.setInt(2, paid);
	        stm.setInt(3, id);
	        stm.executeUpdate();
	        
	        System.out.println("Database updated successfully ");
	        cm.getConnection().close();
	}
	
	public void deleteStudentFeeDtails(int id) throws ClassNotFoundException, SQLException, NumberFormatException, IOException, DocumentException {
		// TODO Auto-generated method stub
		int roll = 0;
		Connection con = null;
		Statement stmt = null;
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
		  stmt = con.createStatement();
		    String sqlget = "SELECT * FROM FEES ORDER BY ROLL_NO";
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
		    
		    String sqlDelStuDetail = "delete from FEES where  ROLL_NO=? ";
		    
		    
		    PreparedStatement stm = con.prepareStatement(sqlDelStuDetail);
		    stm.setInt(1,roll);
		    stm.executeUpdate();
	        cm.getConnection().close();
	}

}
