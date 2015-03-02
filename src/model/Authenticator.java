package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authenticator {
	
	public boolean authUserValues(String email, String password, String name, String address, int zipcode, int phonenumber ){
					
			
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		if(!m.matches()){return false;}
		
		return true;
		
	}
	
}
