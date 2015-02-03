package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.MessageFile;
import com.chat.model.User;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import com.chat.view.ChatFrame;
import com.chat.view.NotificationPopup;
import com.chat.view.notificationDialog;
import com.test.chat.MainFrame;

import java.rmi.RemoteException;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.chat.view.MainFrame;

public class ChatClientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    private ChatFrame senderChatFrame;
    private ChatFrame receiverChatFrame;
    private Vector<ChatFrame> chatFrameVector;
    private MainFrame mainFrame;
    private User loginUser;
    private User Receiver;
    private Vector<User> usersVector;
    
    public ChatClientController( ChatClientService chatClientService,ChatServerService chatServerService) {

	this.chatServerService = chatServerService;
        this.chatClientService = chatClientService;
        chatFrameVector = new Vector<>();
        usersVector = new Vector<>();
    }
    
    public void setLoginUser(){
        	try {
	    loginUser = chatClientService.getUser();
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    
    public User getLoginUser() {
	return loginUser;
    }

    public void showChatFrame (User reciever) {
        setLoginUser();
	if(loginUser != null) {
            String chatSessionId = UUID.randomUUID().toString();
            System.out.println(chatSessionId);
            ChatClientController chatController = ((ChatClientServiceImpl)chatClientService).getChatController();
            MessengerClientController messengerController = ((ChatClientServiceImpl)chatClientService).getMessengerController();
             usersVector.addElement(loginUser);
            usersVector.addElement(reciever);
            senderChatFrame = new ChatFrame(loginUser,usersVector,messengerController, chatController,chatSessionId);
            chatController.setChatFrame(senderChatFrame);
	    senderChatFrame.setVisible(true);
	}
    }
    
    public void showReceiverChatFrame (String sessionId, User receiver){
        setLoginUser();
        if (loginUser != null){
               
            ChatClientController chatController = ((ChatClientServiceImpl)chatClientService).getChatController();
            MessengerClientController messengerController = ((ChatClientServiceImpl)chatClientService).getMessengerController();
            receiverChatFrame = new ChatFrame(loginUser,usersVector,messengerController,chatController, sessionId);
            chatController.setChatFrame(receiverChatFrame);
	    receiverChatFrame.setVisible(true);
        }
    }
    public void sendMessage(Message message) {
        
        try {
            
            chatServerService.sendMessage(message);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receiveMessage(Message message) {
       
         ChatClientController chatController = ((ChatClientServiceImpl)chatClientService).getChatController();
        for (int i = 0; i < chatController.getChatFrame().size(); i++){
            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
            if (message.getSessionId().equals(chatFrame.getSessionId())){
                chatFrame.receiveMessage(message);
                break;
            }
            else {
                System.out.println("Chat farme not active !!!!!!!!!!!");
                showReceiverChatFrame(message.getSessionId(), message.getSenderName());
                chatFrame.receiveMessage(message);
            }
        }
        
        if (chatController.getChatFrame().size() == 0){
            System.out.println("Chat farme not active !!!!!!!!!!!");
            showReceiverChatFrame(message.getSessionId(), message.getSenderName());
            receiverChatFrame.receiveMessage(message);
        }
    }
    
    public void sendFile (MessageFile messageFile){
        
        try {
            chatServerService.sendFile(messageFile);
            
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void receiveFile (MessageFile messageFile){
        ChatClientController chatController = ((ChatClientServiceImpl)chatClientService).getChatController();
        for (int i = 0; i < chatController.getChatFrame().size(); i++){
            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
            if (messageFile.getSessionID().equals(chatFrame.getSessionId())){
                chatFrame.receiveFile(messageFile);
                break;
            }
            else {
                System.out.println("Chat farme not active !!!!!!!!!!!");
                showReceiverChatFrame(messageFile.getSessionID(), messageFile.getSender());
                chatFrame.receiveFile(messageFile);
            }
        }
        
        if (chatController.getChatFrame().size() == 0){
            System.out.println("Chat farme not active !!!!!!!!!!!");
            showReceiverChatFrame(messageFile.getSessionID(), messageFile.getSender());
            receiverChatFrame.receiveFile(messageFile);
        }
    }
    public void setChatFrame (ChatFrame chatFrame){
        chatFrameVector.addElement(chatFrame);
    }
    
    public Vector<ChatFrame> getChatFrame (){
        return chatFrameVector;
    }
    
    public void removeChatFrame(String sessionId){
        for (int i = 0; i < chatFrameVector.size(); i++){
            if (sessionId.equals(chatFrameVector.elementAt(i).getSessionId())){
                chatFrameVector.removeElementAt(i);
            }
        }
    }
    
    public boolean requestSend (String fileName, Vector<User> receiver){
        try {
            if (receiverChatFrame == null){
                return chatServerService.requestSend(fileName, loginUser,receiver, senderChatFrame.getSessionId());
            }
            else if (senderChatFrame == null){
                return chatServerService.requestSend(fileName, loginUser,receiver, receiverChatFrame.getSessionId());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean confirmRequest (User sender, String fileName, String sessionId){
        
         ChatClientController chatController = ((ChatClientServiceImpl)chatClientService).getChatController();
        for (int i = 0; i < chatController.getChatFrame().size(); i++){
            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
            if (sessionId.equals(chatFrame.getSessionId())){
                return chatFrame.confirmRequest(fileName);
            }
            else {
                System.out.println("Chat farme not active !!!!!!!!!!!");
                showReceiverChatFrame(sessionId, sender);
                return chatFrame.confirmRequest(fileName);
            }
        }
        
        if (chatController.getChatFrame().size() == 0){
            System.out.println("Chat farme not active !!!!!!!!!!!");
            showReceiverChatFrame(sessionId, sender);
            return receiverChatFrame.confirmRequest(fileName);
        }
        return false;
    }

    public void showUserOnline(User u) {
	// TODO Auto-generated method stub
	new NotificationPopup(u).setVisible(true);
	((ChatClientServiceImpl)chatClientService).getMessengerController().initContactListView();
    }
}
