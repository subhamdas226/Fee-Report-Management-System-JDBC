package utility;

import java.util.regex.*;

public class Validation {
	public boolean roll(String id) {
		String reg = "[0-9]";
		boolean e = Pattern.compile(reg).matcher(id).matches();
		
		if(e == true) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean name(String name) {
		String reg = "^[a-zA-Z ]*$";
		boolean e = Pattern.compile(reg).matcher(name).matches();
		
		if(e == true) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean dob(String dob) {
		
		String reg = "^\\d{2}-\\d{2}-\\d{4}$";
		boolean e = Pattern.compile(reg).matcher(dob).matches();
		
		if(e == true) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean year(int year) {
		
		return false;
	}
	public boolean gender(String gender) {
		String reg = "^[a-zA-Z]*$";
		boolean e = Pattern.compile(reg).matcher(gender).matches();
		
		if(e == true) {
			return true;
		}
		else {
			return false;
		}
	
	}
	public boolean contact(String contact) {
		String regix_phone = "^(\\+91?)[789]{1}\\d{9}$";
		boolean e = Pattern.compile(regix_phone).matcher(contact).matches();
		
		if(e == true) {
			return true;
		}
		else {
			return false;
		}
	}  

	public boolean email(String email) {
		String reg = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		boolean e = Pattern.compile(reg).matcher(email).matches();
		
		if(e == true) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean pass(String pass) {
		//String reg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{5,20}$";
		String reg = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{5,20})";
		boolean e = Pattern.compile(reg).matcher(pass).matches();
		
		if(e == true) {
			return true;
		}
		else {
			return false;
		}
	}
}
