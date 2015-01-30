package com.chat.rmi;

import com.chat.controller.AuthenticationClientController;
import com.chat.controller.ChatClientController;
import com.chat.controller.ContactClientController;
import com.chat.controller.MessengerClientController;
import com.chat.controller.SignUpClientController;
import com.chat.controller.StatusCLientController;
import com.chat.model.Message;
import com.chat.model.MessageFile;
import com.chat.model.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClientServiceImpl extends UnicastRemoteObject implements ChatClientService {

    private User user;

    private ChatClientController chatController;

    private MessengerClientController messengerController;
    private AuthenticationClientController authenticationController;
    private SignUpClientController signUpController;
    private StatusCLientController statusController;
    private ContactClientController contactController;

    public ChatClientServiceImpl(User user) throws RemoteException {
        this.user = user;

    }

    public User getUser() throws RemoteException {
        return user;
    }

    public void setUser(User user) throws RemoteException {
        this.user = user;
    }

    public MessengerClientController getMessengerController() {
        return messengerController;
    }

    public void setMessengerController(MessengerClientController messengerController) {
        this.messengerController = messengerController;
    }

    public AuthenticationClientController getAuthenticationController() {
        return authenticationController;
    }

    public void setAuthenticationController(AuthenticationClientController authenticationController) {
        this.authenticationController = authenticationController;
    }

    public ChatClientController getChatController() {
        return chatController;
    }

    public void setChatController(ChatClientController chatController) {
        this.chatController = chatController;
    }

    public SignUpClientController getSignUpController() {
        return signUpController;
    }

    public void setSignUpController(SignUpClientController signUpController) {
        this.signUpController = signUpController;
    }

    public StatusCLientController getStatusController() {
        return statusController;
    }

    public void setStatusController(StatusCLientController statusController) {
        this.statusController = statusController;
    }

    public ContactClientController getContactController() {
        return contactController;
    }

    public void setContactController(ContactClientController contactController) {
        this.contactController = contactController;
    }

    public void fitchContactList() throws RemoteException {
        // TODO Auto-generated method stub
        if (messengerController != null) {
            messengerController.fitchContactList();
        }
    }

    public void receiveMessage(Message message) throws RemoteException {

        System.out.println("Message Received");
        chatController.receiveMessage(message);

    }

    @Override
    public void doFourceSignOut() throws RemoteException {
        authenticationController.showSignIn();

    }
     public boolean confirmRequest (User sender, String fileName, String sessionId){
        return chatController.confirmRequest(sender, fileName, sessionId);
    }
    
    public void receiveFile (MessageFile messageFile){
        chatController.receiveFile(messageFile);
    }
}
