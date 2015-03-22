package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	boolean b;
	
	public void setCon(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://mysql.itu.dk:3306/la_pizzeria";
//			String connectionUrl = "jdbc:mysql://localhost:3306/la_pizzaria";
			String connectionUser = "la_pizzeria";
			String connectionPassword = "pizzatime";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public Connection getCon() throws Exception {
        if (conn == null) {
            setCon();
        }
        return conn;
    }
    
    public PreparedStatement preparedStatement(String sql) throws Exception {
    	return getCon().prepareStatement(sql);
    }
    
    public ResultSet getData(String sql) throws Exception {
        Statement state = getCon().createStatement();
        ResultSet rs = state.executeQuery(sql);
        return rs;
    }
}
