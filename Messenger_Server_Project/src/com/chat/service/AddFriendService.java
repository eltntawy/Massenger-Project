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
public class AddFriendService {

    public static void addFriend(User user, User mainUser) {

        try {
            Connection conn = DBConnection.getConnection();

            // TODO create currect select querey
            String sql = "INSERT INTO `messenger_project`.`friend_list` (`user_id`, `friend_id`) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mainUser.getUserId());
            ps.setInt(2, user.getUserId());
            ps.execute();

            deleteFriendRequest(user, mainUser);

            String sql2 = "INSERT INTO `messenger_project`.`friend_list` (`user_id`, `friend_id`) VALUES (?, ?);";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(2, mainUser.getUserId());
            ps2.setInt(1, user.getUserId());
            ps2.execute();

            deleteFriendRequest(mainUser, user);
            //  }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void deleteFriendRequest(User user, User mainUser) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "Delete from messenger_project.friend_request where friend_requestcol=? and sender_id=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            //modified
            ps.setInt(2, user.getUserId());
            ps.setInt(1, mainUser.getUserId());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(addFriendRequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void DeleteContactFromUser(User user, User Mainuser) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "Delete from messenger_project.friend_list where (user_id=? and friend_id=?) OR (user_id=? and friend_id=?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            //modified
            
            ps.setInt(1, Mainuser.getUserId());
            ps.setInt(2, user.getUserId());
            ps.setInt(3, user.getUserId());
            ps.setInt(4, Mainuser.getUserId());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(addFriendRequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
