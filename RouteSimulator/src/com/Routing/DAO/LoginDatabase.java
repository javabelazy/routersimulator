package com.Routing.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Routing.Model.LoginModel;

public class LoginDatabase {

private Connection connect= Connction.connect();

	public Boolean authenticate(LoginModel login) {
		// TODO Auto-generated method stub
		
		System.out.println("login authen"+ login.getUsername());
		
		try {
			PreparedStatement statement=connect.prepareStatement("SELECT * FROM logintable WHERE Username=? and Password=?");
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			ResultSet rs=statement.executeQuery();
			if (rs.next())
			return true;
			else
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(" err inside authenthin");
			e.printStackTrace();
		}
		
		
		return null;
	}


}
