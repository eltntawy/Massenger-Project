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

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

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
    private Button btnCreateUser;

    @FXML
    private Button btnAnnouncementMessage;

    @FXML
    private Button btnShowContactCount;

    @FXML
    private PieChart onlineContactChart;

    @FXML
    private Ellipse serverStatusIndecator;
    Registry reg;

    ContactServerController contactServerController;

    ChatServerServiceImpl server;

    private Stage primaryStage;

    private Scene mainScene;

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

	    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(pieDataList);

	    final Label caption = new Label("");
	    caption.setTextFill(Color.DARKORANGE);
	    caption.setStyle("-fx-font: 24 arial;");

	    onlineContactChart.setAnimated(true);
	    // onlineContactChart.setData(pieChartData);
	    serverStatusIndecator.setFill(Paint.valueOf("Red"));
	    onlineContactChart.setTitle("Online Contact Chart");
	    btnStop.setDisable(true);

	} catch (SQLException ex) {
	    Logger.getLogger(FXMLMainServerFrameController.class.getName()).log(Level.SEVERE, null, ex);
	}

    }

    public void initPieChart() throws SQLException {

	Map<String, Integer> contactStatusMap = ContactService.getAllContactStatus();
	List<PieChart.Data> pieDataList = new ArrayList<PieChart.Data>();
	for (String key : contactStatusMap.keySet()) {
	    pieDataList.add(new PieChart.Data(key, contactStatusMap.get(key)));
	}

	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(pieDataList);
	onlineContactChart.setData(pieChartData);

    }

    public void initContactController(ContactServerController contactServerController) {
	this.contactServerController = contactServerController;
    }

    public void btnStartAction(ActionEvent e) throws SQLException {
	try {

	    reg = LocateRegistry.createRegistry(8888);

	    ServerController serverController = new ServerController();

	    server = new ChatServerServiceImpl(serverController);

	    server.unregisterAllClient();
	    
	    ChatServerController chatController2 = new ChatServerController();

	    server.setChatController(chatController2);
	    reg.rebind("ChatService", server);

	    serverStatusIndecator.setFill(Paint.valueOf("Green"));
	    System.out.println("Server Started");
	    btnStart.setDisable(true);
	    btnStop.setDisable(false);

	    initPieChart();

	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    System.err.println("Server error");

	}
    }

    public void btnStopAction(ActionEvent e) {
	if (reg != null) {
	    try {
		server.unregisterAllClient();
		reg.unbind("ChatService");
		UnicastRemoteObject.unexportObject(reg, true);
		UnicastRemoteObject.unexportObject(server, true);
		serverStatusIndecator.setFill(Paint.valueOf("Red"));

		btnStart.setDisable(false);
		btnStop.setDisable(true);
		System.out.println("Server Stopped");
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    } catch (NotBoundException ex) {
		ex.printStackTrace();
	    }
	}
    }

    public void btnAddUserAction(ActionEvent e) throws SQLException {

	FXMLLoader fxmlLoader = new FXMLLoader();
	Parent root = null;
	try {
	    root = fxmlLoader.load(getClass().getResource("FXMLCreateUser.fxml").openStream());
	    FXMLCreateUserController fxmlCreateUserController = (FXMLCreateUserController) fxmlLoader.getController();
	    fxmlCreateUserController.setStage(primaryStage);
	    fxmlCreateUserController.setMainScene(mainScene);

	    primaryStage.setScene(new Scene(root));

	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

    }

    public void btnSendAnnouncementMessageAction(ActionEvent e) {
	FXMLLoader fxmLoader = new FXMLLoader();
	Parent root = null;
	try {
	    root = fxmLoader.load(getClass().getResource("FXMLAnnouncementMessage.fxml").openStream());
	    FXMLAnnouncementMessage announcementMessage = (FXMLAnnouncementMessage) fxmLoader.getController();
	    announcementMessage.setMainScene(mainScene);
	    announcementMessage.setStage(primaryStage);

	    primaryStage.setScene(new Scene(root));

	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

    }

    public void btnShowContactCountAction(ActionEvent e) {
	FXMLLoader fxmLoader = new FXMLLoader();
	Parent root = null;
	try {
	    root = fxmLoader.load(getClass().getResource("FXMLUserChartReport.fxml").openStream());
	    FXMLUserChartReport userChartReport = (FXMLUserChartReport) fxmLoader.getController();
	    userChartReport.setMainScene(mainScene);
	    userChartReport.setStage(primaryStage);

	    primaryStage.setScene(new Scene(root));

	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }

    public void setStage(Stage primaryStage) {
	// TODO Auto-generated method stub
	this.primaryStage = primaryStage;
    }

    public void setMainScene(Scene mainScene) {
	this.mainScene = mainScene;
    }
}
