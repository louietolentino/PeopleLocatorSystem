package com.pointwest.java.bean;

public class UserInput {

	private int choice;
	private String username;
	private String password;
	private String location;
	private String floorLevel;
	private String quadrant;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFloorLevel() {
		return floorLevel;
	}

	public void setFloorLevel(String floorLevel) {
		this.floorLevel = floorLevel;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
