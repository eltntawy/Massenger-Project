package com.chat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;


public class DBConnection {


    public static Connection getConnection() throws SQLException {

	DriverManager.registerDriver(new Driver());
        
        String unicode= "?useUnicode=yes&characterEncoding=UTF-8";

	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/messenger_project?useUnicode=yes&characterEncoding=utf8", "root", "root");
	
	return conn;
    }

}
