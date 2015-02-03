/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controller;

import com.chat.db.DBConnection;
import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatServerService;
import com.chat.service.UserService;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eltntawy
 */
public class ServerController {

    private static Vector<ChatClientService> clientVector = new Vector<>();

    public ServerController() {

    }

    public static Vector<ChatClientService> getChatClientVector() {
        return clientVector;
    }

    public boolean userAuthenticate(String userName, String password) {
        try {
            DBConnection db = new DBConnection();
            Connection connection = DBConnection.getConnection();
            connection.prepareStatement("SELECT * FROM messenger_project.user;");
        } catch (SQLException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void registerClient(ChatClientService chatClientService) {

        clientVector.add(chatClientService);
        
        synchronized (clientVector) {
            for(ChatClientService client : clientVector)  {
                try {
            	client.getUser();
                } catch (RemoteException e) {
            	clientVector.remove(client);
            	e.printStackTrace();
                }
            }
	}
        
    }

    public  void  unregisterClient(ChatClientService chatClientService) {

	synchronized (clientVector) {
	    if(clientVector.contains(chatClientService))
		clientVector.remove(chatClientService);
	}
        
    }

    public Vector<ChatClientService> getregisteredClient() {

        return clientVector;
    }

    public  void unregisterAllClient() {

        UserService.doOfflineAllUsers();
        for (ChatClientService client : clientVector) {
            try {
                client.doFourceSignOut();
                client.setUser(null);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }

        synchronized (clientVector) {
            clientVector.removeAllElements();
	}
        
    }

}
