/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yomna
 */
public class ChatServerController {

    public ChatServerController() {
    }
    
    
    
    public void sendMessage (Message message){
        
        
        System.out.println("Message Received at serve");
        Vector<ChatClientService> clientVector = ServerController.getChatClientVector();
        
        for (int i = 0; i < clientVector.size(); i++){
            
            ChatClientService client = clientVector.elementAt(i);
            try {
                User Receiver = client.getUser();
                if (Receiver.getUserName().equals(message.getReceiverName().getUserName())){
                    //receiver is online
                    client.receiveMessage(message);
                }
                
            } catch (RemoteException ex) {
                Logger.getLogger(ChatServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
