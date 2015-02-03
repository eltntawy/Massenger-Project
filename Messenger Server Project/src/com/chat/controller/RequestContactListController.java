/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controller;

import com.chat.model.User;
import com.chat.service.RequestContactListService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Marwa
 */
public class RequestContactListController {

    public List<User> getRequestContactList(User user) throws SQLException {
        return RequestContactListService.getRequestContactList(user);
    }

}
