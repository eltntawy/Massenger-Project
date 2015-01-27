/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import com.chat.controller.ChatServerController;
import com.chat.controller.ServerController;
import com.chat.rmi.ChatServerServiceImpl;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;

/**
 * FXML Controller class
 *
 * @author eltntawy
 */
public class FXMLMainServerFrameController implements Initializable {

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;
    @FXML
    private PieChart onlineContactChart;

    Registry reg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void btnStartAction(ActionEvent e) {
        try {
            reg = LocateRegistry.createRegistry(8888);

            ServerController serverController = new ServerController();

            ChatServerServiceImpl server = new ChatServerServiceImpl(serverController);

            ChatServerController chatController2 = new ChatServerController();

            server.setChatController(chatController2);
            reg.rebind("ChatService", server);
            
            
            
            System.out.println("Server Started");
        } catch (RemoteException ex) {
            ex.printStackTrace();
            System.out.println("Server Started");
            fx.showErrorDialog(null, "Server is already Running!", "Error", "Start Server");
        }
    }

    public void btnStopAction(ActionEvent e) {
        if (reg != null) {
            try {
                reg.unbind("ChatService");
                System.out.println("Server Stopped");
            } catch (RemoteException ex) {
                Dialogs.showErrorDialog(null, "Error while stopping the server!", "Error", "Stop Server");
            } catch (NotBoundException ex) {
                Dialogs.showErrorDialog(null, "Error while stopping the server!", "Error", "Stop Server");
            }
        }
    }
}
