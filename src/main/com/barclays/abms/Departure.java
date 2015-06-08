package com.barclays.abms;

/**
 * This class is created to load the data for Departures.
 * 
 * @author Vaibhav
 *
 */
public class Departure {
	private final String flightNo;
	private final Node departureGate;
	private final String departureLocation;
	private final String departureTime;
	
	public Departure(String flightNo, Node departureGate, String departureLocation, String departureTime){
		this.flightNo = flightNo;
		this.departureGate = departureGate;
		this.departureLocation = departureLocation;
		this.departureTime = departureTime;
	}
	
	public String getFlightNo() {
		return flightNo;
	}
	public Node getDepartureGate() {
		if("ARRIVAL".equals(flightNo)){
			return new Node("BaggageClaim");
		}
		return departureGate;
	}
	public String getDepartureLocation() {
		return departureLocation;
	}
	public String getDepartureTime() {
		return departureTime;
	}
}
