package com.chat.controller;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.JPanel;

import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.MainFrame;
import com.chat.view.SignInPanel;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        // parentFrame.remove(null);

    }

    public void initRMIService() throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry("127.0.0.1", 8888);

        ChatServerService serverService = (ChatServerService) reg.lookup("ChatService");

        ChatClientService clientService = new ChatClientServiceImpl(null);

        serverService.registerClient(clientService);

        this.chatServerService = serverService;
        this.chatClientService = clientService;
    }

    public AuthenticationClientController(MainFrame frame) {

        this.parentFrame=frame;
                
        this.signInPanel = new SignInPanel(parentFrame, this);
        

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

    public User doSignIn(String userName, String password) throws RemoteException, SQLException, NotBoundException {
        // TODO Auto-generated method stub
            initRMIService();

            if (chatClientService != null && chatServerService != null) {
                AuthenticationClientController signInController = new AuthenticationClientController(parentFrame, chatClientService, chatServerService);

                ChatClientController chatController = new ChatClientController(chatClientService, chatServerService);
                ContactClientController contactController = new ContactClientController(chatClientService, chatServerService);

                SignUpClientController signUpController = new SignUpClientController(parentFrame, chatClientService, chatServerService);
                StatusCLientController statusController = new StatusCLientController(parentFrame, chatClientService, chatServerService);

                ((ChatClientServiceImpl) chatClientService).setAuthenticationController(signInController);
                ((ChatClientServiceImpl) chatClientService).setChatController(chatController);
                ((ChatClientServiceImpl) chatClientService).setContactController(contactController);
                ((ChatClientServiceImpl) chatClientService).setSignUpController(signUpController);
                ((ChatClientServiceImpl) chatClientService).setStatusController(statusController);
                

            }
        User user = userAuthentication(userName, password);
        if (user != null) {

            chatClientService.setUser(user);
            MessengerClientController messengerController = new MessengerClientController(parentFrame, chatClientService, chatServerService);
            messengerController.showMainPanel();
            messengerController.getContactListOfCurrentUser();
            messengerController.getRequestContactList();
            ((ChatClientServiceImpl) chatClientService).setMessengerController(messengerController);
            return user;
        }
        return null;
    }

    public void doSignUp() throws RemoteException {
        // TODO Auto-generated method stub
        SignUpClientController signUpController = new SignUpClientController(parentFrame, chatClientService, chatServerService);
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
