/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.model;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Vector;


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
    private Vector<User> usersVector;
    private Font font;
    private Color color;
    
    public Message (User senderName, Vector<User> usersVector, String message, String sessionId){
        this.senderName = senderName;
        this.message = message;
        this.sessionId = sessionId;
        this.usersVector = usersVector;
        
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
     public void setUsersVector(Vector<User> usersVector){
        this.usersVector = usersVector;
    }
    
    public Vector<User> getUsersVector (){
        return usersVector;
    }
    public Font getFont() {
        return font;
    }
    public void setFont(Font font) {
        this.font = font;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
}
