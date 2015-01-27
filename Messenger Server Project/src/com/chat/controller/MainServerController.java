package com.chat.controller;

import com.chat.rmi.ChatServerServiceImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class MainServerController {

    public MainServerController() throws RemoteException {
	
	Registry reg = LocateRegistry.createRegistry(8888);
	
        ServerController serverController = new ServerController();
        
	ChatServerServiceImpl server = new ChatServerServiceImpl(serverController);
        
        ChatServerController chatController2 = new ChatServerController();
        
        server.setChatController(chatController2);
	reg.rebind("ChatService", server);
        
        System.out.println("Server Started");
    }

    
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	try {
	    new MainServerController();
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
