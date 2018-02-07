package com.pointwest.java.bean;

public class LoggedInEmployeeBean extends EmployeeBean {

	private String role;
	
	private static final LoggedInEmployeeBean instance = new LoggedInEmployeeBean();
	
	private LoggedInEmployeeBean() {
		
	}

	public static LoggedInEmployeeBean getInstance() {
		return instance;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
