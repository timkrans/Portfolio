package Controllers;

import java.io.IOException;

import application.LoginFile;
import application.PatientFile;
import application.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PatientSignUpController {

    @FXML
    private DatePicker birthDate;

    @FXML
    private Button createAccountBtn;

    @FXML
    private PasswordField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password2;

    @FXML
    private Hyperlink signInHyperlink;

    @FXML
    private PasswordField username;
    
    @FXML
    private Label errorLabel;
    
    //handler for log out
    public void initialize(){
    	//handler for log out
    	signInHyperlink.setOnAction(e->{
    		ViewFactory.getViewFactoryInstance().showLoginView(e);
    	});
    	
    	//handler for create account
    	createAccountBtn.setOnAction(e->{
    		//validates inputs
    		if(birthDate.getValue() == null) {
    			errorLabel.setText("Please select a date");
    		}
    		else if(firstName.getText().equals("")) {
    			errorLabel.setText("Please enter the first name");
    		}
    		else if(lastName.getText().equals("")) {
    			errorLabel.setText("Please enter the last name");
    		}
    		else if(password.getText().equals("")) {
    			errorLabel.setText("Please enter password");
    		}
    		else if(!password2.getText().equals(password.getText())) {
    			errorLabel.setText("Passwords do not match");
    		}
    		else if(username.getText().equals("")) {
    			errorLabel.setText("Please enter a username");
    		}
    		else {
    			LoginFile.getFileInstance().setDateOfBirth(birthDate.getValue().toString());
    			LoginFile.getFileInstance().setFName((firstName.getText()));
    			LoginFile.getFileInstance().setLName((lastName.getText()));
    			LoginFile.getFileInstance().setType("Patient");
    			LoginFile.getFileInstance().setPassword(password.getText());
    			LoginFile.getFileInstance().setUserName(username.getText());
    			PatientFile.getFileInstance().setDateOfBirth(birthDate.getValue().toString());
    			PatientFile.getFileInstance().setFName((firstName.getText()));
    			PatientFile.getFileInstance().setLName((lastName.getText()));
    			PatientFile.getFileInstance().setEmail(email.getText());
    			try {
					LoginFile.getFileInstance().creatFile();
					//System.out.print(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			try {
					PatientFile.getFileInstance().contactChangeFileEdit();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			//send it to the patient view
    			ViewFactory.getViewFactoryInstance().showPatientView(e);
    		}
    	});
    }

}
