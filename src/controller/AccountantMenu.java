package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.itextpdf.text.DocumentException;

import dao.FeeDetailsDao;
import dao.StudentDetailsDao;
import model.StudentPojo;
import service.ExportPdf;
import utility.Validation;

public class AccountantMenu {

	public void accountantSection() throws NumberFormatException, IOException, ClassNotFoundException, SQLException, DocumentException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StudentDetailsDao std = new StudentDetailsDao();
		FeeDetailsDao fdd = new FeeDetailsDao();
		Validation val = new  Validation();
		StudentPojo st = new StudentPojo();
		
		while(true) {
			System.out.println("------ACCOUNTANT SECTION------");
			System.out.println(" 1: Add Student ");
			System.out.println(" 2: view Student ");
			System.out.println(" 3: Delete Student ");
			System.out.println(" 4: Update Student ");
			System.out.println(" 5: Export Student Fee Report pdf");
			System.out.println(" 6: Logout ");
			int opt = 0;
			try {
				opt = Integer.parseInt(br.readLine());
			}
			catch(Exception e) {
				System.out.println(" \nSelect from above given respective inputs.\n");
				continue;
			}
			//------------------ADD STUDENT-----------------------------------------------------
			if(opt == 1) {
				System.out.println("------ADD STUDENT-------");
				
				while(true) {
					System.out.println(" Enter Student Roll NO. : ");
					int id = 0;
					try {
					 id = Integer.parseInt(br.readLine());
					}
					catch(Exception e) {
						System.out.println("You have entered wrong input only numbers will allow.");
						continue;
						
					}
						st.setId(id);
						break;
				}
				
				while(true) {
					System.out.println(" Enter Student Name : ");
					String name = br.readLine();
					boolean check = val.name(name);
					if(check == true) {
						st.setName(name);
						break;
					}
					else {
						System.out.println("You have entered wrong input only Alphabets will allow.");
						continue;
					}
					
				}
				
				
				while(true) {
					System.out.println(" Enter Student DOB(ex:25-10-1990) : ");
					String dob = br.readLine();
					boolean check = val.dob(dob);
					if(check == true) {
						st.setDob(dob);
						break;
					}
					else {
						System.out.println(" Not a valid DOB see example");
						continue;
					}
					
				}
				
				
				System.out.println(" Enter Student Address : ");
				String address = br.readLine();
				st.setAddress(address);
				
				while(true) {
					System.out.println(" Enter Student Branch : ");
					String branch = br.readLine();
					boolean check = val.name(branch);
					if(check == true) {
						st.setBranch(branch);
						break;
					}
					else {
						System.out.println(" Invalid input ");
						continue;
					}
				}
				
				
				
				System.out.println(" Enter Student Batch year(ex:1990) : ");
				int year = 0;
				while(true) {
					try {
					year = Integer.parseInt(br.readLine());
					}
					catch(Exception e) {
						System.out.println(" Not a valid input only numbers will allow.");
						continue;
					}
					String reg = "^\\d{4}$";
					boolean e = Pattern.compile(reg).matcher(Integer.toString(year)).matches();
					if(e == true) {
						st.setYear(year);
						break;
						}
					else {
						System.out.println(" Please Enter valid input, see example.");
						continue;
						}
				}
				while(true) {
					System.out.println(" Enter Student Gender : ");
					String gender = br.readLine();
					boolean check = val.gender(gender);
					if(check == true) {
						st.setGender(gender);
						break;
					}
					else {
						System.out.println(" Invalid input ");
						continue;
					}
					
				}
				
				
				while(true) {
				System.out.println(" Enter Student contact(ex: +91 (789){1} (947982384){9}) : ");
				String contact = br.readLine();
				boolean check = val.contact(contact);
				if(check == true) {
					st.setContact(contact);
					break;
					}
				else {
					System.out.println(" Invalid input,see example ");
					continue;
					}				
				}
				std.addStudent(st);
				
				//-----------------ADD STUDENT FEE DETAILS--------------------------
				
				System.out.println(" ---ADD STUDENT FEE DETAILS---");
				System.out.println(" Total fees is : 40,000 .");
				while(true) {
					int paidfees =0;
					System.out.println(" Enter Student paid fees : ");
					try {
						paidfees = Integer.parseInt(br.readLine());
						if(paidfees>40000) {
							System.out.println(" You just have to pay 40,000.");
							continue;
						}
					}
					catch(Exception e) {
						System.out.println(" Not a valid input only numbers will allow.");
						continue;
					}
					st.setPaidfees(paidfees);
					break;
				}
				
				
				fdd.addStudentFeeDetails(st);
			}
			else if(opt ==2) {
				while(true) {
					System.out.println("\n---------View Student Details----------\n ");
					System.out.println(" 1. : View Student personal Details ");
					System.out.println(" 2. : View Student Fee Details ");
					System.out.println(" 3. : Exit");
					
					int ch=0;
					try {
						ch= Integer.parseInt(br.readLine());
					}
					catch(Exception e){
						System.out.println(" Select from above given respective inputs.");
						continue;
					}
					if(ch == 1) {
						
						std.viewStudent();
					}
					else if(ch ==2) {
						fdd.viewFeeDetails();
					}
					else if(ch ==3) {
						break;
					}
					else {
						System.out.println("\n please enter above listed valid inputs\n");
						continue;
					}
				}
				
			}
			//------------------DELETE STUDENT-----------------------------------------------------
			else if(opt ==3) {
				while(true) {
					System.out.println("\n---------Delete Student Details----------\n ");
					
					System.out.println(" Enter Student ID : ");
					int id = 0;
					try {
						id = Integer.parseInt(br.readLine());
					}
					catch(Exception e) {
						System.out.println(" Please enter a valid input..");
						continue;
					}
					std.deleteStudent(id);
					break;
				}
			}
			//------------------UPDATE STUDENT-----------------------------------------------------
			else if(opt ==4) {
				
				while(true) {
					System.out.println("\n---------Update Student Details----------\n ");
					System.out.println(" ==Update Student Fee Details== ");
					System.out.println(" Enter Student Roll NO. : ");
					int id = 0;
					try {
						id = Integer.parseInt(br.readLine());
					}
					catch(Exception e) {
						System.out.println(" Please enter a valid input..");
						continue;
					}
					fdd.updateFeeDetails(id);
					break;
				}
			}
			//------------------Export STUDENT Fee Details-----------------------------------------------------
			else if(opt ==5) {
				
				while(true) {
					System.out.println("\n---------Export STUDENT Fee Details----------\n ");
					
					System.out.println(" Enter Student Roll NO. : ");
					int id = 0;
					try {
						id = Integer.parseInt(br.readLine());
					}
					catch(Exception e) {
						System.out.println(" Please enter a valid input..");
						continue;
					}
					ExportPdf epd = new ExportPdf();
					epd.ExportPdf(id);
					break;
				}
			}
			else if(opt ==6) {
				System.out.println("\n\t.....Successfully Accountant Logging Out......\n");
				break;
			}
			else {
				System.out.println("\n....You have entered a wrong input...\n");
				continue;
			}
		}

		
	}

}
