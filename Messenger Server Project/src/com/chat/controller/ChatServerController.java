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

    private static Vector<ChatClientService> clientVector;
    private Vector<User> UserVector;

    public void sendMessage(Message message) {

        System.out.println("Message Received at serve");
        clientVector = ServerController.getChatClientVector();
        UserVector = message.getUsersVector();

        for (int i = 0; i < UserVector.size(); i++) {
            if (UserVector.elementAt(i).getStatus() == User.AVAILABLE) {
                for (int j = 0; j < clientVector.size(); j++) {
                    String receiver = UserVector.elementAt(i).getUserName();
                    String clientName;
                    ChatClientService client = clientVector.elementAt(j);
                    try {
                        clientName = clientVector.elementAt(j).getUser().getUserName();
                        if (receiver.equals(clientName) && !(receiver.equals(message.getSenderName().getUserName()))) {
                            client.receiveMessage(message);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatServerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    }

    public boolean requestSend(String fileName, User sender, Vector<User> UsersVector, String sessionId) {

        clientVector = ServerController.getChatClientVector();
          for (int i = 0; i < UserVector.size(); i++) {
            if (UserVector.elementAt(i).getStatus() == User.AVAILABLE) {
                for (int j = 0; j < clientVector.size(); j++) {
                    String receiver = UserVector.elementAt(i).getUserName();
                    String clientName;
                    ChatClientService client = clientVector.elementAt(j);
                    try {
                        clientName = clientVector.elementAt(j).getUser().getUserName();
                        if (receiver.equals(clientName) && !(receiver.equals(sender.getUserName()))) {
                            return client.confirmRequest(sender, fileName, sessionId, UsersVector);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatServerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        
        return false;
    }

    public int sendFile(MessageFile messageFile) throws RemoteException {
        System.out.println("File Received at serve");
        clientVector = ServerController.getChatClientVector();
        UserVector = messageFile.getUsersVector();
        int count = 0;
        for (int i = 0; i < UserVector.size(); i++) {
            if (UserVector.elementAt(i).getStatus() == User.AVAILABLE) {
                for (int j = 0; j < clientVector.size(); j++) {
                    String receiver = UserVector.elementAt(i).getUserName();
                    String clientName;
                    ChatClientService client = clientVector.elementAt(j);
                    try {
                        clientName = clientVector.elementAt(j).getUser().getUserName();
                        if (receiver.equals(clientName) && !(receiver.equals(messageFile.getSender().getUserName()))) {
                            client.receiveFile(messageFile);
                            count++;
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatServerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        return count;
    }

    public static void sendMessageForAllClient(Message message) throws RemoteException {

        for (ChatClientService client : ServerController.getChatClientVector()) {
            client.receiveMessage(message);
        }
    }
}
