/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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

//	Message message = new Message(senderName, receiverName, txtMessage.getText(), UUID.randomUUID().toString());
//	ChatServerController.sendMessageForAllClient(message);
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
