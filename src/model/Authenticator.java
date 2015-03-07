package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authenticator {
	
	public boolean isValidUser(String email, String password, String name, String address, String zipcode, String phonenumber){
		if(!isValidEmail(email)){return false;}
		if(!isValidPassword(password)){return false;}
		if(!isValidName(name)){return false;}
		if(!isValidAddress(address)){return false;}
		if(!isValidZipcode(zipcode)){return false;}
		if(!isValidPhonenumber(phonenumber)){return false;}
		
		return true;
	}
	
	
	public boolean isValidEmail(String email){
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	public boolean isValidPassword(String password){
		Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[A-z])(?=.*[*'^¨~|;,.><!#¤%&()=?@£$€])(?=\\S+$).{6,}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(password);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	public boolean isValidName(String name){
		Pattern p = Pattern.compile("^[A-z\\s]+$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	public boolean isValidAddress(String address){
		Pattern p = Pattern.compile("^(?=.*\\s)(?=.*\\d).*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(address);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	public boolean isValidZipcode(String zipcode){
		Pattern p = Pattern.compile("^\\d{4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(zipcode);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	public boolean isValidPhonenumber(String phonenumber){
		Pattern p = Pattern.compile("^\\d{8}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(phonenumber);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	public boolean isValidPizzaName(String name){
		Pattern p = Pattern.compile(".+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if(!m.matches()){return false;}
		
		return true;
	}
	
	public boolean isValidPizzaPrice(String price){
		Pattern p = Pattern.compile("^\\d+$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(price);
		if(!m.matches()){return false;}
		
		return true;	
	}
	
	public boolean isValidPizzaDescription(String description){
		Pattern p = Pattern.compile(".+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(description);
		if(!m.matches()){return false;}
		
		return true;	
	}
	
	public boolean isAdmin(User u){
		if (u.getRole().equals("admin")) return true;
		return false;
	}
}
