package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.AccountantDetailsDao;
import dao.ViewAccountantDetailsDao;
import model.AccountantPojo;
import utility.Validation;

public class AdminMenu {
	AccountantDetailsDao acdd = new AccountantDetailsDao();
	Validation val = new  Validation();
	public void adminSection() throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AccountantPojo adacc = new AccountantPojo();
		while(true) {
			System.out.println("------ADMIN SECTION------");
			System.out.println(" 1: Add Accountant ");
			System.out.println(" 2: view Accountant ");
			System.out.println(" 3: Delete Accountant ");
			System.out.println(" 4: Logout ");
			int opt = 0;
			try {
				opt = Integer.parseInt(br.readLine());
			}
			catch(Exception e) {
				System.out.println(" \nSelect from above given respective inputs.\n");
				continue;
			}
			if(opt == 1) {
				System.out.println("------ADD ACCOUNTANT-------");
				while(true) {
					System.out.println(" Enter Accountant id : ");
					int id = 0;
					try {
						id = Integer.parseInt(br.readLine());
					}
					catch(Exception e) {
						System.out.println("You have entered wrong input only numbers will allow.");
						continue;		
					}
					adacc.setId(id);
					break;
				}
				while(true) {
					System.out.println(" Enter Accountant Name : ");
					String name = br.readLine();
					boolean check = val.name(name);
					if(check == true) {
						adacc.setName(name);
						break;
					}
					else {
						System.out.println("You have entered wrong input only Alphabets will allow.");
						continue;
					}
					
				}
				while(true) {
					System.out.println(" Enter Accountant password : ");
					String password = br.readLine();
					boolean check = val.pass(password);
					if(check == true) {
						adacc.setPassword(password);
						break;
						}
					else {
						System.out.println("It should contains at least 5 characters and at most 20 characters.");
						System.out.println("It should contains at least one digit..");
						System.out.println("It should contains at least one upper case alphabet.");
						System.out.println("It should contains at least one lower case alphabet.");
						System.out.println("It should contains at least one special character which includes !@#$%&*()-+=^.");
						System.out.println("It shouldn’t contain any white space.");
					    
						continue;
						}				
					}
				
				while(true) {
					System.out.println(" Enter Accountant Email(ex:example@gmail.com) : ");
					String email = br.readLine();
					boolean check = val.email(email);
					if(check == true) {
						adacc.setEmail(email);
						break;
						}
					else {
						System.out.println(" Invalid input ");
						continue;
						}				
					}
				while(true) {
					System.out.println(" Enter Accountant contact(ex: +91 (789){1} (947982384) {9}) : ");
					String contact = br.readLine();
					boolean check = val.contact(contact);
					if(check == true) {
						adacc.setContact(contact);
						break;
						}
					else {
						System.out.println(" Invalid input, see example ");
						continue;
						}				
					}
				
				
				acdd.addAccountant(adacc);
				continue;
			}
			else if(opt ==2) {
				ViewAccountantDetailsDao vad = new ViewAccountantDetailsDao();
				vad.viewAccountant();
			}
			else if(opt==3){
				System.out.println("\n---------Delete Accountant Details----------\n ");
				System.out.println(" Enter Accountant ID : ");
				int id = Integer.parseInt(br.readLine());
				acdd.deleteAccountant(id);
			}
			else if(opt==4){
				System.out.println("\n\t.....Successfully Admin Logging Out......\n");
				break;
			}
			else {
				System.out.println("\n you have entered a wrong input \n");
			}
		}
	}

}
