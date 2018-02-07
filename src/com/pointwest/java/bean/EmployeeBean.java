package com.pointwest.java.bean;

import java.util.List;

public class EmployeeBean {

	private String firstName;
	private String lastName;
	private String id;
	private int seatID;
	private String buildingID;
	private String buildingAddress;
	private String floorNumber;
	private String quadrant;
	private String rowNumber;
	private String columnNumber;
	private String local;
	private String shift;
	private List<ProjectBean> projects;

	public int getSeatID() {
		return seatID;
	}

	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

	public String getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}

	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(String columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public List<ProjectBean> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectBean> projects) {
		this.projects = projects;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
