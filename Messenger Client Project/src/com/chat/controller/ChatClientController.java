package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.ChatFrame;
import com.test.chat.MainFrame;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.chat.view.MainFrame;

public class ChatClientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    private ChatFrame chatFrame;
    private MainFrame mainFrame;
    private User loginUser;
    private User Receiver;

    public ChatClientController( ChatClientService chatClientService,ChatServerService chatServerService) {

	this.chatServerService = chatServerService;
        this.chatClientService = chatClientService;

    }
    
    public void setLoginUser(){
        	try {
	    loginUser = chatClientService.getUser();
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    
    public User getLoginUser() {
	return loginUser;
    }

    public void showChatFrame (User reciever) {
        setLoginUser();
	if(loginUser != null) {
            chatFrame = new ChatFrame(loginUser,reciever,((ChatClientServiceImpl)chatClientService).getChatController());
	    chatFrame.setVisible(true);
	}
    }
    public void sendMessage(Message message) {
        
        try {
            
            chatServerService.sendMessage(message);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receiveMessage(Message message) {
       
        chatFrame.receiveMessage(message);
    }
    
}
