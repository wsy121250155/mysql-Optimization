package optimization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnect {
	private String url="jdbc:mysql://127.0.0.1:3306/hw2";
	private String user="wsy";
	private String password="123";
	private Connection connection;
	public Dbconnect(){}
	public Dbconnect(String url, String user, String pw){
		this.url=url;
		this.user=user;
		this.password=pw;
	}
	public Connection getConnection(){
		try {
			connection=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
