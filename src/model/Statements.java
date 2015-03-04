package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
<<<<<<< HEAD
    public List getPizzas(){
			try {
				List l = new ArrayList<Pizza>();
				ResultSet rs = DBConnect.getData("SELECT * FROM pizzas");
				while(rs.next()){
					Pizza p = new Pizza();
					p.name = rs.getString("name");
					p.price = rs.getInt("price");
					p.pizzaid = rs.getInt("pizzaid");
					l.add(p);
				}
				return l;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
    }
    
    
	public boolean addUserToDatabase(String e, String p, String n, String a, String z, String ph){
=======
	public void addUserToDatabase(String e, String p, String n, String a, String z, String ph){
>>>>>>> 952e22511bcd0d19a366324ab3a07f3f1973f89b
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
				
<<<<<<< HEAD
				return true;

=======
>>>>>>> 952e22511bcd0d19a366324ab3a07f3f1973f89b
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
			PreparedStatement pstmt = DBConnect.preparedStatement("INSERT INTO pin (useremail, pincode) VALUES (?, ?);");
			pstmt.setString(1, email);
			pstmt.setString(2, pinCode);
			pstmt.executeUpdate();
		
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return pinCode;
		
	}
	
<<<<<<< HEAD
    
=======
	public List<Pizza> getPizzas(int offset, int numberOfPizzas, String sortBy) throws Exception{
		try {
			List<Pizza> pizzas = new ArrayList<Pizza>();
			rs = c.getData("SELECT name, price, description FROM pizza ORDER BY " + sortBy + " LIMIT " + offset + ", " + numberOfPizzas);
			
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
			rs = c.getData("SELECT COUNT(*) AS numpizz FROM pizza");
			if(rs.next()) return (rs.getInt("numpizz"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
>>>>>>> 952e22511bcd0d19a366324ab3a07f3f1973f89b
}
