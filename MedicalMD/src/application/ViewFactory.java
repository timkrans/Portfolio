package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewFactory {
	private static ViewFactory INSTANCE;
	 private final String LOGIN_VIEW_LOCATION = "/Views/Login.fxml";
	 private final String DOCTOR_VIEW_LOCATION = "/Views/DoctorPortal.fxml";
	 private final String MESSAGE_VIEW_LOCATION = "/Views/MessagePortal.fxml";
	 private final String NURSE_ENTRANCE_VIEW_LOCATION= "/Views/NurseEntrancePortal.fxml";
	 private final String NURSE_VIEW_LOCATION = "/Views/NursePortal.fxml";
	 private final String PATIENT_VIEW_LOCATION = "/Views/PatientPortal.fxml";
	 private final String PATIENT_SIGN_UP_VIEW_LOCATION = "/Views/PatientSignUp.fxml";
	 private final String REGISTER_PATIENT_VIEW_LOCATION = "/Views/RegisterPatient.fxml";
	
	private ViewFactory() {}
	
	public static ViewFactory getViewFactoryInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ViewFactory();
		}
		return INSTANCE;
	}
	
	private void showView(Event e, String location) {		
		Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
		currentStage.close();
		
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource(location)); OR                                                                                                                                                                                                                                                                                                                              
			Pane pane = FXMLLoader.load(getClass().getResource(location));
			Scene scene = new Scene(pane);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public void showLoginView(Event e) {
		showView(e, LOGIN_VIEW_LOCATION);
	}
	public void showDoctorView(Event e) {
		showView(e, DOCTOR_VIEW_LOCATION);
	}
	public void showMessageView(Event e) {
		showView(e, MESSAGE_VIEW_LOCATION);
	}
	public void showNurseEntranceView(Event e) {
		showView(e, NURSE_ENTRANCE_VIEW_LOCATION);
	}
	public void showNurseView(Event e) {
		showView(e, NURSE_VIEW_LOCATION);
	}
	public void showPatientView(Event e) {
		showView(e, PATIENT_VIEW_LOCATION);
	}
	public void showPatientSignUpView(Event e) {
		showView(e, PATIENT_SIGN_UP_VIEW_LOCATION);
	}
	public void showRegisterPatientView(Event e) {
		showView(e, REGISTER_PATIENT_VIEW_LOCATION);		
	}
	
}
