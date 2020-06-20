package model;

public class AccountantPojo {
	String name;
	static String password;
	static String email;
	String contact;
	int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		AccountantPojo.password = password;
	}
	public static String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		AccountantPojo.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	
}
