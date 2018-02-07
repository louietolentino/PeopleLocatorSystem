package com.pointwest.java.ui;

import java.util.Scanner;

import com.pointwest.java.bean.LoggedInEmployeeBean;
import com.pointwest.java.bean.UserInput;

public class LoginPageUI {

	private UserInput userInput;
	private LoggedInEmployeeBean loggedInEmployee;
	private Scanner myScanner;

	public LoginPageUI() {
		userInput = new UserInput();
		loggedInEmployee = LoggedInEmployeeBean.getInstance();
	}

	public UserInput getUserLoginInput() {

		myScanner = new Scanner(System.in);

		System.out.print(
				"\n--------------------------------------\nLOGIN\n--------------------------------------\nUsername: ");
		userInput.setUsername(myScanner.nextLine());
		System.out.print("Password: ");
		userInput.setPassword(myScanner.nextLine());
		return userInput;
	}

	public void displaySuccessfulLoginMessage() {
		System.out.println("\nLogin Successful!");
	}

	public void displayFailedLoginMessage() {
		System.out.println("\nLogin failed. Wrong username/password!");
	}

	public UserInput getUserOptionFromHomePage() {
		
		myScanner = new Scanner(System.in);

		System.out.print("\n## HOME ##\n--------------------------------------\nWelcome "
				+ loggedInEmployee.getFirstName() + " " + loggedInEmployee.getLastName() + " ["
				+ loggedInEmployee.getRole()
				+ "]\n--------------------------------------\nMENU:\n[1]\tSearch\n[2]\tView Seatplan\n[3]\tLogout\nEnter choice: ");
		userInput.setChoice(myScanner.nextInt());
		return userInput;
	}
	
	public void displayLoggedOutMessage() {
		System.out.println("\nYou have logged out.");
	}

}
