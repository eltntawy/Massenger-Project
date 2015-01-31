package com.chat.controller;

import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatClientServiceImpl;
import com.chat.rmi.ChatServerService;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.chat.view.MainFrame;

public class MainClientController {

    private ChatServerService chatServerService;
    private ChatClientService chatClientService;
    private MainFrame frame;
    private ChatClientController chatController;

    /**
     *
     */
    public MainClientController() {

        

                this.frame = new MainFrame();

                frame.initSplashScreen();

                Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();

                int x = (int) resolution.getWidth() - frame.getWidth() - 2;
                int y = (int) frame.getLocation().getY();
                frame.setLocation(x, y);
                AuthenticationClientController  signInController = new AuthenticationClientController(frame);
                frame.addCurrentPanel(signInController.getSigninPanel());
                frame.setVisible(true);
                

    }

    public ChatServerService getChatServerService() {
        return chatServerService;
    }

    public ChatClientService getChatClientService() {
        return chatClientService;

    }

    public static void main(String args []) {
        new MainClientController();
    }
}
