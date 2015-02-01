package com.chat.controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.chat.model.User;
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
    public User user;
    public SignUpPanel signupview;
    private String firstname;
    private String secondname;
    private int gender;
    private String email;
    private String username;
    private String password;
    private ChatClientServiceImpl clientimp;

    public SignUpClientController(MainFrame parentFrame, ChatClientService chatClientService, ChatServerService chatServerService) {
	this.chatClientService = chatClientService;
	this.chatServerService = chatServerService;
	this.parentFrame = parentFrame;
	this.signUpPanel = new SignUpPanel(parentFrame, this);
    }

    public void initRMIService() throws RemoteException, NotBoundException {

	String serverIP = JOptionPane.showInputDialog("Please enter the server ip ","127.0.0.1");
	Registry reg = LocateRegistry.getRegistry(serverIP, 8888);

	ChatServerService serverService = (ChatServerService) reg.lookup("ChatService");

	ChatClientService clientService = new ChatClientServiceImpl(null);

	this.chatServerService = serverService;
	this.chatClientService = clientService;
    }

    public void doSignup(User user) throws RemoteException, NotBoundException,SQLException {
	// TODO Auto-generated method stub

	AuthenticationClientController signInController = new AuthenticationClientController(parentFrame, chatClientService, chatServerService);

	initRMIService();

	if (chatClientService != null && chatServerService != null) {

	    ChatClientController chatController = new ChatClientController(chatClientService, chatServerService);
	    ContactClientController contactController = new ContactClientController(chatClientService, chatServerService);

	    SignUpClientController signUpController = this;
	    StatusCLientController statusController = new StatusCLientController(parentFrame, chatClientService, chatServerService);

	    ((ChatClientServiceImpl) chatClientService).setAuthenticationController(signInController);
	    ((ChatClientServiceImpl) chatClientService).setChatController(chatController);
	    ((ChatClientServiceImpl) chatClientService).setContactController(contactController);
	    ((ChatClientServiceImpl) chatClientService).setSignUpController(signUpController);
	    ((ChatClientServiceImpl) chatClientService).setStatusController(statusController);

	    chatServerService.doSignup(user);
	    signInController.showSignIn();
	}

    }

    public void showSignUp() {
	// TODO Auto-generated method stub
	parentFrame.removeCurrentPanel();
	parentFrame.addCurrentPanel(signUpPanel);
    }

    public void signUp(User user) throws RemoteException, SQLException {
	AuthenticationClientController signInController = new AuthenticationClientController(parentFrame, chatClientService, chatServerService);

	try {
	    initRMIService();

	    if (chatClientService != null && chatServerService != null) {

		ChatClientController chatController = new ChatClientController(chatClientService, chatServerService);
		ContactClientController contactController = new ContactClientController(chatClientService, chatServerService);

		SignUpClientController signUpController = this;
		StatusCLientController statusController = new StatusCLientController(parentFrame, chatClientService, chatServerService);

		((ChatClientServiceImpl) chatClientService).setAuthenticationController(signInController);
		((ChatClientServiceImpl) chatClientService).setChatController(chatController);
		((ChatClientServiceImpl) chatClientService).setContactController(contactController);
		((ChatClientServiceImpl) chatClientService).setSignUpController(signUpController);
		((ChatClientServiceImpl) chatClientService).setStatusController(statusController);

	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	} catch (NotBoundException ex) {
	    ex.printStackTrace();
	}

	chatServerService.doSignup(user);
	signInController.showSignIn();

    }

}