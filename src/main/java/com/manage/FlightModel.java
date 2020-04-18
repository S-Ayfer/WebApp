package com.manage;

public class FlightModel {
	
	private int id;
	private String fromLocation;
	private String toLocation;
	private int totalSeats;
	
	public FlightModel(int id, String fromLocation, String toLocation, int totalSeats) {
		this.id = id;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.totalSeats = totalSeats;
	}
	
	public FlightModel(String fromLocation, String toLocation, int totalSeats) {

		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.totalSeats = totalSeats;
	}
	public FlightModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

}
