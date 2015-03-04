package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
    
	public void addUserToDatabase(String e, String p, String n, String a, String z, String ph){
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
				
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
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
}
