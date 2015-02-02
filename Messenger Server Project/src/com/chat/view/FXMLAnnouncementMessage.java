/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import com.chat.controller.ChatServerController;
import com.chat.model.Message;
import com.chat.model.User;
import com.chat.view.resource.Resource;

/**
 * FXML Controller class
 *
 * @author eltntawy
 */
public class FXMLAnnouncementMessage implements Initializable {

    @FXML
    TextArea txtMessage;

    

    @FXML
    Button btnSendMessage;
   
    @FXML
    Button btnCancel;

    private Scene mainScane;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

	

    }

    public void btnSendMessageAction(ActionEvent e) throws SQLException {

	User sender = new User(0, "Administrator", "", "Administrator", Resource.IMAGE_DEFAULT_USER, 0);
	Message message = new Message(sender , null, txtMessage.getText(), UUID.randomUUID().toString());
	try {
	    ChatServerController.sendMessageForAllClient(message);
	    txtMessage.setText("");
	} catch (RemoteException e1) {
	    // TODO Auto-generated catch block
	    Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Send Message");
		alert.setContentText("Server can not send message right now please check the server is running first.");
		alert.show();
	    e1.printStackTrace();
	}
    }

    public void btnCancelAction(ActionEvent e) throws SQLException {

	primaryStage.setScene(mainScane);
    }
    

    public void setStage(Stage primaryStage) {
	// TODO Auto-generated method stub
	this.primaryStage = primaryStage;
    }

    public void setMainScene(Scene mainScene) {
	this.mainScane = mainScene;
    }
}
