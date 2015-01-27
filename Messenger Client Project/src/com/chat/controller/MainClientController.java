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

        try {
            initRMIService();

            if (chatClientService != null && chatServerService != null) {

                this.frame = new MainFrame(chatClientService, chatServerService);

                frame.initSplashScreen();

                AuthenticationClientController signInController = new AuthenticationClientController(frame, chatClientService, chatServerService);

                ChatClientController chatController = new ChatClientController(chatClientService, chatServerService);
                ContactClientController contactController = new ContactClientController(chatClientService, chatServerService);

                SignUpClientController signUpController = new SignUpClientController(frame, chatClientService, chatServerService);
                StatusCLientController statusController = new StatusCLientController(frame, chatClientService, chatServerService);

                ((ChatClientServiceImpl)chatClientService).setAuthenticationController(signInController);
                ((ChatClientServiceImpl)chatClientService).setChatController(chatController);
                ((ChatClientServiceImpl)chatClientService).setContactController(contactController);
                ((ChatClientServiceImpl)chatClientService).setSignUpController(signUpController);
                ((ChatClientServiceImpl)chatClientService).setStatusController(statusController);

                Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();

                int x = (int) resolution.getWidth() - frame.getWidth() - 2;
                int y = (int) frame.getLocation().getY();
                frame.setLocation(x, y);

                frame.addCurrentPanel(signInController.getSigninPanel());
                frame.setVisible(true);
            }
        } catch (RemoteException ex) {
            System.err.println("Server Error : server not started.\nPlease start server first");
        } catch (NotBoundException ex) {
            System.err.println("Server Error : server not started.\nPlease start server first");
        }

    }

    public void initRMIService() throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry("127.0.0.1", 8888);

        ChatServerService serverService = (ChatServerService) reg.lookup("ChatService");

        ChatClientService clientService = new ChatClientServiceImpl(null);

        serverService.registerClient(clientService);

        this.chatServerService = serverService;
        this.chatClientService = clientService;
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
