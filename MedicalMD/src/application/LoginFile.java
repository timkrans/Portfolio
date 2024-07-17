package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginFile {
	private static LoginFile INSTANCE;
	 private String userName;
	 private String password;
	 private String type;
	 private String fName;
	 private String lName;
	 private String dateOfBirth;
	 
	 //constructor
	 public LoginFile() {
		 userName = "";
		 password = "";
		 type = "";
		 fName = "";
		 lName = "";
		 dateOfBirth = "";
	 }
	 
	 public static LoginFile getFileInstance() {
			if (INSTANCE == null) {
				INSTANCE = new LoginFile();
			}
			return INSTANCE;
	 }
	 
	 //mutator for password
	 public void setPassword(String p) {
		 password =p;
	 }
	 
	 //mutator for user
	 public void setUserName(String s) {
		 userName = s;
	 }
	 
	 //mutator for type 
	 public void setType(String t) {
		 type = t;
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
	 
	 //getters for password
	 public String getPassword() {
		 return password;
	 }
		 
	 //getters for userName
	 public String getUserName() {
		 return userName;
	 }
		 
		 
	 //getters for type
	 public String getType() {
		 return type;
	 }
	 
	 //create file
	 public void creatFile() throws IOException {
			File p =  new File(userName+".txt");
			if(p.exists() && !p.isDirectory()) {
				
			}
			else {
				FileWriter myWriter = new FileWriter(p);
				myWriter.write(this.userName+"\n"+password + "\n"+ type + "\n"+ fName + "\n" + lName + "\n" + dateOfBirth + "\n");
				myWriter.close();
			}
		 
	 }
	 
	 //access file
	 public void accessFile(String userName) throws FileNotFoundException {
		 File fOne = new File(userName+".txt");
		 if(fOne.exists() && !fOne.isDirectory()) { 
			 	Scanner scOne = new Scanner(fOne);
				this.userName = scOne.nextLine();
				password = scOne.nextLine();
				type = scOne.nextLine();
				fName = scOne.nextLine();
				lName = scOne.nextLine();
				dateOfBirth = scOne.nextLine();
				scOne.close();
		 }
	 }
	 
}