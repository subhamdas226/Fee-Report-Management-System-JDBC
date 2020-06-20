package model;

public class AdminPojo {
	private static String email;
	private static String password;
	
	public static  String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		AdminPojo.email = email;
	}
	public static String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		AdminPojo.password = password;
	}
	
	
}
