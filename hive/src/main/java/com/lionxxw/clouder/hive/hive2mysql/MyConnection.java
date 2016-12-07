package com.lionxxw.clouder.hive.hive2mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	private static String mysql_host = "jdbc:mysql://192.168.170.128:3306/test";
	private static String hive_host = "jdbc:hive2://192.168.170.128:10000/default";

	
	public static Connection getMysqlInstance() throws Exception
	{
		
		Class.forName("com.mysql.jdbc.Driver") ;
		Connection con = 
			  DriverManager.getConnection(mysql_host,"root","123456");
		return con; 
	}
	public static Connection getHiveInstance() throws Exception
	{
		
		Class.forName("org.apache.hive.jdbc.HiveDriver") ;
		Connection con = 
			  DriverManager.getConnection(hive_host, "root", "");
		return con; 
	}
	public static void main(String[] args) {
		try {
			System.out.println(MyConnection.getHiveInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
