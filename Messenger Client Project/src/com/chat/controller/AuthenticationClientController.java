package com.chat.controller;

import java.awt.Component;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.MainFrame;
import com.chat.view.SignInPanel;
import com.chat.view.SignUpPanel;

public class AuthenticationClientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    private SignInPanel signInPanel;
    private MainFrame parentFrame;

    public AuthenticationClientController(MainFrame parentFrame, ChatClientService chatClientService, ChatServerService chatServerService) {

        this.chatClientService = chatClientService;
        this.chatServerService = chatServerService;
        this.parentFrame = parentFrame;
        this.signInPanel = new SignInPanel(parentFrame, this);
        this.chatClientService = chatClientService;
        this.chatServerService = chatServerService;
        this.parentFrame = parentFrame;
        this.signInPanel = new SignInPanel(parentFrame, this);
        // parentFrame.remove(null);

    }

    public JPanel getSigninPanel() {
        // TODO Auto-generated method stub
        return signInPanel;
    }

    public void showSignIn() {
        // TODO Auto-generated method stub
        parentFrame.removeCurrentPanel();
        parentFrame.addCurrentPanel(signInPanel);

    }

    public User doSignIn(String userName, String password) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        User user = userAuthentication(userName, password);
        if (user != null) {

            chatClientService.setUser(user);
            MessengerClientController messengerController = new MessengerClientController(parentFrame, chatClientService, chatServerService);
            messengerController.showMainPanel();
            messengerController.getContactListOfCurrentUser();
            messengerController.getRequestContactList();
            ((ChatClientServiceImpl)chatClientService).setMessengerController(messengerController);
            return user;
        }
        return null;
    }

    public void doSignUp() throws RemoteException {
        // TODO Auto-generated method stub
        SignUpClientController signUpController = ((ChatClientServiceImpl)chatClientService).getSignUpController();
        signUpController.showSignUp();
    }

    public void doSignOut() throws RemoteException {
        // TODO Auto-generated method stub
        parentFrame.removeCurrentPanel();
        chatServerService.unregisterClient(chatClientService);
    }

    public User userAuthentication(String userName, String password) throws SQLException, RemoteException {
        return chatServerService.userAuthentication(userName, password);
    }

}
