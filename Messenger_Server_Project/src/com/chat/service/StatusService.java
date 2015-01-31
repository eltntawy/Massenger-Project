package com.chat.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chat.db.DBConnection;
import com.chat.model.User;

public class StatusService {

    public static int updateUserSatus(User user) throws SQLException {
	Connection conn = DBConnection.getConnection();

	// TODO create currect select querey
	String sql = " update user set status = ?  where user_id = ?";
	PreparedStatement ps = conn.prepareStatement(sql);

	ps.setInt(1, user.getStatus());
	ps.setInt(2, user.getUserId());

	return ps.executeUpdate();
    }
}
