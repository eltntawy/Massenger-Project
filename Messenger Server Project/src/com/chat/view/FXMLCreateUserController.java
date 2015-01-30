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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import com.chat.model.User;
import com.chat.service.UserService;
import com.chat.view.resource.Resource;

/**
 * FXML Controller class
 *
 * @author eltntawy
 */
public class FXMLCreateUserController implements Initializable {

    @FXML
    TextField txtFirstName;

    @FXML
    TextField txtSecondName;

    @FXML
    TextField txtEmail;

    @FXML
    TextField txtUserName;
    @FXML
    TextField txtPassword;

    @FXML
    TextField txtConfirmPassword;
    @FXML
    RadioButton radioMale;

    @FXML
    RadioButton radioFemale;

    @FXML
    Button btnCreateUser;
    @FXML
    Button btnCancel;

    ToggleGroup tg;

    final String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\" + "@([\\w]+\\.)+[\\w]+[\\w]$";

    private Scene mainScane;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

	tg = new ToggleGroup();

	radioMale.setToggleGroup(tg);
	radioFemale.setToggleGroup(tg);

	radioMale.setSelected(true);

	EventHandler<Event> event = new EventHandler<Event>() {

	    @Override
	    public void handle(Event event) {
		// TODO Auto-generated method stub
		if (txtConfirmPassword.getText().equals(txtPassword.getText()) && !"".equals(txtConfirmPassword.getText()) && !"".equals(txtPassword.getText())) {
		    txtPassword.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		    txtConfirmPassword.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");

		} else {
		    if ("".equals(txtConfirmPassword.getText()) && "".equals(txtPassword.getText())) {

		    } else {
			txtPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			txtConfirmPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		    }

		}

		if (!"".equals(txtUserName.getText())) {
		    txtUserName.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		}

		boolean isValidEmail = txtEmail.getText().matches(email_pattern);
		if (isValidEmail) {
		    txtEmail.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		} else {
		    if ("".equals(txtEmail.getText())) {

		    } else {
			txtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		    }
		}

		if (!"".equals(txtFirstName.getText())) {
		    txtFirstName.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		}

		if (!"".equals(txtSecondName.getText())) {
		    txtSecondName.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		}

		if (!"".equals(txtUserName.getText())) {
		    txtUserName.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		}
	    }
	};

	txtConfirmPassword.setOnKeyReleased(event);
	txtEmail.setOnKeyReleased(event);
	txtFirstName.setOnKeyReleased(event);
	txtSecondName.setOnKeyReleased(event);
	txtPassword.setOnKeyReleased(event);
	txtUserName.setOnKeyReleased(event);

    }

    public void btnCreateAction(ActionEvent e) throws SQLException {

	boolean isValid = true;
	if (!txtConfirmPassword.getText().equals(txtPassword.getText()) || "".equals(txtConfirmPassword.getText()) || "".equals(txtPassword.getText())) {
	    txtConfirmPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	    txtPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	    isValid = false;
	}

	if ("".equals(txtUserName.getText())) {
	    txtUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	    isValid = false;
	}

	boolean isValidEmail = txtEmail.getText().matches(email_pattern);
	if ("".equals(txtEmail.getText()) || isValidEmail) {
	    txtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	    isValid = false;
	}

	if ("".equals(txtFirstName.getText())) {
	    txtFirstName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	    isValid = false;
	}

	if ("".equals(txtSecondName.getText())) {
	    txtSecondName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	    isValid = false;
	}

	if ("".equals(txtUserName.getText())) {
	    txtUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	    isValid = false;
	}

	if (isValid) {
	    int i = UserService.insertUser(new User(0, txtUserName.getText(), txtPassword.getText(), txtFirstName.getText() + " " + txtSecondName.getText(), Resource.IMAGE_DEFAULT_USER, User.OFFLINE));
	    if (i == 0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("User Creation");
		alert.setContentText("User created successfully.");

	    }
	}
    }

    public void btnCancelAction(ActionEvent e) {

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
