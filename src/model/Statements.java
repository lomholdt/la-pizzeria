package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
				pstmt = c.preparedStatement("INSERT INTO user (email, password, name, address, zipcode, phonenumber) "
					+ "VALUES (?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, e);
				pstmt.setString(2, p);
				pstmt.setString(3, n);
				pstmt.setString(4, a);
				pstmt.setString(5, z);
				pstmt.setString(6, ph);				
				pstmt.executeUpdate();
				
				return true;

		} catch (Exception e1) {
//			e1.printStackTrace();
			System.out.print(e1.getMessage());
			return false;
		}
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
	

	public List<Pizza> getPizzas(int offset, int numberOfPizzas, String sortBy) throws Exception{
		try {
			List<Pizza> pizzas = new ArrayList<Pizza>();
			PreparedStatement pinstmt = c.preparedStatement("SELECT name, price, description FROM pizza ORDER BY ? LIMIT ?,?");
			pinstmt.setString(1, sortBy);
			pinstmt.setInt(2, offset);
			pinstmt.setInt(3,  numberOfPizzas);
			rs = pinstmt.executeQuery();
			
			while(rs.next()){
				Pizza p = new Pizza();
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
			PreparedStatement pinstmt = c.preparedStatement("SELECT name, price, description FROM pizza WHERE pizza.id = ?;");
			pinstmt.setInt(1, withId);
			rs = pinstmt.executeQuery();
			if(rs.next()) {
				Pizza p = new Pizza();
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setDescription(rs.getString("description"));
				return p;
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
}
