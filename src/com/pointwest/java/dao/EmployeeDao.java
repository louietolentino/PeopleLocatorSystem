package com.pointwest.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pointwest.java.bean.EmployeeBean;
import com.pointwest.java.bean.LoggedInEmployeeBean;
import com.pointwest.java.bean.ProjectBean;
import com.pointwest.java.bean.SeatPlanBean;
import com.pointwest.java.bean.UserInput;

public class EmployeeDao {

	private Connection connection = null;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private LoggedInEmployeeBean employee;
	private String query;

	public EmployeeDao() {
		employee = LoggedInEmployeeBean.getInstance();
	}

	private Connection connectDB() {

		String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
		String username = "newuser";
		String password = "password123";

		try {

			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(db, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public List<LoggedInEmployeeBean> getLoginCredentials(String username, String password) {

		query = "select first_name, last_name, role from plsdb.employee where username = ? and password = ?;";
		List<LoggedInEmployeeBean> userLoggedInDetails = new ArrayList<LoggedInEmployeeBean>();

		try {
			preparedStatement = connectDB().prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setFirstName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.setRole(resultSet.getString("role"));
				userLoggedInDetails.add(employee);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userLoggedInDetails;

	}

	private void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<EmployeeBean> getSearchResultByEmpID(String userInputEmployeeID) {
		query = "select employee.emp_id, employee.first_name, employee.last_name, employee.shift, project.proj_name, employee_seat.seat_id, employee_seat.bldg_id, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number"
				+ " from (((employee_seat INNER JOIN seat ON seat.seat_id = employee_seat.seat_id) INNER JOIN (employee_project INNER JOIN project ON employee_project.proj_alias = project.proj_alias) ON employee_project.employee_id = employee_seat.emp_id) INNER JOIN employee ON employee.emp_id = employee_project.employee_id)"
				+ " where employee.emp_id = ?";

		try {
			preparedStatement = connectDB().prepareStatement(query);
			preparedStatement.setString(1, userInputEmployeeID);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getProcessedResultSet(resultSet);
	}

	public List<EmployeeBean> getSearchResultByName(String userInputName) {
		query = "select employee.emp_id, employee.first_name, employee.last_name, employee.shift, project.proj_name, employee_seat.seat_id, employee_seat.bldg_id, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number"
				+ " from (((employee_seat INNER JOIN seat ON seat.seat_id = employee_seat.seat_id) INNER JOIN (employee_project INNER JOIN project ON employee_project.proj_alias = project.proj_alias) ON employee_project.employee_id = employee_seat.emp_id) INNER JOIN employee ON employee.emp_id = employee_project.employee_id)"
				+ " where employee.first_name like \"%" + userInputName + "%\" or employee.last_name like \"%"
				+ userInputName + "%\";";

		try {
			preparedStatement = connectDB().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getProcessedResultSet(resultSet);
	}

	public List<EmployeeBean> getSearchResultByProject(String userInputProject) {
		query = "select employee.emp_id, employee.first_name, employee.last_name, employee.shift, project.proj_name, employee_seat.seat_id, employee_seat.bldg_id, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number"
				+ " from (((employee_seat INNER JOIN seat ON seat.seat_id = employee_seat.seat_id) INNER JOIN (employee_project INNER JOIN project ON employee_project.proj_alias = project.proj_alias) ON employee_project.employee_id = employee_seat.emp_id) INNER JOIN employee ON employee.emp_id = employee_project.employee_id)"
				+ " where project.proj_name like \"%" + userInputProject + "%\"";

		try {
			preparedStatement = connectDB().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getProcessedResultSet(resultSet);
	}
	
	public List<EmployeeBean> getSeatPlanByLocationFloorLevel(UserInput userInput){
		query = "select employee.first_name, employee.last_name, employee_seat.emp_id, employee_seat.seat_id, employee_seat.bldg_id, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number"
				+ " from ((employee_seat INNER JOIN seat ON employee_seat.seat_id = seat.seat_id) INNER JOIN employee on employee.emp_id = employee_seat.emp_id) "
				+ "where seat.bldg_id = ? and seat.floor_number = ?;";
		List<EmployeeBean> employeeSeatAndName = new ArrayList<EmployeeBean>();
		try {
			preparedStatement = connectDB().prepareStatement(query);
			preparedStatement.setString(1, userInput.getLocation());
			preparedStatement.setString(2, userInput.getFloorLevel());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				EmployeeBean empNameAndSeatHolder = new EmployeeBean();
				empNameAndSeatHolder.setFirstName(resultSet.getString("employee.first_name"));
				empNameAndSeatHolder.setLastName(resultSet.getString("employee.last_name"));
				empNameAndSeatHolder.setQuadrant(resultSet.getString("seat.quadrant"));
				empNameAndSeatHolder.setRowNumber(resultSet.getString("seat.row_number"));
				empNameAndSeatHolder.setColumnNumber(resultSet.getString("seat.column_number"));
				employeeSeatAndName.add(empNameAndSeatHolder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeSeatAndName;
	}
	
	public List<SeatPlanBean> getSeatPlanLayoutByLocationFloorLevel(UserInput userInput){
		query = "select * from seat where bldg_id = \"" + userInput.getLocation() 
			+ "\" and floor_number = \"" + userInput.getFloorLevel() + "\";";
		List<SeatPlanBean> seatPlanLayout = new ArrayList<SeatPlanBean>();
		try {
			preparedStatement = connectDB().prepareStatement(query);
//			preparedStatement.setString(1, userInput.getLocation());
//			preparedStatement.setString(2, userInput.getFloorLevel());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SeatPlanBean seatPlan = new SeatPlanBean();
				seatPlan.setBldg_id(resultSet.getString("bldg_id"));
				seatPlan.setFloor_number(resultSet.getString("floor_number"));
				seatPlan.setQuadrant(resultSet.getString("quadrant"));
				seatPlan.setRow_number(resultSet.getString("row_number"));
				seatPlan.setColumn_number(resultSet.getString("column_number"));
				seatPlan.setLocal(resultSet.getString("local_number"));
				seatPlan.setSeat();
				seatPlanLayout.add(seatPlan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seatPlanLayout;
	}

	private List<EmployeeBean> getProcessedResultSet(ResultSet resultSet) {
		List<EmployeeBean> searchResultByEmpID = new ArrayList<EmployeeBean>();
		try {
			while (resultSet.next()) {
				boolean isExisting = false;
				int matchedIndex = 0;
				EmployeeBean empSearchResult = new EmployeeBean();
				ProjectBean project = new ProjectBean();
				List<ProjectBean> projects;

				empSearchResult.setId(resultSet.getString("employee.emp_id"));
				empSearchResult.setFirstName(resultSet.getString("employee.first_name"));
				empSearchResult.setLastName(resultSet.getString("employee.last_name"));
				empSearchResult.setShift(resultSet.getString("employee.shift"));
				empSearchResult.setBuildingID(resultSet.getString("employee_seat.bldg_id"));
				empSearchResult.setFloorNumber(resultSet.getString("seat.floor_number"));
				empSearchResult.setQuadrant(resultSet.getString("seat.quadrant"));
				empSearchResult.setRowNumber(resultSet.getString("seat.row_number"));
				empSearchResult.setColumnNumber(resultSet.getString("seat.column_number"));

				if (resultSet.getString("seat.local_number").isEmpty()) {
					empSearchResult.setLocal("NONE");
				} else {
					empSearchResult.setLocal(resultSet.getString("seat.local_number"));
				}

				if (("Project Never Exist").equals(resultSet.getString("project.proj_name"))) {
					project.setProjectName("NONE");
				} else {
					project.setProjectName(resultSet.getString("project.proj_name"));
				}

				for (int index = 0; index < searchResultByEmpID.size(); index++) {
					if ((resultSet.getString("employee.emp_id")).equals(searchResultByEmpID.get(index).getId())
							&& ((resultSet.getString("employee_seat.bldg_id"))
									.equals(searchResultByEmpID.get(index).getBuildingID()))) {
						isExisting = true;
						matchedIndex = index;
						break;
					}
				}

				if (isExisting) {
					searchResultByEmpID.get(matchedIndex).getProjects().add(project);
				} else {
					projects = new ArrayList<ProjectBean>();
					projects.add(project);
					empSearchResult.setProjects(projects);
					searchResultByEmpID.add(empSearchResult);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return searchResultByEmpID;
	}

}
