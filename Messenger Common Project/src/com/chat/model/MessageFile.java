/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.model;

import java.io.Serializable;

/**
 *
 * @author yomna
 */
public class MessageFile implements Serializable{
    byte[] file;
    User sender;
    User receiver;
    String sessionID;
    
    public MessageFile(byte[] file, User sender, User receiver, String sessionID) {
        this.file = file;
        this.sender = sender;
        this.receiver = receiver;
        this.sessionID = sessionID;
        
    }

    public void setFile(byte[] file) {
        this.file = file;
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
    

    public byte[] getFile() {
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
