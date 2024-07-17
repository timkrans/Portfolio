package Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.ViewFactory;
import application.PatientFile;
import application.PatientVisitFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NursePortalController {

    @FXML
    private TextArea allergiesTA;

    @FXML
    private DatePicker birthDate;

    @FXML
    private TextField bloodPressureTF;

    @FXML
    private Button findPatientBtn;

    @FXML
    private TextField firstName;

    @FXML
    private TextArea healthConcernsTA;

    @FXML
    private TextField heightTF;

    @FXML
    private TextField immunizationList;

    @FXML
    private TextField lastName;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button messagePatientBtn;

    @FXML
    private Label patientName;

    @FXML
    private TextField prevHealthList;

    @FXML
    private TextField prevMedList;

    @FXML
    private Hyperlink registerPatientBtn;

    @FXML
    private Button saveInfoBtn;

    @FXML
    private TextField tempTF;

    @FXML
    private TextField weightTF;
    
    @FXML
    private Label errorLabel;

    public NursePortalController(){
    	//.out.print("control");
    }
    
    public void initialize() {
    	//logout button handler
    	logOutBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showLoginView(e);
		});
    	
    	//view message
    	messagePatientBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showMessageView(e);
		});
    	
    	//register patient
    	registerPatientBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showRegisterPatientView(e);
		});
    	
    	saveInfoBtn.setOnAction( e-> {
    		if(firstName.getText().equals(PatientFile.getFileInstance().getFName())
					&& lastName.getText().equals(PatientFile.getFileInstance().getLName())
					&&birthDate.getValue().toString().equals(PatientFile.getFileInstance().getDateOfBirth()))
			{
    			//setting all values for visit
    			PatientVisitFile.getFileInstance().setFName(firstName.getText());
    			PatientVisitFile.getFileInstance().setLName(lastName.getText());
    			PatientVisitFile.getFileInstance().setDateOfBirth(birthDate.getValue().toString());
    			PatientVisitFile.getFileInstance().setAllergies(allergiesTA.getText());
    			PatientVisitFile.getFileInstance().setBloodPressure(bloodPressureTF.getText());
    			PatientVisitFile.getFileInstance().setHealthConcerns(healthConcernsTA.getText());
    			PatientVisitFile.getFileInstance().setHeight(heightTF.getText());
    			PatientVisitFile.getFileInstance().setHistoryOfImmunization(immunizationList.getText());
    			PatientVisitFile.getFileInstance().setPreviousHealth(prevHealthList.getText());
    			PatientVisitFile.getFileInstance().setPreviousPrescription(prevMedList.getText());
    			PatientVisitFile.getFileInstance().setWeight(weightTF.getText());
    			PatientVisitFile.getFileInstance().setBodyTemp(tempTF.getText());
    			
    			
    			try {
					PatientVisitFile.getFileInstance().createFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	});
    	
    	//finds pateint
    	findPatientBtn.setOnAction(e->{
    		if(birthDate.getValue() == null) {
    			errorLabel.setText("Please select a date");
    		}
    		else if(firstName.getText().equals("")) {
    			errorLabel.setText("Please enter the first name");
    		}
    		else if(lastName.getText().equals("")) {
    			errorLabel.setText("Please enter the last name");
    		}
    		else {
    			try {
					PatientFile.getFileInstance().accessFile(firstName.getText(), lastName.getText(), birthDate.getValue().toString());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			//verifies findings
    			if(firstName.getText().equals(PatientFile.getFileInstance().getFName())
    					&& lastName.getText().equals(PatientFile.getFileInstance().getLName())
    					&&birthDate.getValue().toString().equals(PatientFile.getFileInstance().getDateOfBirth()))
    			{
 //   				System.out.print("true");
    				errorLabel.setText("If not found,");
    				try {
						PatientVisitFile.getFileInstance().accessFile(firstName.getText(), lastName.getText(), birthDate.getValue().toString());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				immunizationList.setText(PatientVisitFile.getFileInstance().getHistoryOfImmunization());
    				prevMedList.setText(PatientVisitFile.getFileInstance().getPreviousMed() + " " +  PatientVisitFile.getFileInstance().getNewPrescription());
    				prevHealthList.setText(PatientVisitFile.getFileInstance().getPreviousHealth() + " " +  PatientVisitFile.getFileInstance().getHealthConcerns());
    				patientName.setText(PatientFile.getFileInstance().getFName() + " " + PatientFile.getFileInstance().getLName());
    			}
    			else {
//    				System.out.print("false");
    				errorLabel.setText("Patient not found please->");
    			}
    		}
    	});
    }
}
