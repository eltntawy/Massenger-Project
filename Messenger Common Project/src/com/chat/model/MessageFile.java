/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.model;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author yomna
 */
public class MessageFile implements Serializable{
    List<byte[]> file;
    User sender;
    User receiver;
    String sessionID;
    String fileName="";
    Vector<User> usersVector;
    
    public MessageFile(List<byte []> file,String fileName, User sender, Vector<User> usersVector, String sessionID) {
        this.file = file;
        this.fileName = fileName;
        this.sender = sender;
        this.sessionID = sessionID;
        this.usersVector = usersVector;
        
    }

    public void setFile(List<byte[]> file) {
        this.file = file;
    }
    
    public void setUsersVector(Vector<User> usersVector){
        this.usersVector = usersVector;
    }
    
    public Vector<User> getUsersVector (){
        return usersVector;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
    

    public List<byte[]> getFile() {
        return file;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public String getSessionID() {
        return sessionID;
    }
}
