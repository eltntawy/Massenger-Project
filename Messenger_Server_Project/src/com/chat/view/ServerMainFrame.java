/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.JOptionPane;

import com.chat.controller.ServerController;

/**
 *
 * @author eltntawy
 */
public class ServerMainFrame extends Application {

    @Override
    public void start(final Stage primaryStage) {
	Parent root = null;
	try {
	    URL url = getClass().getResource("FXMLMainServerFrame.fxml");
	    root = FXMLLoader.load(url);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	Scene firstScene = new Scene(root);
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
		    //JOptionPane.showMessageDialog(null, "Please stop server frist");
		} else {
		    primaryStage.close();
		    Platform.exit();
		    System.exit(0);
		}
	    }

	});
    }
}
