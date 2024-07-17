package Controllers;


import java.io.FileNotFoundException;

import application.LoginFile;
//import Models.User;
import application.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class LoginController {
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button signInBtn;
	@FXML
	private HBox signInHBox;
	@FXML
	private RadioButton patientRadioBtn;
	@FXML
	private RadioButton nurseRadioBtn;
	@FXML
	private RadioButton doctorRadioBtn;
	@FXML
	private Hyperlink createAccountLink;
	@FXML
	private ToggleGroup Iama;
	
	public LoginController() {
//		System.out.println("Login in contructor called");
	}
	
//	@Override
	public void initialize() {	
//		System.out.println("initializing");
		
		username.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER) {
				password.requestFocus();
			}
		});
		
		password.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				signInBtn.requestFocus();
				signInBtn.fire();
			}
		});
		
		signInBtn.setOnAction( e -> {			
			RadioButton selectedRB = (RadioButton) Iama.getSelectedToggle();
			if (selectedRB == null) {				
				Label selectOptionLabel = new Label("Please select an option on your left");
				selectOptionLabel.setTextFill(Color.color(1, 0, 0));
				signInHBox.getChildren().add(selectOptionLabel);
				return;
			}
			
			String role = selectedRB.getText();
			role = role.toLowerCase(); 	
			
			//fixes poor labeling
			if(role.contains("patient")) {
				role = "patient";
			}
			// redirect to correct view using role
			try {
				if (authorizeUser()) {
					// TODO:
					switch (role) {
					case "patient":
						ViewFactory.getViewFactoryInstance().showPatientView(e);
						break;
					case "doctor":
						ViewFactory.getViewFactoryInstance().showDoctorView(e);
						break;
					case "nurse":
						ViewFactory.getViewFactoryInstance().showNurseEntranceView(e);
						break;
					default:
//						System.out.print(role);
						break;
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		createAccountLink.setOnAction(e -> {
				ViewFactory.getViewFactoryInstance().showPatientSignUpView(e);			
		});	
		
		
	}
	
	public boolean authorizeUser() throws FileNotFoundException {
		// check if authenticated
		LoginFile.getFileInstance().accessFile(username.getText());
		RadioButton selectedRB = (RadioButton) Iama.getSelectedToggle();
		String role = selectedRB.getText(); 
		//fixes poor labeling
		if(role.contains("Patient")) {
			role = "Patient";
		}
		//checks validation if false will return false
		if(username.getText().equals(LoginFile.getFileInstance().getUserName())
			&& password.getText().equals(LoginFile.getFileInstance().getPassword()) 
			&& role.equals(LoginFile.getFileInstance().getType())) {
//				System.out.println(role);
				return true;
		}
		return false;
	}
	
}