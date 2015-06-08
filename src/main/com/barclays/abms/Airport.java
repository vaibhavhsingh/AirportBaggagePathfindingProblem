package com.barclays.abms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a form to Graph -> Airport which holds information about various terminals -> Nodes, transportation paths -> ConveyorBelt.
 * It holds the map of departures, baggage information 
 * @author Vaibhav
 *
 */
public class Airport {
	/*
	 * List of nodes
	 */
	private final List<Node> nodes;
	/*
	 * List of conveyorBelts
	 */
	private final List<ConveyorBelt> conveyorBelts;
	
	/*
	 * Map of FlightNo and Departure. The Key=FlightNo, Value=Departure 
	 */
	private final Map<String, Departure> departures;
	
	/*
	 * Map of flightNo and Bags. The Key=FlightNo, Value=Bags
	 */
	private final Map<String, Bags> bags;
	
	public Airport(){
		this.nodes = new ArrayList<Node>();
		this.conveyorBelts = new ArrayList<ConveyorBelt>();
		this.departures = new LinkedHashMap<String,Departure>();
		this.bags = new LinkedHashMap<String, Bags>();
	}
	public Airport(List<Node> nodes, List<ConveyorBelt> conveyorBelts,Map<String, Departure> departures,Map<String, Bags> bags) {
		this.nodes = nodes;
		this.conveyorBelts = conveyorBelts;
		this.departures = departures;
		this.bags = bags;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<ConveyorBelt> getConveyorBelts() {
		return conveyorBelts;
	}

	public Map<String, Departure> getDepartures() {
		return departures;
	}
	
	public Map<String, Bags> getBags() {
		return bags;
	}
	
	/**
	 * This method can be used to add the {@link ConveyorBelts} to {@link Airport}.
	 * @param beltName - Name by which conveyorBelt will be identified
	 * @param source - One end of conveyorBelt.
	 * @param target - Other end of conveyorBelt.
	 * @param travelTime - Time
	 */
	public void addConveyorBelt(String beltName, String source, String target, int travelTime){
		Node sourceNode = addAndGetNode(source);
		Node targetNode = addAndGetNode(target);
		ConveyorBelt belt = new ConveyorBelt(beltName, sourceNode, targetNode, travelTime);
		conveyorBelts.add(belt);
	}
	
	private Node addAndGetNode(String nodeName){
		for(Node node:nodes){
			if(nodeName.equals(node.getNodeName())){
				return node;
			}
		}
		Node node = new Node(nodeName);
		nodes.add(node);
		return node;
	}
}
