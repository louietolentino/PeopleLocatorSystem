package com.pointwest.java.ui;

import java.util.List;
import java.util.Scanner;

import com.pointwest.java.bean.EmployeeBean;
import com.pointwest.java.bean.UserInput;

public class ViewSeatPlanPageUI {
	
	private Scanner myScanner;
	private UserInput userInput;
	
	public ViewSeatPlanPageUI() {
		userInput = new UserInput();
	}

	public UserInput getUserOptionFromViewSeatPlanMenuPage() {
		myScanner = new Scanner(System.in);
		System.out.print("\n## VIEW SEATPLAN ##\nMENU:\n[1]\tBy Location - Floor Level\n[2]\tBy Quadrant\nEnter choice: ");
		userInput.setChoice(myScanner.nextInt());
		return userInput;
	}
	
	public UserInput getUserInputFromViewSeatPlanByLocationFloorLevelPage() {
		myScanner = new Scanner(System.in);
		System.out.print("\n## VIEW SEATPLAN - By Location - Floor Level ##\nEnter Location: ");
		userInput.setLocation(myScanner.nextLine());
		System.out.print("Enter Floor Level: ");
		userInput.setFloorLevel(myScanner.nextLine());
		return userInput;
	}
	
	public void displaySeatPlan(String[][] seatPlan) {
//		System.out.println("\n## VIEW SEATPLAN ##\nLOCATION: " + seatPlan.get(0).getBuildingID() + "[" + seatPlan.get(0).getBuildingAddress() + "], FLOOR: " + seatPlan.get(0).getFloorNumber() + "\n-------------------------------------------------------------------------------------------------");
		int maxRow = 18;
		int maxCol = 6;

		for (int row = 0; row < maxRow; row++) {
			for(int column = 0; column < maxCol; column++) {
				System.out.print(seatPlan[row][column]);
				System.out.print("\t");
			}
			System.out.println("");
		}
		
	}

}
