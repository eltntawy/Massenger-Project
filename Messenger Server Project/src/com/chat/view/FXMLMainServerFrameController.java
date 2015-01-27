/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import com.chat.controller.ChatServerController;
import com.chat.controller.ContactServerController;
import com.chat.controller.ServerController;
import com.chat.rmi.ChatServerServiceImpl;
import com.chat.service.ContactService;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

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

    @FXML
    private Ellipse serverStatusIndecator;
    Registry reg;

    ContactServerController contactServerController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Map<String, Integer> contactStatusMap = ContactService.getAllContactStatus();

            List<PieChart.Data> pieDataList = new ArrayList<PieChart.Data>();
            for (String key : contactStatusMap.keySet()) {
                pieDataList.add(new PieChart.Data(key, contactStatusMap.get(key)));
            }

            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(pieDataList);

            final Label caption = new Label("");
            caption.setTextFill(Color.DARKORANGE);
            //caption.setStyle("-fx-font: 24 arial;");

            for (final PieChart.Data data : onlineContactChart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY());
                                caption.setText(String.valueOf(data.getPieValue()) + "%");
                            }
                        });
            }

            onlineContactChart.setAnimated(true);
            onlineContactChart.setData(pieChartData);
            serverStatusIndecator.setFill(Paint.valueOf("Red"));
            onlineContactChart.setTitle("Online Contact Chart");
            btnStop.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMainServerFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initContactController(ContactServerController contactServerController) {
        this.contactServerController = contactServerController;
    }

    public void btnStartAction(ActionEvent e) {
        try {
            if (reg == null) {
                reg = LocateRegistry.createRegistry(8888);
            }

            ServerController serverController = new ServerController();

            ChatServerServiceImpl server = new ChatServerServiceImpl(serverController);

            ChatServerController chatController2 = new ChatServerController();

            server.setChatController(chatController2);
            reg.rebind("ChatService", server);

            serverStatusIndecator.setFill(Paint.valueOf("Green"));
            System.out.println("Server Started");
            btnStart.setDisable(true);
            btnStop.setDisable(false);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            System.err.println("Server error");

        }
    }

    public void btnStopAction(ActionEvent e) {
        if (reg != null) {
            try {
                reg.unbind("ChatService");
                serverStatusIndecator.setFill(Paint.valueOf("Red"));
                System.out.println("Server Stopped");
                btnStart.setDisable(false);
                btnStop.setDisable(true);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
