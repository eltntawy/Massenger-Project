/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.chat.controller.MainServerController;
import com.chat.controller.ServerController;

/**
 *
 * @author eltntawy
 */
public class ServerMainFrame extends Application {

    @Override
    public void start(final Stage primaryStage) {
	/*Parent root = null;
	
	try {
	    root = FXMLLoader.load(getClass().getResource("FXMLMainServerFrame.fxml"));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}*/
	
	FXMLLoader fxmlLoader = new FXMLLoader();
	Parent root = null;
	try {
	    root = fxmlLoader.load(getClass().getResource("FXMLMainServerFrame.fxml").openStream());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	FXMLMainServerFrameController fxmlMainServerFrameController = (FXMLMainServerFrameController) fxmlLoader.getController();
	fxmlMainServerFrameController.setStage(primaryStage);
	
	
	Scene firstScene = new Scene(root);
	fxmlMainServerFrameController.setMainScene(firstScene);
	primaryStage.setResizable(false);
	primaryStage.setScene(firstScene);
	primaryStage.show();
	// primaryStage.setFullScreen(true);

	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

	    @Override
	    public void handle(WindowEvent event) {
		if (ServerController.getChatClientVector().size() > 0) {
		    event.consume();
		    Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText("Server closing warning");
		    alert.setContentText("Please stop server first !!");

		    alert.showAndWait();
		} else {
		    primaryStage.close();
		    Platform.exit();
		    System.exit(0);
		}
	    }

	});
    }
}
