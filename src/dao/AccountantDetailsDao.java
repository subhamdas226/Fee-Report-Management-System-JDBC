package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import controller.AdminMenu;
import model.*;
import utility.ConnectionManager;

public class AccountantDetailsDao {
	
	public void addAccountant(AccountantPojo addacc) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int id = addacc.getId();
		String name = addacc.getName();
		String password  = AccountantPojo.getPassword();
		String email = AccountantPojo.getEmail();
		String contact = addacc.getContact();
		
		/*System.out.println(id);
		System.out.println(name);
		System.out.println(password);
		System.out.println(email);
		System.out.println(contact); */
		ConnectionManager cm = new ConnectionManager();
		
		//insert all details into the database
		String sql = "insert into Accountant (ID,NAME,PASSWORD,EMAIL,CONTACT)VALUES(?,?,?,?,?)";
		
		//CREATE STATEMENT OBJECT
		PreparedStatement st = cm.getConnection().prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2, name);
		st.setString(3, password);
		st.setString(4, email);
		st.setString(5, contact);
		
		AdminMenu am =new AdminMenu();
		try {
			st.executeUpdate();
			System.out.println("\n Sucessfully Accountant Details has been Stored.\n");
			cm.getConnection().close();
		}
		catch(Exception e) {
			System.out.println("\n Unique Constraint Violated. Same ID cannot be alloted to Different Accountant.\n");
			am.adminSection();
		}
		
		
	}
	public void deleteAccountant(int id) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		int roll = 0;
		Connection con = null;
		Statement stmt = null;
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
		
		  stmt = con.createStatement();
		    String sqlget = "SELECT * FROM Accountant";
		    ResultSet rs = stmt.executeQuery(sqlget);
		    
		    while(rs.next()){
		    	 roll = rs.getInt("ID");
		    	 if(id == roll) {
		    		 break;
		    	 } 
		    }
		    if(id !=roll) {
		    	System.out.println("Accountant ID does not match....!");
		    	cm.getConnection().close();
		    	
		    }
		    else {
		    	String sqlDelStuDetail = "delete from Accountant where  ID=? ";

			    PreparedStatement stm = con.prepareStatement(sqlDelStuDetail);
			    stm.setInt(1,roll);
			    stm.executeUpdate();
		        System.out.println("Database updated successfully ");
		        cm.getConnection().close();
		    }
		    
	}

}
