package com.barclays.abms;

/**
 * This class is created to load the information about Bags
 * @author Vaibhav
 *
 */
public class Bags {
	private String id;
	private Node node;
	private String flightNo;
	
	public Bags(String id, Node node, String flighNo){
		this.id = id;
		this.node = node;
		this.flightNo = flighNo;
	}
	public String getId() {
		return id;
	}
	public Node getNode() {
		return node;
	}
	public String getFlightNo() {
		return flightNo;
	}
}
