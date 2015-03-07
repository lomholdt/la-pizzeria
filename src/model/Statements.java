package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.security.*;


public class Statements {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DBConnect c;

    public Statements() {
        try {
        	c = new DBConnect();
			this.conn = c.getCon();
        } catch (Exception ex) {
        	ex.getMessage();
        }
    }
    
    public boolean login(String email, String pwd) {
    	try {
			pwd = hash256(pwd);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		pstmt = c.preparedStatement("SELECT email, password, active FROM user WHERE email =?;");
    		pstmt.setString(1, email);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			if(rs.getString("active").equals("1") && rs.getString("password").equals(pwd)) {
    				// BAM, LOG IND!
    				return true;
    			}
    		}
    	}
    	catch(Exception e1) {
    		e1.printStackTrace();
    	}
    	return false;
    }
   
	public boolean addUserToDatabase(String e, String p, String n, String a, String z, String ph){
		try {
			p = hash256(p);
			System.out.println(p);
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
				pstmt = c.preparedStatement("INSERT INTO user (email, password, name, address, zipcode, phonenumber) "
					+ "VALUES (?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, e);
				pstmt.setString(2, p);
				pstmt.setString(3, n);
				pstmt.setString(4, a);
				pstmt.setString(5, z);
				pstmt.setString(6, ph);				
				pstmt.executeUpdate();
				addUserRole(e, "user");
				return true;
		} catch (Exception e1) {
//			e1.printStackTrace();
			System.out.print(e1.getMessage());
		}
		return false;
	}
	
	public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
	
	public boolean addUserRole(String email, String role){
		try {
			pstmt = c.preparedStatement("INSERT INTO role (email, role) VALUES (?, ?)");
			pstmt.setString(1, email);
			pstmt.setString(2, role);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return false;
	}
	
	public String addPinToDatabase(String email){
		String pinCode = "";
		for (int k = 0; k < 4; k++){pinCode += new Random().nextInt(10);}
		try{
			PreparedStatement pstmt = c.preparedStatement("INSERT INTO pin (useremail, pincode) VALUES (?, ?);");
			pstmt.setString(1, email);
			pstmt.setString(2, pinCode);
			pstmt.executeUpdate();
		
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return pinCode;
	}
	
	public void removePinFromDatabase(String email) {
		try {
			PreparedStatement pstmt = c.preparedStatement("DELETE FROM la_pizzeria.pin WHERE pin.useremail = ?");
			pstmt.setString(1, email);
			pstmt.executeUpdate();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean validateEmail(String pin, String email) {
		try {
			PreparedStatement pinstmt = c.preparedStatement("SELECT pincode FROM pin WHERE pin.useremail = ?;");
			pinstmt.setString(1, email);
			rs = pinstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pincode").equals(pin)) {
					PreparedStatement pstmt = c.preparedStatement("UPDATE la_pizzeria.user SET active = 1 WHERE user.email =?;");
					pstmt.setString(1, email);
					pstmt.executeUpdate();
					return true;
				}
			}
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public List<Pizza> getPizzas(int offset, int pizzasPerPage, String sortBy) throws Exception{
		try {
			List<Pizza> pizzas = new ArrayList<Pizza>();
//			PreparedStatement pinstmt = c.preparedStatement("SELECT id, name, price, description FROM pizza ORDER BY ? LIMIT ?,?");
//			pinstmt.setString(1, sortBy);
//			pinstmt.setInt(2, offset);
//			pinstmt.setInt(3, pizzasPerPage);
//			rs = pinstmt.executeQuery();
			rs = c.getData("SELECT id, name, price, description FROM pizza ORDER BY " + sortBy + " LIMIT " + offset + ", " + pizzasPerPage);
			
			while(rs.next()){
				Pizza p = new Pizza();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setDescription(rs.getString("description"));
				pizzas.add(p);
			}
			
			return pizzas;
		} catch (Exception e) {
			throw e;
		}	
	}
	
	public int getNumPizzas(){
		try {
			PreparedStatement pinstmt = c.preparedStatement("SELECT COUNT(*) AS numpizz FROM pizza");
			rs = pinstmt.executeQuery();
			if(rs.next()) return (rs.getInt("numpizz"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public Item getPizza(int withId) throws Exception{
		try {
			PreparedStatement pinstmt = c.preparedStatement("SELECT id, name, price, description FROM pizza WHERE pizza.id = ?;");
			pinstmt.setInt(1, withId);
			rs = pinstmt.executeQuery();
			if(rs.next()) {
				Pizza p = new Pizza();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setDescription(rs.getString("description"));
				return p;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean addPizza(String name, int price, String description){
		try{
			PreparedStatement pstmt = c.preparedStatement("INSERT INTO pizza (name, price, description) VALUES (?, ?, ?);");
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setString(3,  description);
			pstmt.executeUpdate();
			
			return true;
		
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public boolean removePizza(int removeId){
		try {
			PreparedStatement pstmt = c.preparedStatement("DELETE FROM la_pizzeria.pizza WHERE pizza.id = ?");
			pstmt.setInt(1, removeId);
			pstmt.executeUpdate();
			System.out.println("PIZZA REMOVED...");
			return true;
			
		} catch (Exception e) {
			System.out.println("Could not remove pizza!!!");
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public User getUser(String email){
		try{
			PreparedStatement pstmt = c.preparedStatement("SELECT user.email,user.name,user.address,user.zipcode,user.phonenumber, role.role FROM user, role WHERE user.email=? AND role.email=?");
			pstmt.setString(1, email);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()){
				User currentUser = new User();
				currentUser.setEmail(rs.getString("email"));
				currentUser.setName(rs.getString("name"));
				currentUser.setAddress(rs.getString("address"));
				currentUser.setZipcode(rs.getInt("zipcode"));
				currentUser.setPhoneNumber(rs.getInt("phonenumber"));
				currentUser.setRole(rs.getString("role"));
				return currentUser;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean checkEmail(String email){
		try{
			PreparedStatement pstmt = c.preparedStatement("SELECT email FROM user WHERE email='"+email+"'");
			rs = pstmt.executeQuery();
			if(!rs.next()){
				return true;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}
}
