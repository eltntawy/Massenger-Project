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
import com.xml.XMLJAXB;

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

    public ChatClientController(ChatClientService chatClientService, ChatServerService chatServerService) {

        this.chatServerService = chatServerService;
        this.chatClientService = chatClientService;
        chatFrameVector = new Vector<>();
        usersVector = new Vector<>();
    }

    public void setLoginUser() {
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

    public void showChatFrame(User reciever) {
        setLoginUser();
        int counter = 0;
        if (loginUser != null) {
            String chatSessionId = UUID.randomUUID().toString();
            System.out.println(chatSessionId);
            ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
            MessengerClientController messengerController = ((ChatClientServiceImpl) chatClientService).getMessengerController();
            usersVector.removeAllElements();
            usersVector.addElement(loginUser);
            usersVector.addElement(reciever);
            senderChatFrame = new ChatFrame(loginUser, usersVector, messengerController, chatController, chatSessionId);
            chatController.setChatFrame(senderChatFrame);
            senderChatFrame.setVisible(true);
        }
    }

    public void showReceiverChatFrame(String sessionId, User receiver, Vector<User> UsersVector) {
        setLoginUser();
        if (loginUser != null) {

            ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
            MessengerClientController messengerController = ((ChatClientServiceImpl) chatClientService).getMessengerController();
            receiverChatFrame = new ChatFrame(loginUser, UsersVector, messengerController, chatController, sessionId);
            chatController.setChatFrame(receiverChatFrame);
            receiverChatFrame.setVisible(true);
        }
    }

    public void sendMessage(Message message) {

	ChatClientController chatController = ((ChatClientServiceImpl)chatClientService).getChatController();


        try {

            chatServerService.sendMessage(message);

            for(User receiver : message.getUsersVector()) {
                if(!receiver.equals(chatController.getLoginUser())) {
            	com.xml.Message xmlMsg = new com.xml.Message(message.getSenderName(), 
                        	receiver, message.getMessage(), message.getSessionId());
                        XMLJAXB.appendMsg(xmlMsg);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMessage(Message message) {

        boolean chatFrameOpen = false;
        ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
        for (int i = 0; i < chatController.getChatFrame().size(); i++) {
            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
            if (message.getSessionId().equals(chatFrame.getSessionId())) {
                chatFrame.receiveMessage(message);
                chatFrameOpen = true;
                break;
            }
//            else {
//                System.out.println("Chat farme not active !!!!!!!!!!!");
//                showReceiverChatFrame(message.getSessionId(), message.getSenderName(), message.getUsersVector());
//                chatFrame.receiveMessage(message);
//            }
        }
        if (chatFrameOpen == false) {
            System.out.println("Chat farme not active !!!!!!!!!!!");
            showReceiverChatFrame(message.getSessionId(), message.getSenderName(), message.getUsersVector());
            receiverChatFrame.receiveMessage(message);
        }

        
        for(User receiver : message.getUsersVector()) {
            if(receiver.equals(chatController.getLoginUser())) {
        	com.xml.Message xmlMsg = new com.xml.Message(receiver,message.getSenderName(), 
                    	 message.getMessage(), message.getSessionId());
                    XMLJAXB.appendMsg(xmlMsg);
            }
        }
    }
    
    /*public void sendFile (MessageFile messageFile){
       

        if (chatController.getChatFrame().size() == 0) {
            System.out.println("Chat farme not active !!!!!!!!!!!");
            showReceiverChatFrame(message.getSessionId(), message.getSenderName(), message.getUsersVector());
            receiverChatFrame.receiveMessage(message);
        }
    }*/

    public void sendFile(MessageFile messageFile) {

        try {
            chatServerService.sendFile(messageFile);

        } catch (RemoteException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveFile(MessageFile messageFile) {
	boolean chatFrameOpen = false;
        ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
        for (int i = 0; i < chatController.getChatFrame().size(); i++) {
            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
            if (messageFile.getSessionID().equals(chatFrame.getSessionId())) {
                chatFrame.receiveFile(messageFile);
                chatFrameOpen = true;
                break;
            } else {
                System.out.println("Chat farme not active !!!!!!!!!!!");
                showReceiverChatFrame(messageFile.getSessionID(), messageFile.getSender(), messageFile.getUsersVector());
                chatFrame.receiveFile(messageFile);
            }
        }

        if (chatController.getChatFrame().size() == 0) {
            System.out.println("Chat farme not active !!!!!!!!!!!");
            showReceiverChatFrame(messageFile.getSessionID(), messageFile.getSender(), messageFile.getUsersVector());
            receiverChatFrame.receiveFile(messageFile);
        }
    }

    public void setChatFrame(ChatFrame chatFrame) {
        chatFrameVector.addElement(chatFrame);
    }

    public Vector<ChatFrame> getChatFrame() {
        return chatFrameVector;
    }

    public void removeChatFrame(String sessionId) {
        for (int i = 0; i < chatFrameVector.size(); i++) {
            if (sessionId.equals(chatFrameVector.elementAt(i).getSessionId())) {
                chatFrameVector.removeElementAt(i);
            }
        }
    }

    public boolean requestSend(String fileName, Vector<User> receiver) {
        try {
            if (receiverChatFrame == null) {
                return chatServerService.requestSend(fileName, loginUser, receiver, senderChatFrame.getSessionId());
            } else if (senderChatFrame == null) {
                return chatServerService.requestSend(fileName, loginUser, receiver, receiverChatFrame.getSessionId());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean confirmRequest(User sender, String fileName, String sessionId, Vector<User> userVector) {

        ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
        for (int i = 0; i < chatController.getChatFrame().size(); i++) {
            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
            if (sessionId.equals(chatFrame.getSessionId())) {
                return chatFrame.confirmRequest(fileName, userVector);
//                chatFrameOpen = true;
//                break;
            } else {
                System.out.println("Chat farme not active !!!!!!!!!!!");
                showReceiverChatFrame(sessionId, sender, userVector);
                return chatFrame.confirmRequest(fileName, userVector);
            }
        }

        //if (chatController.getChatFrame().size() == 0) {
            System.out.println("Chat farme not active !!!!!!!!!!!");
            showReceiverChatFrame(sessionId, sender, userVector);
            return receiverChatFrame.confirmRequest(fileName, userVector);
  //      }
//        ChatClientController chatController = ((ChatClientServiceImpl) chatClientService).getChatController();
//        for (int i = 0; i < chatController.getChatFrame().size(); i++) {
//            ChatFrame chatFrame = chatController.getChatFrame().elementAt(i);
//            if (sessionId.equals(chatFrame.getSessionId())) {
//                return chatFrame.confirmRequest(fileName, userVector);
//            } else {
//                System.out.println("Chat farme not active !!!!!!!!!!!");
//                showReceiverChatFrame(sessionId, sender, userVector);
//                return chatFrame.confirmRequest(fileName, userVector);
//            }
//        }
//
//        if (chatController.getChatFrame().size() == 0) {
//            System.out.println("Chat farme not active !!!!!!!!!!!");
//            showReceiverChatFrame(sessionId, sender, userVector);
//            return receiverChatFrame.confirmRequest(fileName, userVector);
//        }
//        return false;
    }

    public void showUserOnline(User u) {
        // TODO Auto-generated method stub
        new NotificationPopup(u).setVisible(true);
        ((ChatClientServiceImpl) chatClientService).getMessengerController().initContactListView();
    }
}
