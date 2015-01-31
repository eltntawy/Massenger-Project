package com.chat.rmi;

import com.chat.model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.chat.model.User;

public interface ChatClientService extends Remote {

    public User getUser() throws RemoteException;

    public void setUser(User user) throws RemoteException;
    public void receiveMessage (Message message) throws RemoteException;
 //   public void setChatController (ChatController cont)throws RemoteException;

    public void fitchContactList() throws RemoteException;

    public void doFourceSignOut() throws RemoteException ;

}
