package com.chat.service;

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
import com.chat.view.resource.Resource;
import java.awt.Image;

public class UserService {

    /**
     *
     * insert user to database
     *
     *
     */
    public static int insertUser(User user) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO user VALUES (('" + user.getUserFirstName() + "','" + user.getUserSecondName() + "','" + user.getUserName() + "','" + user.getPassword() + "','" + user.getUserEmail() + "')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return 0;
    }

    /**
     * get user by user name
     *
     *
     */
    public static User getUserByUserName(String userName) {
        return null;
    }

    /**
     * get user by email
     *
     *
     */
    public static User getUserByEmail(String email) {
        return null;
    }

    /**
     * check is this user is in database before or not
     *
     * @return return true if this user is in database before
     *
     */
    public static boolean isRegistratedUser(User user) {
        return false;
    }

    /**
     *
     * check the userName and passwrod and fill user data and return it
     *
     * @throws SQLException
     *
     *
     */
    public static User userAuthentication(String userName, String passwrod) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM user WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, userName);
        ps.setString(2, passwrod);

        ResultSet rs = ps.executeQuery();
        User user = null;
        while (rs.next()) {
            int userId = rs.getInt("user_id");
            userName = rs.getString("User_Name");
            String password = rs.getString("password");
            String FullName = rs.getString("first_name");
            int status = rs.getInt("status");
            String img = rs.getString("image");
            ImageIcon imgicon;
            if (img == null || "".equals(img)) {                      //TODO Check for file Existance
                //image default
                imgicon = Resource.IMAGE_DEFAULT_USER;
            } else {
                imgicon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(rs.getString("image")).getScaledInstance(65, 65, Image.SCALE_SMOOTH));
            }
            //  ImageIcon userPicture = "".equals(rs.getString("image")) ? Resource.IMAGE_DEFAULT_USER : new ImageIcon(Toolkit.getDefaultToolkit().getImage(rs.getString("image")));

            user = new User(userId, userName, password, FullName, imgicon, status);
            break;
        }

        return user;
    }

    /**
     *
     * check the userName and passwrod and fill user data and return the contact
     * list of this user
     *
     *
     */
    public static List<User> userAuthAndGetContactList(String userName, String password) {
        return null;
    }

    /**
     *
     * delete user from database
     *
     *
     */
    public static boolean deleteUser(User user) {

        return false;
    }

    /**
     *
     * delete user from database
     *
     *
     */
    public static boolean deleteUserByUserName(String userName) {
        return false;
    }
}
