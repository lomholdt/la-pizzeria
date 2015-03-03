package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
	
	static Connection conn = null;
	static PreparedStatement stmt = null;
	static boolean b;
	
	public static void setCon(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://mysql.itu.dk:3306/la_pizzeria";
			String connectionUser = "la_pizzeria";
			String connectionPassword = "pizzatime";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static Connection getCon() throws Exception {
        if (conn == null) {
            setCon();
        }
        return conn;
    }
    
    public static PreparedStatement preparedStatement(String sql) throws Exception {
    	return getCon().prepareStatement(sql);
    }
    
    public static ResultSet getData(String sql) throws Exception {
        Statement state = getCon().createStatement();
        ResultSet rs = state.executeQuery(sql);
        return rs;
    }
}
