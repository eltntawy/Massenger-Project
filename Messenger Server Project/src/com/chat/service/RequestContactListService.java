/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.service;

import com.chat.db.DBConnection;
import com.chat.model.User;
import com.chat.view.resource.Resource;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Marwa
 */
public class RequestContactListService {

    public static List<User> getRequestContactList(User user) {

        try {
            Connection conn = DBConnection.getConnection();

            // TODO create currect select querey
            String sql = " SELECT u.user_id,u.User_Name,u.password,u.first_name,u.second_name,u.status,u.image "
                    + " FROM user u, friend_request friendRequest "
                    + " where u.user_id = friendRequest.sender_id and friendRequest.Friend_Requestcol  = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();
            List<User> list = new ArrayList<User>();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("User_Name");
                String password = rs.getString("password");
                String FullName = rs.getString("first_name") + " " + rs.getString("second_name");
                int status = rs.getInt("status");
                String img = rs.getString("image");
                ImageIcon imgicon;
                if (img == null || "".equals(img)) {                      //TODO Check for file Existance
                    //image default
                    imgicon = Resource.IMAGE_DEFAULT_USER;
                } else {
                    imgicon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(rs.getString("image")).getScaledInstance(40, 40, Image.SCALE_SMOOTH));
                }
                User userR = new User(userId, userName, password, FullName, imgicon, status);
                list.add(userR);
                System.out.println("user is " + user.getUserName());

            }

            return list;
            // return null;
        } catch (SQLException ex) {
            Logger.getLogger(RequestContactListService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
