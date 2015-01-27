package com.chat.controller;


import com.chat.model.Status;

import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.MainFrame;
import com.chat.view.MainPanel;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTextField;

public class MessengerClientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    private MainPanel mainPanel;
    private MainFrame parentFrame;

    public MessengerClientController(MainFrame parentFrame, ChatClientService chatClientService, ChatServerService chatServerService) {

        this.chatClientService = chatClientService;
        this.chatServerService = chatServerService;

        this.parentFrame = parentFrame;
        this.mainPanel = new MainPanel(parentFrame, this);

    }

    public void doSignOut() throws RemoteException {
        // TODO Auto-generated method stub
        AuthenticationClientController signInController = new AuthenticationClientController(parentFrame, chatClientService, chatServerService);
        signInController.doSignOut();
        signInController.showSignIn();

    }

    public void showMainPanel() {
        // TODO Auto-generated method stub

        parentFrame.removeCurrentPanel();
        parentFrame.addCurrentPanel(mainPanel);
        mainPanel.initRequestContactList();

    }

    public List<User> getContactOfNameOrEmailOrUseName(String searchText) throws RemoteException, SQLException {

        // TODO Auto-generated method stub
        ContactClientController contactController = new ContactClientController(chatClientService, chatServerService);
        return contactController.getContactOfNameOrEmailOrUseName(searchText);

    }

    public void showChatFrameWith(User reciever) {

        ChatClientController chatController = new ChatClientController(chatClientService, chatServerService);
        chatController.showChatFrame(reciever);
    }

    public List<User> getContactListOfCurrentUser() throws RemoteException, SQLException {

        // TODO Auto-generated method stub
        if (((ChatClientServiceImpl)chatClientService).getUser() != null) {
            return chatServerService.getContactListOfCurrentUser(((ChatClientServiceImpl)chatClientService).getUser());
        }
        return null;
    }

    public void addRequestContact(User selectedValue) throws RemoteException {
        System.out.println(selectedValue.getUserId() + "    " + ((ChatClientServiceImpl)chatClientService).getUser().getUserId());
        if (!(selectedValue.getUserId() == ((ChatClientServiceImpl)chatClientService).getUser().getUserId())) {
            chatServerService.addRequestContact(selectedValue, ((ChatClientServiceImpl)chatClientService).getUser());
        }

    }

    public boolean checkUserId(User selectedValue) throws RemoteException {
        return selectedValue.getUserId() != ((ChatClientServiceImpl)chatClientService).getUser().getUserId();
    }

    public List<User> getRequestContactList() throws RemoteException, SQLException {

        if (((ChatClientServiceImpl)chatClientService).getUser() != null) {
            return chatServerService.getRequestContactList(((ChatClientServiceImpl)chatClientService).getUser());
        }
        return null;

    }

    public User getUser() throws RemoteException {
        return ((ChatClientServiceImpl)chatClientService).getUser();
    }

    public void addFriend(User user) throws SQLException, RemoteException {
        chatServerService.addFriend(user, ((ChatClientServiceImpl)chatClientService).getUser());

    }

    public void deleteFriendRequest(User user) throws RemoteException, SQLException {
        chatServerService.deleteFriendRequest(user, ((ChatClientServiceImpl)chatClientService).getUser());
    }

    public void updateUserImage(String Path) throws SQLException, RemoteException {
        chatServerService.updateUserImage(Path, ((ChatClientServiceImpl)chatClientService).getUser());
    }

    public JTextField getItemfocus() {
        return mainPanel.getitemFocus();
    }

    public void doChangeStaus(Status status) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        StatusCLientController statusController = new StatusCLientController(parentFrame, chatClientService, chatServerService);
        User user = ((ChatClientServiceImpl)chatClientService).getUser();
        user.setStatus(status.getStatus());
        statusController.doChangeStatus(user);

        if (user.getStatus() == User.SIGNOUT) {
            // change action on current user
            AuthenticationClientController signInController = ((ChatClientServiceImpl)chatClientService).getAuthenticationController();
            signInController.doSignOut();
            signInController.showSignIn();
        }
    }

    public void fitchContactList() {
        mainPanel.initContactList();

    }
}
