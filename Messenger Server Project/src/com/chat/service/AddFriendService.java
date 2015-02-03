/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.service;

import com.chat.controller.ChatServerController;
import com.chat.controller.ServerController;
import com.chat.db.DBConnection;
import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
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

    public static void initContactListForOtherUser(User user) {
        Vector<ChatClientService> clientVector;
        clientVector = ServerController.getChatClientVector();

        for (int i = 0; i < clientVector.size(); i++) {
            try {
                if (clientVector.get(i).getUser().getUserName().equals(user.getUserName())) {
                    try {
                        clientVector.get(i).fitchContactList();
                    } catch (RemoteException ex) {
                        Logger.getLogger(AddFriendService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AddFriendService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean checkRequestExistance(User user, User user0) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM messenger_project.friend_request where (sender_id=? and Friend_Requestcol=? ) or (sender_id=? and Friend_Requestcol=?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            //modified

            ps.setInt(2, user.getUserId());
            ps.setInt(1, user0.getUserId());
            ps.setInt(3, user.getUserId());
            ps.setInt(4, user0.getUserId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }

            //return false;
        } catch (SQLException ex) {
            Logger.getLogger(addFriendRequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
