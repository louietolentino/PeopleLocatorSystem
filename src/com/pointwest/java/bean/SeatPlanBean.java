package com.pointwest.java.bean;

public class SeatPlanBean {

	private String bldg_id;
	private String floor_number;
	private String quadrant;
	private String row_number;
	private String column_number;
	private String first_name;
	private String last_name;
	private String name;
	private String local;
	private String seat;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat() {
		this.seat = this.bldg_id + this.floor_number + "F" + this.quadrant + this.column_number + "-" + this.row_number + "\t";
	}

	public String getBldg_id() {
		return bldg_id;
	}

	public void setBldg_id(String bldg_id) {
		this.bldg_id = bldg_id;
	}

	public String getFloor_number() {
		return floor_number;
	}

	public void setFloor_number(String floor_number) {
		this.floor_number = floor_number;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public String getRow_number() {
		return row_number;
	}

	public void setRow_number(String row_number) {
		this.row_number = row_number;
	}

	public String getColumn_number() {
		return column_number;
	}

	public void setColumn_number(String column_number) {
		this.column_number = column_number;
	}

	public String getName() {
		return name;
	}

	public void setName() {
		this.name = this.last_name + ", " + this.first_name;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = "loc. " + local + "\t";
	}

}
