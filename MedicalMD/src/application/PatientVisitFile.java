package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatientVisitFile {
	private static PatientVisitFile INSTANCE;
	private String fName;
	private String lName;
	private String dateOfBirth;
	private String height;
	private String weight;
	private String bodyTemp;
	private String bloodPress;
	private String visitNum;
	private String allergies;
	private String previousHealth;
	private String previousMed;
	private String healthConcerns;
	private String historyOfImmunization;
	private String nurseRecommendations;
	private String newPrescription;
	
	//constructor
	public PatientVisitFile() {
		fName = "";
		lName = "";
		dateOfBirth = "";
		height = "";
		weight = "";
		bodyTemp = "";
		bloodPress = "";
		visitNum = "";
		allergies = "";
		previousHealth = "";
		previousMed = "";
		healthConcerns = "";
		historyOfImmunization = "";
		nurseRecommendations = "";
		newPrescription = "";
	}
	
	//makes static
	public static PatientVisitFile getFileInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PatientVisitFile();
		}
		return INSTANCE;
	}
	
	//accessor for fname
	public String getFName() {
		return fName;
	}
	
	//accessor for lname
	public String getLName() {
		return lName;
	}
	
	//accessor for date of birth
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	//accessor for height
	public String getHeight() {
		return height;
	}
	
	//accessor for weight
	public String getWeight() {
		return weight;
	}
	
	//accssor for body temperature
	public String getBodyTemp() {
		return bodyTemp;
	}
	
	//accesor for blood pressure
	public String getBloodPressure() {
		return bloodPress;
	}
	
	//accessor for visit number
	public String getVisitNum() {
		return visitNum;
	}
	
	//accessor for allergies
	public String getAllergies() {
		return allergies;
	}
	
	//accessor for previous health
	public String getPreviousHealth() {
		return previousHealth;
	}
	
	//accessor for previous prescriptions
	public String getPreviousMed() {
		return previousMed;
	}
	
	//accessor for health concern
	public String getHealthConcerns() {
		return healthConcerns;
	}
	
	//accessor for history of immunization
	public String getHistoryOfImmunization() {
		return historyOfImmunization;
	}
	
	//accessor for nurse recommendations
	public String getNurseRecommendations() {
		return nurseRecommendations;
	}
	
	//accessor for new prescription
    public String getNewPrescription() {
    	return newPrescription;
    }
    
	//setter for fname
	public void setFName(String f) {
		fName = f;
	}
			
	//setter for lname
	public void setLName(String l) {
		lName = l;
	}
			
	//setter for date of birth
	public void setDateOfBirth(String d) {
		dateOfBirth = d;
	}
			
	//setter for height
	public void setHeight(String h) {
		height = h;
	}
	
	//setter for weight
	public void setWeight(String w) {
		weight = w;
	}
	
	//setter for body temp
	public void setBodyTemp(String b) {
		bodyTemp = b;
	}
	
	//setter for blood pressure
	public void setBloodPressure(String p) {
		bloodPress = p;
	}
	
	//setter for visitNum
	public void setVisitNum(String v) {
		visitNum = v;
	}
	
	//setter for allergies
	public void setAllergies(String a) {
		allergies = a;
	}
	
	//setter for previous health
	public void setPreviousHealth(String p) {
		previousHealth = p;
	}
	
	//setter for previous prescription
	public void setPreviousPrescription(String p) {
		previousMed = p;
	}
	
	//setter for previous health concern
	public void setHealthConcerns(String h) {
		healthConcerns = h;
	}
	
	//setter for history of immunization
	public void setHistoryOfImmunization(String h) {
		historyOfImmunization = h;
	}
	
	//setter for nurse reccomendations
	public void setNurseRecommendations(String n) {
		nurseRecommendations = n;
	}
	
	//setter for new prescription
	public void setNewPrescription(String s) {
		newPrescription = s;
	}
	
	//createFile
	public void createFile() throws IOException {
		visitNum = "1";
		int number = 1;
		File z =  new File(fName+ lName+ dateOfBirth+ "visit" + visitNum + ".txt");
		boolean exist = true;
		while(exist == true) {
			visitNum = number+"";
			z = new File(fName+ lName+ dateOfBirth+ "visit" + visitNum + ".txt");
			if(z.exists() && !z.isDirectory()) { 
				number++;
			}
			else {
				visitNum = number+"";
				exist = false;
			}
		}
		z =  new File(fName+ lName+ dateOfBirth+ "visit" + visitNum + ".txt");
		FileWriter myWriter = new FileWriter(z);
		myWriter.write(fName + "\n" + lName + "\n" + dateOfBirth + "\n"	+height+"\n" +weight + "\n" + bodyTemp + "\n"+
					bloodPress+ "\n" +visitNum + "\n" + allergies + "\n" +previousHealth + "\n" + previousMed	
					+"\n" + healthConcerns + "\n" + historyOfImmunization + "\n" + nurseRecommendations +"\n" + newPrescription + "\n");
		myWriter.close();
	}
	
	//accessFile
	public void accessFile(String f, String l , String date) throws FileNotFoundException {
		visitNum = "1";
		int number = 1;
		File z =  new File(f+ l+ date+ "visit" + visitNum + ".txt");
		boolean exist = true;
		while(exist == true) {
			visitNum = number+"";
			z = new File(f+ l+ date+ "visit" + visitNum + ".txt");
			if(z.exists() && !z.isDirectory()) { 
				number++;
			}
			else {
				exist = false;
			}
		}
		number = number- 1;
		if(number== 0) {
			
		}
		else {
			visitNum = number+"";
			z =  new File(f+ l+ date+ "visit" + visitNum + ".txt");
			Scanner scOne = new Scanner(z);
			fName = scOne.nextLine();
			lName = scOne.nextLine();
			dateOfBirth = scOne.nextLine();
			height = scOne.nextLine();
			weight = scOne.nextLine();
			bodyTemp = scOne.nextLine();
			bloodPress = scOne.nextLine();
			visitNum = scOne.nextLine();
			allergies = scOne.nextLine();
			previousHealth = scOne.nextLine();
			previousMed = scOne.nextLine();
			healthConcerns = scOne.nextLine();
			historyOfImmunization = scOne.nextLine();
			nurseRecommendations = scOne.nextLine();
			newPrescription = scOne.nextLine();
			scOne.close();
		}
	}
	
	//change new prescription
	public void newPreCreateFile() throws IOException {
		visitNum = "1";
		int number = 1;
		File z =  new File(fName+ lName+ dateOfBirth+ "visit" + visitNum + ".txt");
		boolean exist = true;
		while(exist == true) {
			visitNum = number+"";
			z = new File(fName+ lName+ dateOfBirth+ "visit" + visitNum + ".txt");
			if(z.exists() && !z.isDirectory()) { 
				number++;
			}
			else {
				exist = false;
			}
		}
		number = number- 1;
		visitNum = number+"";
		z =  new File(fName+ lName+ dateOfBirth+ "visit" + visitNum + ".txt");
		z.delete();
		createFile();
	}
	
}
