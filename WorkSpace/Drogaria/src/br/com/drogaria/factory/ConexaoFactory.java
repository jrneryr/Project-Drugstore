package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoFactory {

	private final static String USER = "root";
	private final static String PASSWORD = "@EppursiMuove!";
	private final static String URL = "jdbc:mysql://localhost:3306/drogaria?useSSL=false&useTimezone=true&serverTimezone=UTC";
	
	public static Connection conectar() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
		return conexao;
	}
	
	public static void closeConnection(Connection con){
		if(con!= null ){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		if(stmt!= null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
		closeConnection(con,stmt);
		if(rs!= null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
