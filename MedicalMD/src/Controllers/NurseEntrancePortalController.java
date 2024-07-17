package Controllers;

import java.io.IOException;

import application.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NurseEntrancePortalController {
	
    @FXML
    private Button existingPatientBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button newPatientBtn;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Button viewMessagesBtn;
    
    
    public NurseEntrancePortalController(){
    	//System.out.print("control");
    }
    public void initialize() {
    	//handler for button
    	logOutBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showLoginView(e);
		});
    	
    	//handler for existing patient
    	existingPatientBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showNurseView(e);
		});
    	
    	//handler for existing patient
    	newPatientBtn.setOnAction( e -> {
			//System.out.print("clicked");showMessageView(
			ViewFactory.getViewFactoryInstance().showRegisterPatientView(e);
		});
    	
    	//handler for viewmessage button
    	viewMessagesBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showMessageView(e);
		});
    }
    
    
}
