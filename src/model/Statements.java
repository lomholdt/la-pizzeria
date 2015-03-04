package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Statements {
	

    Connection conn;

    public Statements() {
        try {
            this.conn = DBConnect.getCon();
        } catch (Exception ex) {
        	ex.getMessage();
        }
    }
    
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
		try {
				PreparedStatement pstmt = DBConnect.preparedStatement("INSERT INTO user (email, password, name, address, zipcode, phonenumber) "
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
			PreparedStatement pstmt = DBConnect.preparedStatement("INSERT INTO pin (useremail, pincode) VALUES (?, ?);");
			pstmt.setString(1, email);
			pstmt.setString(2, pinCode);
			pstmt.executeUpdate();
		
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return pinCode;
		
	}
	
    
}
