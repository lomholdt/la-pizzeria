package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Statements {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

    public Statements() {
        try {
            this.conn = DBConnect.getCon();
        } catch (Exception ex) {
        	ex.getMessage();
        }
    }
    
//    public List getPizzas(){
//			try {
//				List l = new ArrayList<Pizza>();
//				rs = DBConnect.getData("SELECT * FROM pizzas");
//				while(rs.next()){
//					Pizza p = new Pizza();
////					p.name = rs.getString("name");
////					p.price = rs.getInt("price");
////					p.pizzaid = rs.getInt("pizzaid");
//					l.add(p);
//				}
//				return l;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//    }
    
	public void addUserToDatabase(String e, String p, String n, String a, String z, String ph){
		try {
				pstmt = DBConnect.preparedStatement("INSERT INTO user (email, password, name, address, zipcode, phonenumber) "
					+ "VALUES (?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, e);
				pstmt.setString(2, p);
				pstmt.setString(3, n);
				pstmt.setString(4, a);
				pstmt.setString(5, z);
				pstmt.setString(6, ph);
				
				pstmt.executeUpdate();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public List<Pizza> getPizzas(int offset, int numberOfPizzas, String sortBy){
		try {
			List<Pizza> pizzas = new ArrayList<Pizza>();
			
			rs = DBConnect.getData("SELECT name, price, description FROM pizza ORDER BY " + sortBy + " LIMIT " + offset + ", " + numberOfPizzas);
			
			while(rs.next()){
				Pizza p = new Pizza();
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setDescription(rs.getString("description"));
				pizzas.add(p);
			}
						
			return pizzas;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;	
	}
	
	public int getNumPizzas(){
		try {
			rs = DBConnect.getData("SELECT COUNT(*) AS numpizz FROM pizza");
			if(rs.next()) return (rs.getInt("numpizz"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
    
}
