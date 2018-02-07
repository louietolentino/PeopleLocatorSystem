package com.pointwest.java.ui;

import java.util.List;
import java.util.Scanner;

import com.pointwest.java.bean.EmployeeBean;
import com.pointwest.java.bean.ProjectBean;
import com.pointwest.java.bean.UserInput;

public class SearchPageUI {

	private Scanner myScanner;
	private UserInput userInput;

	public SearchPageUI() {
		userInput = new UserInput();
	}

	public UserInput getUserOptionFromSearchMenuPage() {
		myScanner = new Scanner(System.in);
		System.out.print("\n## SEARCH ##\nMENU:\n[1]\tBy Employee ID\n[2]\tBy Name\n[3]\tBy Project\nEnter choice: ");
		userInput.setChoice(myScanner.nextInt());
		return userInput;
	}

	public String getUserInputFromSearchByEmpIDPage() {
		myScanner = new Scanner(System.in);
		String employeeID;
		System.out.print("\n## SEARCH - By Employee ID ##\nEnter Employee ID: ");
		employeeID = myScanner.nextLine();
		return employeeID;
	}

	public String getUserInputFromSearchByNamePage() {
		myScanner = new Scanner(System.in);
		String name;
		System.out.print("\n## SEARCH - By Name ##\nEnter name: ");
		name = myScanner.nextLine();
		return name;
	}

	public String getUserInputFromSearchByProjectPage() {
		myScanner = new Scanner(System.in);
		String project;
		System.out.print("\n## SEARCH - By Project ##\nEnter project: ");
		project = myScanner.nextLine();
		return project;
	}

	public void displaySearchResults(List<EmployeeBean> searchResult) {
		int resultCounter = 1;
		System.out.println("\n## SEARCH RESULT - (" + searchResult.size()
				+ ") ##\n----------------------------------------------------------\nEMPLOYEE ID|FIRSTNAME|LASTNAME|SEAT|LOCAL|SHIFT|PROJECT(S)\n----------------------------------------------------------");
		for (EmployeeBean result : searchResult) {
			System.out.print("[" + resultCounter + "] " + result.getId() + "|" + result.getFirstName() + "|"
					+ result.getLastName() + "|" + result.getBuildingID() + result.getFloorNumber() + "F"
					+ result.getQuadrant() + result.getRowNumber() + "-" + result.getColumnNumber() + "|"
					+ result.getLocal() + "|" + result.getShift() + "|");
			if (result.getProjects().size() > 1) {
				for (ProjectBean project : result.getProjects()) {
					System.out.print(project.getProjectName() + ", ");
				}
			} else if (result.getProjects().size() == 1) {
				for (ProjectBean project : result.getProjects()) {
					System.out.print(project.getProjectName());
				}
			} else if (result.getProjects().isEmpty()) {
				System.out.println("NONE");
			}
			System.out.print("\n");
			resultCounter++;
		}
		System.out.println("-----------------------end of result----------------------");
	}

	public UserInput getUserOptionFromSearchResultsPage() {
		myScanner = new Scanner(System.in);
		System.out.print("\nACTIONS:[1] Search Again\t[2] Home\nEnter choice: ");
		userInput.setChoice(myScanner.nextInt());
		return userInput;
	}

}
