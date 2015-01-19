/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author yomna
 */
public class Message {
    
    private String senderName;
    private String receiverName;
    private String message;
    private String time;
    
    public Message (String senderName, String receiverName, String message){
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setSenderName (String senderName){
        this.senderName = senderName;
    }
    public void setReceiverName (String receiverName){
        this.receiverName = receiverName;
    }
    public String getMessage(){
        return message;
    }
    public String getSenderName(){
        return senderName;
    }
    public String getReceiverName(){
        return receiverName;
    }
    public String getTime(){
        Calendar cal = Calendar.getInstance();
        DateFormat date = DateFormat.getTimeInstance();
        time = date.format(cal.getTime());
        return time;
    }
}
