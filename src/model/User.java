package model;

public class User {
	private int id;
	private String email;
	private String password;
	private String name;
	private String address;
	private int zipcode;
	private int phoneNumber;
	private String role;
	
	public User(){
	}
	
	public User(String email, String password, String name, String address,
			int zipcode, int phoneNumber, String role) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getRole(){
		return role;
	}
	
	
}
