package com.chat.controller;

import java.sql.SQLException;
import java.util.List;

import com.chat.model.User;
import com.chat.service.ContactService;
import java.util.Map;

public class ContactServerController {

    public static void updateUserImage(String Path, User user) {
        ContactService.updateUserImage(Path, user);
    }

    public List<User> getContactOfNameOrEmailOrUseName(String searchText) throws SQLException {
        // TODO Auto-generated method stub
        return ContactService.getContactByNameOrEmailOrUseName(searchText);

    }

    public List<User> getContactListOfCurrentUser(User u) throws SQLException {
        // TODO Auto-generated method stub
        return ContactService.getContactListOfCurrentUser(u);
    }

    public static Map<String, Integer> getAllContactStatus() throws SQLException {

        return ContactService.getAllContactStatus();

    }

}
