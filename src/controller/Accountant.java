package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.AccountantLoginDao;
import model.AccountantPojo;


public class Accountant {

	public boolean accountant() throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AccountantPojo ap = new AccountantPojo();
		
		System.out.println("---Enter Accountant Credentials--- :");
		System.out.println(" Enter Accountant Email :");
		String email = br.readLine();
		ap.setEmail(email);
		
		System.out.println(" Enter Accountant Password :");
		String password = br.readLine();
		ap.setPassword(password);
		
		AccountantLoginDao val = new AccountantLoginDao();
		boolean chk_val = val.validate();
		
		if(chk_val == true) {
			return true;
		}
		else {
			return false;
		}

	}

}
