/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.MessageFile;
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

    private Vector<ChatClientService> clientVector;
    
    public void sendMessage (Message message){
        
        
        System.out.println("Message Received at serve");
        clientVector = ServerController.getChatClientVector();
        
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
    public boolean requestSend (String fileName,User sender, User receiver, String sessionId){
        
       clientVector = ServerController.getChatClientVector();
        
        for (int i = 0; i < clientVector.size(); i++){
            
            ChatClientService client = clientVector.elementAt(i);
            try {
                User Receiver = client.getUser();
                if (Receiver.getStatus() == User.AVAILABLE){
                    if (Receiver.getUserName().equals(receiver.getUserName())){
                        //receiver is online
                        return client.confirmRequest(sender, fileName, sessionId);
                    }
                }
             /*   else {
                }*/
                
            } catch (RemoteException ex) {
                Logger.getLogger(ChatServerController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        return false;
    }
    
    public void sendFile (MessageFile messageFile){
        System.out.println("File Received at serve");
        clientVector = ServerController.getChatClientVector();
        
        for (int i = 0; i < clientVector.size(); i++){
            
            ChatClientService client = clientVector.elementAt(i);
            try {
                User Receiver = client.getUser();
                if (Receiver.getStatus() == User.AVAILABLE){
                    if (Receiver.getUserName().equals(messageFile.getReceiver().getUserName())){
                        //receiver is online
                        client.receiveFile(messageFile);
                    }
                }
                
            } catch (RemoteException ex) {
                Logger.getLogger(ChatServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
