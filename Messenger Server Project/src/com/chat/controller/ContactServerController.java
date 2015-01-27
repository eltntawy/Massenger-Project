package com.chat.controller;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.chat.db.DBConnection;
import com.chat.model.User;
import com.chat.service.ContactService;
import com.chat.service.UserService;


public class ContactServerController {

    public static void updateUserImage(String Path,User user) {
        ContactService.updateUserImage(Path,user);
    }

    public List<User> getContactOfNameOrEmailOrUseName(String searchText) throws SQLException {
	// TODO Auto-generated method stub
	return ContactService.getContactByNameOrEmailOrUseName(searchText);
	
    }

    public List<User> getContactListOfCurrentUser(User u) throws SQLException {
	// TODO Auto-generated method stub
	return ContactService.getContactListOfCurrentUser(u);
    }

}
