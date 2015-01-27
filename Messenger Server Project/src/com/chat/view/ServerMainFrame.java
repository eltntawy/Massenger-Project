/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eltntawy
 */
public class ServerMainFrame extends Application {

    @Override
    public void start(Stage primaryStage) {
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
    }
}
