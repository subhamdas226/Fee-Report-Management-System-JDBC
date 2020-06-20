package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.AdminLoginDao;
import model.AdminPojo;

public class Admin {

	public boolean admin() throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AdminPojo ap = new AdminPojo();
		
		System.out.println("---Enter Admin Credentials---");
		System.out.println(" Enter Admin Email :");
		String email = br.readLine();
		ap.setEmail(email);
		
		System.out.println(" Enter Admin Password :");
		String password = br.readLine();
		ap.setPassword(password);
		
		AdminLoginDao val = new AdminLoginDao();
		boolean chk_val = val.validate();
		
		if(chk_val == true) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
