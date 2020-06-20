package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.AccountantPojo;
import utility.ConnectionManager;

public class AccountantLoginDao {
	//AccountantPojo ap = new AccountantPojo();
	String email = AccountantPojo.getEmail();
	String password = AccountantPojo.getPassword();

	public boolean validate() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ConnectionManager con = new ConnectionManager();
		Statement st = con.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM Accountant");
		
		int flag = 0;
		
		while(rs.next()) {
			if(email.equals(rs.getString("EMAIL")) && password.equals(rs.getString("PASSWORD"))) {
				con.getConnection().close();
				flag = 1;
				break;
			}
			else {
				con.getConnection().close();
				flag=0;
				}
			}
		if(flag ==1) {
			return true;
			}
		else {
			return false;
			}
	}

}
