package Controllers;

import java.io.IOException;

import application.PatientFile;
import application.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegisterPatientController {

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private Hyperlink findPatientLink;

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button registerBtn;
    
    @FXML
    private Label  errorLabel;
    
    public  RegisterPatientController () {
    	
    }
    
    public void initialize() {
    	logOutBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showLoginView(e);
		});
    	
    	findPatientLink.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showNurseView(e);
		});
    	
    	
    	//button handler for create nre user
    	registerBtn.setOnAction( e -> {
			//System.out.print("clicked");
    		
    		if(dateOfBirth.getValue() == null) {
    			errorLabel.setText("Please select a date");
    		}
    		else if(firstNameTF.getText().equals("")) {
    			errorLabel.setText("Please enter the first name");
    		}
    		else if(lastNameTF.getText().equals("")) {
    			errorLabel.setText("Please enter the last name");
    		}
    		else {
    			PatientFile.getFileInstance().setDateOfBirth(dateOfBirth.getValue().toString());
    			PatientFile.getFileInstance().setFName(firstNameTF.getText());
    			PatientFile.getFileInstance().setLName(lastNameTF.getText());
    			System.out.print(firstNameTF.getText());
    			try {
					PatientFile.getFileInstance().createFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			errorLabel.setText("Patient Created Click ->");
    		}
    	});
    }
}
