package dao;

import java.sql.*;

import model.AdminPojo;
import utility.ConnectionManager;

public class AdminLoginDao {
	String email = AdminPojo.getEmail();
	String password = AdminPojo.getPassword();

	
	public boolean validate() throws SQLException, ClassNotFoundException {
	ConnectionManager con = new ConnectionManager();
	Statement st = con.getConnection().createStatement();
	
	ResultSet rs = st.executeQuery("SELECT * FROM ADMIN");
	
	int flag = 0;
	while(rs.next()) {
		if(this.email.equals(rs.getString("EMAIL")) && this.password.equals(rs.getString("PASSWORD"))) {
			con.getConnection().close();
			flag = 1;
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
