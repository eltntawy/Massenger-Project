package com.chat.controller;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.service.ContactService;
import com.chat.service.StatusService;
import com.chat.view.FXMLMainServerFrameController;

public class StatusServerController {

   

    public int doChangeStatus(User user)  throws RemoteException, SQLException{
	// TODO Auto-generated method stub
	int ret = StatusService.updateUserSatus(user);
	
	Vector<ChatClientService> clientVector = ServerController.getChatClientVector();
	
	for(ChatClientService client : clientVector) {
	    if(ContactService.isFriendOfUser(user, client.getUser())) {
		client.fitchContactList();
	    }
	}
	return ret;
    }
}
