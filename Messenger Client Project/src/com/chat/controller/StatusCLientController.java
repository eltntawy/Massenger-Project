package com.chat.controller;

import java.rmi.RemoteException;
import java.sql.SQLException;

import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.MainFrame;

public class StatusCLientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    
    private MainFrame parentFrame;

    
    public StatusCLientController(MainFrame parentFrame, ChatClientService chatClientService, ChatServerService chatServerService) {
	// TODO Auto-generated constructor stub
        this.chatClientService = chatClientService;
        this.chatServerService = chatServerService;
        this.parentFrame = parentFrame;
    }

   
    public int doChangeStatus(User user) throws RemoteException, SQLException {
	int ret = chatServerService.updateUserStatus(user);
	((ChatClientServiceImpl)chatClientService).getAuthenticationController().showMyStstus(user);
	return ret;
    }
}
