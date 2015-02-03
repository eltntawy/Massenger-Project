package com.chat.rmi;

import com.chat.model.Message;
import com.chat.model.MessageFile;
import com.chat.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatClientService extends Remote {

    public User getUser() throws RemoteException;

    public void setUser(User user) throws RemoteException;
    
    public void receiveMessage (Message message) throws RemoteException;
    
    public void receiveFile (MessageFile messageFile) throws RemoteException;
    
    public boolean confirmRequest (User sender, String fileName, String sessionId, Vector<User> usersVector) throws RemoteException;

    public void fitchContactList() throws RemoteException;

    public void doFourceSignOut() throws RemoteException ;

    public void showUserOnline(User u) throws RemoteException;

}
