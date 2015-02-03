package com.chat.rmi;

import com.chat.controller.AddFriendController;
import com.chat.controller.ChatServerController;
import com.chat.controller.ContactServerController;
import com.chat.controller.RequestContactListController;
import com.chat.controller.ServerController;
import com.chat.controller.StatusServerController;
import com.chat.model.Message;
import com.chat.model.MessageFile;
import com.chat.model.User;
import com.chat.service.ContactService;
import com.chat.service.UserService;
import com.chat.service.addFriendRequestService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServerServiceImpl extends UnicastRemoteObject implements ChatServerService {

    ServerController serverController;
    private ChatServerController chatController;

    public ChatServerController getChatController() {
        return chatController;
    }

    public void setChatController(ChatServerController chatController) {
        this.chatController = chatController;
    }

    public ChatServerServiceImpl(ServerController serverController) throws RemoteException {
        this.serverController = serverController;

    }

    @Override
    public void registerClient(ChatClientService chatClientService) throws RemoteException {

        serverController.registerClient(chatClientService);
        if (chatClientService.getUser() != null) {
            System.out.println("client register" + chatClientService.getUser().getUserName());
        }
    }

    @Override
    public void unregisterClient(ChatClientService chatClientService) throws RemoteException {
        serverController.unregisterClient(chatClientService);
        if (chatClientService.getUser() != null) {
            System.out.println("client unregister" + chatClientService.getUser().getUserName());
        }

    }

    @Override
    public List<User> getContactOfNameOrEmailOrUseName(String searchText) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        ContactServerController contactController = new ContactServerController();
        return contactController.getContactOfNameOrEmailOrUseName(searchText);

    }

    @Override
    public List<User> getContactListOfCurrentUser(User u) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        ContactServerController contactController = new ContactServerController();
        return contactController.getContactListOfCurrentUser(u);
    }

    @Override
    public User userAuthentication(String userName, String password) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        return UserService.userAuthentication(userName, password);
    }

    @Override
    public void addRequestContact(User selectedValue, User user) throws RemoteException {
        if (user != null) {
            addFriendRequestService.addRequestContact(selectedValue, user);
        }
    }

    @Override
    public List<User> getRequestContactList(User user) throws SQLException, RemoteException {
        RequestContactListController request = new RequestContactListController();
        return request.getRequestContactList(user);
    }

    @Override
    public void addFriend(User user, User mainUser) throws SQLException, RemoteException {
        AddFriendController add = new AddFriendController();
        add.addFriend(user, mainUser);

    }

    @Override
    public void deleteFriendRequest(User user, User user0) throws SQLException, RemoteException {
        AddFriendController add = new AddFriendController();
        add.deleteFriendRequest(user, user0);
    }

    @Override
    public void updateUserImage(String Path, User user) throws SQLException, RemoteException {
        ContactServerController.updateUserImage(Path, user);
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {

        System.out.println(message.getSenderName());
        System.out.println(message.getReceiverName());
        System.out.println(message.getMessage());
        chatController.sendMessage(message);
    }

    @Override
    public int updateUserStatus(User user) throws SQLException, RemoteException {
        // TODO Auto-generated method stub
        StatusServerController statusController = new StatusServerController();
        return statusController.doChangeStatus(user);

    }

    public void unregisterAllClient() {
        serverController.unregisterAllClient();
    }

    @Override
    public void DeleteContactFromUser(User user, User Mainuser) throws SQLException, RemoteException {
        AddFriendController remove = new AddFriendController();
        remove.DeleteContactFromUser(user, Mainuser);
    }

    @Override
    public int doSignup(User user) throws SQLException {

        // throw new UnsupportedOpera tionException("Not supported yet.");
        // //To change body of generated methods, choose Tools | Templates.
        if (user != null) {
            try {
                return UserService.insertUser(user);
            } catch (SQLException e) {
                return 0;
            }
        } else {
            return 0;
        }

    }

    @Override
    public void doSignout(User user) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        if (user != null) {
            UserService.doSignoutUser(user);
        }
    }

    public void sendFile(MessageFile messageFile) throws RemoteException {
        chatController.sendFile(messageFile);
    }

    public boolean requestSend(String fileName, User sender, User receiver, String sessionId) {
        return chatController.requestSend(fileName, sender, receiver, sessionId);
    }

    @Override
    public void showMyStatus(User user) throws RemoteException, SQLException {
        // TODO Auto-generated method stub
        for (ChatClientService client : ServerController.getChatClientVector()) {
            for (User contact : ContactService.getContactListOfCurrentUser(client.getUser())) {
                if (user != null && user.getUserName().equals(contact.getUserName())) {
                    client.showUserOnline(user);
                }

            }
        }
    }

    @Override
    public void initContactListForOtherUser(User user) throws RemoteException {
        AddFriendController.initContactListForOtherUser(user);
    }

    @Override
    public boolean checkRequestExistance(User user, User user0) throws RemoteException, SQLException {
        AddFriendController add = new AddFriendController();
        boolean exist = add.checkRequestExistance(user, user0);
        return exist;
    }

}
