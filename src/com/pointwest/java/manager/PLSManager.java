package com.pointwest.java.manager;

import java.util.ArrayList;
import java.util.List;

import com.pointwest.java.bean.EmployeeBean;
import com.pointwest.java.bean.SeatPlanBean;
import com.pointwest.java.bean.UserInput;
import com.pointwest.java.dao.EmployeeDao;

public class PLSManager {

	private EmployeeDao employeeDao;

	public PLSManager() {
		employeeDao = new EmployeeDao();
	}

	public boolean isLoginSuccessful(String username, String password) {
		if (employeeDao.getLoginCredentials(username, password).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public List<EmployeeBean> getSearchResultsByEmployeeID(String userInputEmployeeID) {
		return employeeDao.getSearchResultByEmpID(userInputEmployeeID);
	}

	public List<EmployeeBean> getSearchResultsByName(String userInputName) {
		return employeeDao.getSearchResultByName(userInputName);
	}

	public List<EmployeeBean> getSearchResultsByProject(String userInputProject) {
		return employeeDao.getSearchResultByProject(userInputProject);
	}

	public String[][] getSeatPlanByLocationFloorLevel(UserInput userInput) {
		List<EmployeeBean> employeeSeatPlan = employeeDao.getSeatPlanByLocationFloorLevel(userInput);
		List<SeatPlanBean> plainSeatPlan = employeeDao.getSeatPlanLayoutByLocationFloorLevel(userInput);
		return mergeEmployeeDetailsWithSeatPlan(employeeSeatPlan, plainSeatPlan);
	}

	public String[][] mergeEmployeeDetailsWithSeatPlan(List<EmployeeBean> employeeSeatPlanDetails,
			List<SeatPlanBean> seatPlanTemplate) {

		List<SeatPlanBean> seatPlanWithEmployeeName = new ArrayList<SeatPlanBean>();
		int maxRow = 18;
		int maxCol = 6;
		String[][] detailedSeatPlan = new String[maxRow][maxCol];
		for (int row = 0; row < maxRow; row++) {
			for (int col = 0; col < maxCol; col++) {
				detailedSeatPlan[row][col] = "                    ";
			}
		}

		int rowSeatQuadA = 0;
		int rowNameQuadA = 1;
		int rowLocalQuadA = 2;
		int columnQuadA = 0;
		int rowSeatQuadC = 0;
		int rowNameQuadC = 0;
		int rowLocalQuadC = 0;
		int columnQuadC = 0;

		for (SeatPlanBean seatPlanHolder : seatPlanTemplate) {
			for (EmployeeBean employeeDetailHolder : employeeSeatPlanDetails) {
				if ((seatPlanHolder.getQuadrant().equals(employeeDetailHolder.getQuadrant()))
						&& (seatPlanHolder.getRow_number().equals(employeeDetailHolder.getRowNumber()))
						&& (seatPlanHolder.getColumn_number().equals(employeeDetailHolder.getColumnNumber()))) {
					seatPlanHolder.setFirst_name(employeeDetailHolder.getFirstName());
					seatPlanHolder.setLast_name(employeeDetailHolder.getLastName());
					seatPlanHolder.setName();
					
//					seatPlanHolder.setSeat();
					
					seatPlanWithEmployeeName.add(seatPlanHolder);
//				} else {
//					seatPlanWithEmployeeName.add(seatPlanHolder);
				}
			}
		}
System.out.println(seatPlanWithEmployeeName.size());
		for (SeatPlanBean seatPlanHolder : seatPlanWithEmployeeName) {
			if (columnQuadA < 3) {
				if ("A".equals(seatPlanHolder.getQuadrant())) {
					// rowSeatQuadA = 0;
					// rowNameQuadA = 0;
					// rowLocalQuadA = 0;
					// columnQuadA = 0;
//					columnQuadA = Integer.parseInt(seatPlanHolder.getColumn_number()) - 1;
//					rowSeatQuadA = Integer.parseInt(seatPlanHolder.getRow_number()) - 1;
					detailedSeatPlan[rowSeatQuadA][columnQuadA] = seatPlanHolder.getSeat();

					// rowNameQuadA = Integer.parseInt(seatPlanHolder.getRow_number());
					 detailedSeatPlan[rowNameQuadA][columnQuadA] = seatPlanHolder.getName();
					// rowLocalQuadA = rowNameQuadA + 1;
					 detailedSeatPlan[rowLocalQuadA][columnQuadA] = seatPlanHolder.getLocal();

					columnQuadA++;
				}
				
			} else {
				columnQuadA = 0;
				rowSeatQuadA += 3;
				rowNameQuadA += 3;
				rowLocalQuadA += 3;
				detailedSeatPlan[rowSeatQuadA][columnQuadA] = seatPlanHolder.getSeat();
				detailedSeatPlan[rowNameQuadA][columnQuadA] = seatPlanHolder.getName();
				detailedSeatPlan[rowLocalQuadA][columnQuadA] = seatPlanHolder.getLocal();
				columnQuadA++;
			}
			

//			rowSeatQuadA += 3;
//			rowNameQuadA += 3;
//			rowLocalQuadA += 3;

			// else if ("B".equals(seatPlanHolder.getQuadrant())) {
			// row = Integer.parseInt(seatPlanHolder.getRow_number()) - 1;
			// column = Integer.parseInt(seatPlanHolder.getColumn_number()) + 2;
			// detailedSeatPlan[row][column] = seatPlanHolder.getSeat();
			// row++;
			// detailedSeatPlan[row][column] = seatPlanHolder.getName();
			// row++;
			// detailedSeatPlan[row][column] = seatPlanHolder.getLocal();
			// } else if ("C".equals(seatPlanHolder.getQuadrant())) {
			// row = Integer.parseInt(seatPlanHolder.getRow_number()) - 1;
			// column = Integer.parseInt(seatPlanHolder.getColumn_number()) - 1;
			// detailedSeatPlan[row][column] = seatPlanHolder.getSeat();
			// row++;
			// detailedSeatPlan[row][column] = seatPlanHolder.getName();
			// row++;
			// detailedSeatPlan[row][column] = seatPlanHolder.getLocal();
			// }
		}
		return detailedSeatPlan;
	}

}
