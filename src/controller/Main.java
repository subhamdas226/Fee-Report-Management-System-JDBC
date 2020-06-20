package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException, DocumentException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("Login as a :");
			System.out.println(" 1: Admin ");
			System.out.println(" 2: Accountant ");
			System.out.println(" 3: Application Close ");
			int opt =0;
			try {
				opt = Integer.parseInt(br.readLine());
			
			}
			catch(Exception e) {
				System.out.println(" Not a valid number Input.\n");
				continue;
			}
			
			if(opt == 1) {
				System.out.println("\tAdmin\n");
				Admin adm = new Admin();
				boolean chk = adm.admin();
				if(chk == true) {
					System.out.println("\t\t....Sucessfully Admin Login....\n");
					AdminMenu am = new AdminMenu();
					am.adminSection();
				}
				else {
					System.out.println("wrong admin credentials");
					continue;
				}
			}
			
			else if(opt == 2) {
				System.out.println("\tAccountant\n");
				Accountant acc = new Accountant();
				boolean chk = acc.accountant();
				if(chk == true) {
					System.out.println("\t\t....Sucessfully Accountant Login....\n");
					AccountantMenu acm = new AccountantMenu();
					acm.accountantSection();
				}
				else {
					System.out.println("\n...Wrong accountant credentials...\n");
					continue;
				}
			}
			
			else if(opt == 3) {
				System.out.println(" Application terminated ...");
				break;
			}
			else {
				System.out.println(" Select from above given respective inputs.\n");
				continue;
			}
		}
	}

}
