package com.Routing.DAO;
import java.sql.Connection;
import java.sql.DriverManager;



	public class Connction {
		
		
		public static Connection  connect()
		{
			Connection con;
			try{
			Class.forName("com.mysql.jdbc.Driver");
		     con= DriverManager.getConnection("jdbc:mysql://localhost/logindb","root","root");
			
		     System.out.println("established");
			}catch(Exception e) {e.printStackTrace(); return null;}
			
			return con;
		}

		
		
		}



	


