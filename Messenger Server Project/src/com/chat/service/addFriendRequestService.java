/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.service;

import com.chat.db.DBConnection;
import com.chat.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marwa
 */
public class addFriendRequestService {

    public static void addRequestContact(User user, User userSender) {
        boolean flag = false;
        try {
            Connection conn = DBConnection.getConnection();
            if (userSender.getUserId() == user.getUserId()) {
                System.out.println("Error u cannot add yourself");
                flag = false;
            } else {
                // TODO create currect select querey
                String sql = "INSERT INTO `messenger_project`.`friend_request` (`sender_id`, `Friend_Requestcol`) VALUES (?, ?);";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, userSender.getUserId());
                ps.setInt(2, user.getUserId());
                ps.execute();
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(addFriendRequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
