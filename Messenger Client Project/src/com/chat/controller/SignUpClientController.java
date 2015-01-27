package com.chat.controller;

import java.rmi.RemoteException;

import javax.swing.JFrame;

import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.MainFrame;
import com.chat.view.SignUpPanel;

public class SignUpClientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    private SignUpPanel signUpPanel;
    private MainFrame parentFrame;

    
    public SignUpClientController (MainFrame parentFrame,ChatClientService chatClientService,ChatServerService chatServerService) {
	this.chatClientService = chatClientService;
	this.chatServerService = chatServerService;
	this.parentFrame = parentFrame;
	this.signUpPanel = new SignUpPanel(parentFrame, this);
    }
    
    public void doSignUp() throws RemoteException {
	// TODO Auto-generated method stub
	
        
	AuthenticationClientController signInController = ((ChatClientServiceImpl)chatClientService).getAuthenticationController();

        signInController.doSignUp();
    }

    public void showSignUp() {
	// TODO Auto-generated method stub
	parentFrame.removeCurrentPanel();
	parentFrame.addCurrentPanel(signUpPanel);
    }

    
    
}
