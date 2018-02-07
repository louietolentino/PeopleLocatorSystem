package com.pointwest.java.ui;

import com.pointwest.java.bean.UserInput;
import com.pointwest.java.manager.PLSManager;

public class UIManager {

	private PLSManager manager;
	private LoginPageUI loginPage;
	private SearchPageUI searchPage;
	private ViewSeatPlanPageUI viewSeatPlanPageUI;

	public UIManager() {
		manager = new PLSManager();
		loginPage = new LoginPageUI();
		searchPage = new SearchPageUI();
		viewSeatPlanPageUI = new ViewSeatPlanPageUI();
	}

	public void displayLoginPage() {
		UserInput userInput = loginPage.getUserLoginInput();
		verifyLogin(userInput.getUsername(), userInput.getPassword());
	}

	public void verifyLogin(String username, String password) {

		// call dao method to get database login details
		if (manager.isLoginSuccessful(username, password)) {
			// call method in login page to display login successful message, then call
			// method to display home page
			loginPage.displaySuccessfulLoginMessage();
			processUserOptionFromHomePage(loginPage.getUserOptionFromHomePage());
		} else {
			// call loginPageUI method to display na unsuccessful ang login, then display
			// the login page again
			loginPage.displayFailedLoginMessage();
			displayLoginPage();
		}
	}

	public void processUserOptionFromHomePage(UserInput userInput) {
		switch (userInput.getChoice()) {
		case 1:
			// call search page
			processUserOptionFromSearchMenuPage(searchPage.getUserOptionFromSearchMenuPage());
			break;
		case 2:
			// call view seatplan page
			processUserOptionFromViewSeatPlanMenuPage(viewSeatPlanPageUI.getUserOptionFromViewSeatPlanMenuPage());
			break;
		case 3:
			// logout
			loginPage.displayLoggedOutMessage();
			displayLoginPage();
			break;
		}
	}

	public void processUserOptionFromSearchMenuPage(UserInput userInput) {
		switch (userInput.getChoice()) {
		case 1:
			// call by employee id page
			processUserInputFromSearchByEmpIDPage(searchPage.getUserInputFromSearchByEmpIDPage());
			break;
		case 2:
			// call by name page
			processUserInputFromSearchByNamePage(searchPage.getUserInputFromSearchByNamePage());
			break;
		case 3:
			// call by project page
			processUserInputFromSearchByProjectPage(searchPage.getUserInputFromSearchByProjectPage());
			break;
		default:
			break;
		}
	}

	public void processUserInputFromSearchByEmpIDPage(String userInputEmployeeID) {
		// pass user input empid to manager method that connects to DAO
		searchPage.displaySearchResults(manager.getSearchResultsByEmployeeID(userInputEmployeeID));
		processUserOptionFromSearchResultsPage(searchPage.getUserOptionFromSearchResultsPage());
	}

	public void processUserInputFromSearchByNamePage(String userInputName) {
		searchPage.displaySearchResults(manager.getSearchResultsByName(userInputName));
		processUserOptionFromSearchResultsPage(searchPage.getUserOptionFromSearchResultsPage());
	}

	public void processUserInputFromSearchByProjectPage(String userInputProject) {
		searchPage.displaySearchResults(manager.getSearchResultsByProject(userInputProject));
		processUserOptionFromSearchResultsPage(searchPage.getUserOptionFromSearchResultsPage());
	}

	public void processUserOptionFromSearchResultsPage(UserInput userInput) {
		switch (userInput.getChoice()) {
		case 1:
			processUserOptionFromSearchMenuPage(searchPage.getUserOptionFromSearchMenuPage());
			break;
		case 2:
			processUserOptionFromHomePage(loginPage.getUserOptionFromHomePage());
			break;
		}
	}
	
	public void processUserOptionFromViewSeatPlanMenuPage(UserInput userInput) {
		switch (userInput.getChoice()) {
		case 1: 
//			by location - floor level
			processUserInputFromViewSeatPlanByLocationFloorLevelPage(viewSeatPlanPageUI.getUserInputFromViewSeatPlanByLocationFloorLevelPage());
			break;
		case 2:
//			by quadrant
			break;
		}
	}
	
	public void processUserInputFromViewSeatPlanByLocationFloorLevelPage(UserInput userInput) {
		viewSeatPlanPageUI.displaySeatPlan(manager.getSeatPlanByLocationFloorLevel(userInput));
	}

}
