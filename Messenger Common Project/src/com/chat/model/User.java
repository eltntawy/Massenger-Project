/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.model;

import java.io.Serializable;
import javax.swing.Icon;

/**
 *
 * @author eltntawy
 */
public class User implements Serializable {

    public static int AWAY = 3;
    public static int BUSY = 2;
    public static int AVAILABLE = 1;
    public static int OFFLINE = 0;
    public static int SIGNOUT=-1;

    private int userId;
    private String userName;
    private String password;
    private String FullName;
    private Icon userPicture;
    private int status;

    public User(int userId,String userName, String password, String FullName, Icon userPicture, int status) {
        this.userId=userId;
        this.userName = userName;
        this.password = password;
        this.FullName = FullName;
        this.userPicture = userPicture;
        this.status = status;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public Icon getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(Icon userPicture) {
        this.userPicture = userPicture;
    }

    @Override
    public String toString() {
        return FullName;
    }
    
    

}
