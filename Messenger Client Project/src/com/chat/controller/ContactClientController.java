package com.chat.controller;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.MainFrame;

public class ContactClientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    private MainFrame frame;

    public ContactClientController(ChatClientService chatClientService, ChatServerService chatServerService) {

	this.chatClientService = chatClientService;
	this.chatServerService = chatServerService;

    }

    public void addRequestContact(User user) {
	
    }

    public List<User> getContactOfNameOrEmailOrUseNameList(String searchText) throws RemoteException, SQLException {
	// TODO Auto-generated method stub
	
		   return  chatServerService.getContactOfNameOrEmailOrUseName(searchText);
		   
	    
	
    }
    
    public void getContactOfNameOrEmailOrUseName(final String searchText) throws RemoteException, SQLException {
	// TODO Auto-generated method stub
	new Thread() {
	    @Override
	    public void run() {
	        // TODO Auto-generated method stub
		try {
		   List<User> retList = chatServerService.getContactOfNameOrEmailOrUseName(searchText);
		   ((ChatClientServiceImpl)chatClientService).getMessengerController().initContactListView(retList);
		} catch (RemoteException | SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	};
	
    }

}
