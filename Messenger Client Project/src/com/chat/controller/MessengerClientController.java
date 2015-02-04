package com.chat.controller;

import com.chat.model.Status;
import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.ChatFrame;
import com.chat.view.MainFrame;
import com.chat.view.MainPanel;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
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

    public MainPanel getPanel() {
        return mainPanel;
    }

    public void doSignOut() throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        AuthenticationClientController signInController = new AuthenticationClientController(parentFrame, chatClientService, chatServerService);
        signInController.doSignOut();
//        ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
//        Vector<ChatFrame> chatFrameVector = chatController.getChatFrame();
//        for (int i = 0; i < chatFrameVector.size(); i++){
//            chatFrameVector.elementAt(i).setVisible(false);
//        }
//        chatFrameVector.removeAllElements();
        signInController.showSignIn();

    }

    public void showMainPanel() {
        // TODO Auto-generated method stub

        parentFrame.removeCurrentPanel();
        parentFrame.addCurrentPanel(mainPanel);
        mainPanel.initRequestContactList();

    }

    public void getContactOfNameOrEmailOrUseName(String searchText) throws RemoteException, SQLException {

        // TODO Auto-generated method stub
        ContactClientController contactController = ((ChatClientServiceImpl) chatClientService).getContactController();
        contactController.getContactOfNameOrEmailOrUseName(searchText);

    }

    public void showChatFrameWith(User reciever) {

        int counter = 0;
        int receiversNumber = 0;
        int framesNumber = 0;
        boolean singleChat = false;
        ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
        //check if chat frame is opened with this user
       
        for (int i = 0; i < chatController.getChatFrame().size(); i++) {
            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
            Vector<User> receiversVector = chatFrame.getReceiverVector();

            //for single chat
            if (receiversVector.size() == 2) {
                for (int j = 0; j < receiversVector.size(); j++) {
                    if (!receiversVector.elementAt(j).getUserName().equals(reciever.getUserName())) {
                        receiversNumber++;
                    }
                }
                if (receiversNumber == receiversVector.size()) {
                    framesNumber++;
                }
                singleChat = true;
            }
        }
         if ((chatController.getChatFrame().size() == 0 || singleChat == false) && reciever.getStatus() == User.AVAILABLE ) {
            chatController.showChatFrame(reciever);
        }
        if (framesNumber == chatController.getChatFrame().size() && reciever.getStatus() == User.AVAILABLE) {
            chatController.showChatFrame(reciever);

        }

    }

    public List<User> getContactListOfCurrentUser() throws RemoteException, SQLException {

        // TODO Auto-generated method stub
        if (((ChatClientServiceImpl) chatClientService).getUser() != null) {
            return chatServerService.getContactListOfCurrentUser(((ChatClientServiceImpl) chatClientService).getUser());
        }
        return null;
    }

    public void initContactListView() {
        mainPanel.initContactList();
    }

    public void initContactListView(List<User> retList) {

        mainPanel.initContactList(retList);
    }

    public void addRequestContact(User selectedValue) throws RemoteException {
        System.out.println(selectedValue.getUserId() + "    " + ((ChatClientServiceImpl) chatClientService).getUser().getUserId());
        if (!(selectedValue.getUserId() == ((ChatClientServiceImpl) chatClientService).getUser().getUserId())) {
            chatServerService.addRequestContact(selectedValue, ((ChatClientServiceImpl) chatClientService).getUser());
        }

    }

    public boolean checkUserId(User selectedValue) throws RemoteException {
        boolean test = selectedValue.getUserId() != ((ChatClientServiceImpl) chatClientService).getUser().getUserId();
        return test;
    }

    public List<User> getRequestContactList() throws RemoteException, SQLException {

        if (((ChatClientServiceImpl) chatClientService).getUser() != null) {
            return chatServerService.getRequestContactList(((ChatClientServiceImpl) chatClientService).getUser());
        }
        return null;

    }

    public User getUser() throws RemoteException {
        return ((ChatClientServiceImpl) chatClientService).getUser();
    }

    public void addFriend(User user) throws SQLException, RemoteException {
        chatServerService.addFriend(user, ((ChatClientServiceImpl) chatClientService).getUser());

    }

    public void deleteFriendRequest(User user) throws RemoteException, SQLException {
        chatServerService.deleteFriendRequest(user, ((ChatClientServiceImpl) chatClientService).getUser());
    }

    public void updateUserImage(String Path) throws SQLException, RemoteException {
        chatServerService.updateUserImage(Path, ((ChatClientServiceImpl) chatClientService).getUser());
    }

    public JTextField getItemfocus() {
        return mainPanel.getitemFocus();
    }

    public void doChangeStaus(Status status) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        StatusCLientController statusController = new StatusCLientController(parentFrame, chatClientService, chatServerService);
        User user = ((ChatClientServiceImpl) chatClientService).getUser();
        user.setStatus(status.getStatus());
        statusController.doChangeStatus(user);

        if (user.getStatus() == User.SIGNOUT) {
            // change action on current user
            AuthenticationClientController signInController = ((ChatClientServiceImpl) chatClientService).getAuthenticationController();
            signInController.doSignOut();
            signInController.showSignIn();
        }
    }

    public void fitchContactList() {
        mainPanel.initContactList();

    }

    public void DeleteContactFromUser(User user) throws RemoteException, SQLException {
        chatServerService.DeleteContactFromUser(user, ((ChatClientServiceImpl) chatClientService).getUser());
    }

    public List<User> getContactOfNameOrEmailOrUseNameList(String searchText) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        ContactClientController contactController = ((ChatClientServiceImpl) chatClientService).getContactController();
        return contactController.getContactOfNameOrEmailOrUseNameList(searchText);

    }

    public void initContactListForOtherUser(User user) throws RemoteException {
        chatServerService.initContactListForOtherUser(user);
    }

    public boolean checkRequestExitance(User user) throws RemoteException, SQLException {
        return chatServerService.checkRequestExistance(user, ((ChatClientServiceImpl) chatClientService).getUser());
    }

    public boolean isFriendOfUser(User user, User friend) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        return chatServerService.isFriendOfUser(user, friend);
    }
}
