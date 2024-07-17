package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatientFile {
	private static PatientFile INSTANCE;
	private String fName;
	private String lName;
	private String dateOfBirth;
	private String phone;
	private String email;
	
	//constroctor 
	PatientFile(){
		phone = "";
		email = "";
	}
	
	 public static PatientFile getFileInstance() {
			if (INSTANCE == null) {
				INSTANCE = new PatientFile();
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
	
	//accessor for phone
	public String getPhone() {
		return phone;
	}
	
	//accessor for email
	public String getEmail() {
		return email;
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
		
	//setter for phone
	public void setPhone(String p) {
		phone = p;
	}
		
	//setter for email
	public void setEmail(String e) {
		email = e;
	}
	
	//create file
	public void createFile() throws IOException {
		File z =  new File(fName+ lName+ dateOfBirth+".txt");
		if(z.exists() && !z.isDirectory()) {
			
		}
		else {
			FileWriter myWriter = new FileWriter(z);
			myWriter.write(fName+"\n"+lName + "\n"+ dateOfBirth + "\n"+ phone + "\n"+ email + "\n");
			myWriter.close();
		}
	 
	}
	//get file
	 public void accessFile(String fName, String lName, String date) throws FileNotFoundException {
		 File fOne = new File(fName+ lName+ date+".txt");
		 if(fOne.exists() && !fOne.isDirectory()) { 
			 	Scanner scOne = new Scanner(fOne);
				this.fName = scOne.nextLine();
				this.lName = scOne.nextLine();
				dateOfBirth = scOne.nextLine();
				phone = scOne.nextLine();
				email = scOne.nextLine();
				scOne.close();
		 }
	 }
	 
	 //change contact information 
	 public void  contactChangeFileEdit() throws IOException {
		 File fOne = new File(fName+ lName+ dateOfBirth+".txt");
		 fOne.delete();
		 createFile();	 
	 }
	
}