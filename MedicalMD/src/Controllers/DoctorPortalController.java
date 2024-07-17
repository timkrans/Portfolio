package Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.PatientFile;
import application.PatientVisitFile;
import application.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DoctorPortalController {

    @FXML
    private TextArea allergiesTA;

    @FXML
    private DatePicker birthDate;

    @FXML
    private TextField bloodPressureTF;

    @FXML
    private Label email;

    @FXML
    private Button findPatientBtn;

    @FXML
    private TextField firstName;

    @FXML
    private TextArea healthConcernsTA;

    @FXML
    private TextField heightTF;

    @FXML
    private TextField immuList;

    @FXML
    private TextField lastName;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button messagePatientBtn;

    @FXML
    private TextField newMedList;

    @FXML
    private Label patientName;

    @FXML
    private Label patientName1;

    @FXML
    private Label patientName11;

    @FXML
    private Label phoneNumber;

    @FXML
    private Button prescribeMedBtn;

    @FXML
    private TextField prevHealthTA;

    @FXML
    private TextField prevMedList;

    @FXML
    private TextField tempTF;

    @FXML
    private TextField weightTF;
    
    @FXML
    private Label  errorLabel;

    public DoctorPortalController() {

    }
    
    public void initialize() {
    	//handler for log out
    	logOutBtn.setOnAction(e->{
    		ViewFactory.getViewFactoryInstance().showLoginView(e);
    	});
    	
    	//view message
    	messagePatientBtn.setOnAction( e -> {
			//System.out.print("clicked");
			ViewFactory.getViewFactoryInstance().showMessageView(e);
		});
    	
    	//checks and get users
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
    				errorLabel.setText("Seach Patient:");
    				try {
						PatientVisitFile.getFileInstance().accessFile(firstName.getText(), lastName.getText(), birthDate.getValue().toString());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				//sets up view when having a patient
    				immuList.setText(PatientVisitFile.getFileInstance().getHistoryOfImmunization());
    				prevMedList.setText(PatientVisitFile.getFileInstance().getPreviousMed());
    				prevHealthTA.setText(PatientVisitFile.getFileInstance().getPreviousHealth());
    				patientName.setText(PatientFile.getFileInstance().getFName() + " " + PatientFile.getFileInstance().getLName());
    				heightTF.setText(PatientVisitFile.getFileInstance().getHeight());
    				weightTF.setText(PatientVisitFile.getFileInstance().getWeight());
    				tempTF.setText(PatientVisitFile.getFileInstance().getBodyTemp());
    				bloodPressureTF.setText(PatientVisitFile.getFileInstance().getBloodPressure());
    				email.setText(PatientFile.getFileInstance().getEmail());
    				phoneNumber.setText(PatientFile.getFileInstance().getPhone());
    				healthConcernsTA.setText(PatientVisitFile.getFileInstance().getHealthConcerns());
    				allergiesTA.setText(PatientVisitFile.getFileInstance().getAllergies());
    			}
    			else {
//    				System.out.print("false");
    				errorLabel.setText("Patient not found please->");
    			}
    		}
    	});
    	
    	//handler for prescribe medicine
    	prescribeMedBtn.setOnAction(e->{
    		//verifies it has been checked
    		if(firstName.getText().equals(PatientVisitFile.getFileInstance().getFName())
					&& lastName.getText().equals(PatientVisitFile.getFileInstance().getLName())
					&&birthDate.getValue().toString().equals(PatientVisitFile.getFileInstance().getDateOfBirth()))
			{
    			//sets new Prescription
    			PatientVisitFile.getFileInstance().setNewPrescription(newMedList.getText());
    			try {
					PatientVisitFile.getFileInstance().newPreCreateFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    	});
    	
    }
}
