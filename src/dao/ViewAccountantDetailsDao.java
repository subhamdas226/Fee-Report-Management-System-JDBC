package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;

public class ViewAccountantDetailsDao {

	public void viewAccountant() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
		//Execute a query
	    //System.out.println("Creating statement...");
	    stmt = con.createStatement();
	    
	    String sql = "SELECT * FROM ACCOUNTANT";
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    System.out.println("----------------------------------------------------------------------------------");
	   // System.out.println("|    ID     |     Name     |    PASSWORD    |        EMAIL        |   CONTACT    |");
	    System.out.printf("%-3s %-4s %-3s %-12s %-3s %-10s %-3s %-17s %-3s %-12s %s","|","ID","|","Name","|","PASSWORD","|","EMAIL","|","CONTACT","|");
	    System.out.print("\n");
	    // Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("ID");
	         String name = rs.getString("NAME");
	         String pass = rs.getString("PASSWORD");
	         String email = rs.getString("EMAIL");
	         String contact = rs.getString("CONTACT");

	         //Display values
	         System.out.println("-----------------------------------------------------------------------------------");
	        // System.out.println("|    "+id+"    |     "+name+"     |      "+pass+"     |  "+email+"   |  "+contact+"   | ");
	         System.out.printf("%-3s %-4d %-3s %-12s %-3s %-10s %-3s %-17s %-3s %-12s %s","|",id,"|",name,"|",pass,"|",email,"|",contact,"|");
	         System.out.print("\n");
	        /* 
	         System.out.print("ID: " + id);
	         System.out.print(", name: " + name);
	         System.out.print(", Password: " + pass);
	         System.out.println(", Email: " + email);
	         System.out.println(", Contact: " + contact);  */
	      }
	      System.out.println("------------------------------------------------------------------------------------\n");
	      rs.close();
	}

}
