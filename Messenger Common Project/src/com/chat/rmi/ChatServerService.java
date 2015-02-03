package com.chat.rmi;

import com.chat.model.Message;
import com.chat.model.MessageFile;
import com.chat.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface ChatServerService extends Remote {

    public void sendMessage(Message message) throws RemoteException;

    public void sendFile(MessageFile messageFile) throws RemoteException;

    public boolean requestSend(String fileName, User sender, User receiver, String sessionId) throws RemoteException;

    public void registerClient(ChatClientService chatClientService) throws RemoteException;

    public void unregisterClient(ChatClientService chatClientService) throws RemoteException;

    // public void addRequestContact(User user) throws RemoteException;
    public List<User> getContactOfNameOrEmailOrUseName(String searchText) throws RemoteException, SQLException;

    public List<User> getContactListOfCurrentUser(User u) throws RemoteException, SQLException;

    public User userAuthentication(String userName, String password) throws SQLException, RemoteException;

    public int updateUserStatus(User user) throws SQLException, RemoteException;

    public void addRequestContact(User selectedValue, User user) throws RemoteException;

    public List<User> getRequestContactList(User user) throws SQLException, RemoteException;

    public void addFriend(User user, User mainUser) throws SQLException, RemoteException;

    public void deleteFriendRequest(User user, User user0) throws SQLException, RemoteException;

    public void updateUserImage(String Path, User user) throws SQLException, RemoteException;

    public void DeleteContactFromUser(User user, User user0) throws SQLException, RemoteException;

    public int doSignup(User user) throws RemoteException, SQLException;

    public void doSignout(User user) throws RemoteException, SQLException;

    public void showMyStatus(User user) throws RemoteException, SQLException;

    public void initContactListForOtherUser(User user) throws RemoteException;

    public boolean checkRequestExistance(User user, User user0) throws RemoteException, SQLException;

}
