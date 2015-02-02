/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import com.chat.service.ContactService;

/**
 * FXML Controller class
 *
 * @author eltntawy
 */
public class FXMLUserChartReport implements Initializable {

    @FXML
    BarChart<String, Integer> barChartReport;

    @FXML
    Button btnCancel;

    private Scene mainScane;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

	Series<String, Integer> series = new Series<String, Integer>();
	series.setName("User contact list chart");

	Map<String, Integer> resultMap;
	try {
	    resultMap = ContactService.getContactCountOfAllUsers();
	    for (String key : resultMap.keySet()) {
		series.getData().add(new XYChart.Data(key, resultMap.get(key)));
	    }
	    barChartReport.setAnimated(true);
	    barChartReport.getData().add(series);
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
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
