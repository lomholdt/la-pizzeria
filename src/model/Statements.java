package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
    }
    
}