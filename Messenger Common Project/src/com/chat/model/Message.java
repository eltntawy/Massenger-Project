/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author yomna
 */
public class Message implements Serializable{
    
    private User senderName;
    private User receiverName;
    private String message;
    private String time;
    private String sessionId;
    
    public Message (User senderName, User receiverName, String message, String sessionId){
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.sessionId = sessionId;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setSenderName (User senderName){
        this.senderName = senderName;
    }
    public void setReceiverName (User receiverName){
        this.receiverName = receiverName;
    }
    public void setSessionId (String sessionId){
        this.sessionId = sessionId;
    }
    public String getMessage(){
        return message;
    }
    public User getSenderName(){
        return senderName;
    }
    public User getReceiverName(){
        return receiverName;
    }
    public String getTime(){
        Calendar cal = Calendar.getInstance();
        DateFormat date = DateFormat.getTimeInstance();
        time = date.format(cal.getTime());
        return time;
    }
    public String getSessionId(){
        return sessionId;
    }
}
