package com.barclays.abms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

import com.barclays.io.FileOperation;

/**
 * This is main class of system which starts the entire system. This class loads the input and initializes {@link Airport}. 
 * This class performs task to find the shortest path between two {@link Node}
 * 
 * @author Vaibhav
 *
 */
public class AutomatedBaggageSystem {

	private final List<Node> nodes;
	private final List<ConveyorBelt> conveyorBelts;
	private Set<Node> settledNodes;
	private Set<Node> unSettledNodes;
	private Map<Node, Node> predecessors;
	private Map<Node, Integer> distance;

	/**
	 * AutomatedBaggageSystem is initialized for Airport. Here {@link Airport} can be considered as a graph with various {@link ConveyorBelt} as 
	 * its edges and {@link Node} as their verticies
	 * @param airport
	 */
	public AutomatedBaggageSystem(Airport airport) {
		this.nodes = new ArrayList<Node>(airport.getNodes());
		this.conveyorBelts = new ArrayList<ConveyorBelt>(
				airport.getConveyorBelts());
	}

	/**
	 * This method takes input source {@link Node} and target {@link Node} as inputs to find the shortest path between them 
	 * @param source - {@link Node} is source node
	 * @param target - {@link Node} is destination node
	 * @return - {@link LinkedList}<{@link Node}> It returns the list of nodes through which baggage need to be transported
	 */
	public LinkedList<Node> getShortestPath(Node source, Node target) {
		execute(source);
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = target;
		
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		
		Collections.reverse(path);
		return path;
	}

	/**
	 * This method takes input source {@link Node} and target {@link Node} as inputs to find the time taken by shortest path between them 
	 * @param source - {@link Node} is source node
	 * @param target - {@link Node} is destination node
	 * @return - {@link LinkedList}<{@link Node}> It returns the time taken to move from <tt>source</tt> to <tt>target</tt>
	 */
	public int getShortestDistance(Node source, Node target) {
		execute(source);
		int distance = 0;
		Node step = target;
		
		if (predecessors.get(step) == null) {
			return distance;
		}
		while (predecessors.get(step) != null) {
			distance += getDistance(step, predecessors.get(step));
			step = predecessors.get(step);
		}
		return distance;
	}

	private void execute(Node source) {
		settledNodes = new HashSet<Node>();
		unSettledNodes = new HashSet<Node>();
		distance = new HashMap<Node, Integer>();
		predecessors = new HashMap<Node, Node>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Node node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Node node) {
		List<Node> neighbouringNodes = getNeighbouringNodes(node);
		for (Node target : neighbouringNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target,
						getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}
	}

	private Integer getTravelTimeFunction(List<ConveyorBelt> conveyorBelts,
			Predicate<ConveyorBelt> tester, Function<ConveyorBelt, Integer> mapper) {
		for (ConveyorBelt belt : conveyorBelts) {
			if (tester.test(belt)) {
				Integer data = mapper.apply(belt);
				return data;
			}
		}
		throw new RuntimeException("Should not reach here");
	}

	private int getDistance(Node node, Node target) {
		return getTravelTimeFunction(conveyorBelts, (belt) -> (belt.getSource().equals(node) || belt
				.getTarget().equals(node))
				&& (belt.getTarget().equals(target) || belt.getSource().equals(target)),belt -> belt.getTravelTime());
	}

	private List<Node> getNeighbouringNodes(Node node){
		return getNeighbors(conveyorBelts, node, belt->(belt.getSource().equals(node) || belt
				.getTarget().equals(node))
				&& (!isSettled(belt.getTarget()) || !isSettled(belt
						.getSource())), belt-> (node.equals(belt.getTarget()) ? belt.getSource() : belt.getTarget()));
	}
	private List<Node> getNeighbors(List<ConveyorBelt> conveyorBelts, Node node, Predicate<ConveyorBelt> tester, Function<ConveyorBelt, Node> mapper) {
		List<Node> neighbors = new ArrayList<Node>();
		for (ConveyorBelt conveyorBelt : conveyorBelts) {
			if(tester.test(conveyorBelt)){
				Node neighbourNode = mapper.apply(conveyorBelt);
				neighbors.add(neighbourNode);
			}
		}
		return neighbors;
	}

	private Node getMinimum(Set<Node> nodes) {
		Node minimum = null;
		for (Node node : nodes) {
			if (minimum == null) {
				minimum = node;
			} else {
				if (getShortestDistance(node) < getShortestDistance(minimum)) {
					minimum = node;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Node node) {
		return settledNodes.contains(node);
	}

	private int getShortestDistance(Node destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	private static String getOutputString(String sequenceNo, LinkedList<Node> path, int totalTime){
		StringBuilder sb = new StringBuilder();
		sb.append(sequenceNo).append(' ');
		for(Node node : path){
			sb.append(node);
		}
		sb.append(": ").append(totalTime).append('\n');
		return sb.toString();
	}
	
	public static void main(String args[]) throws IOException{
		FileOperation.cleanUp();
		Airport airport = FileOperation.loadInputToSetupAirport();
		AutomatedBaggageSystem abs = new AutomatedBaggageSystem(airport);
		for(Entry<String, Bags> bagEntry : airport.getBags().entrySet()){
			String flightNo = bagEntry.getKey();
			Node source = bagEntry.getValue().getNode();
			Departure departure = airport.getDepartures().get(flightNo);
			Node target = departure.getDepartureGate();
			LinkedList<Node> path = abs.getShortestPath(source, target);
			int totalTime = abs.getShortestDistance(source, target);
			FileOperation.writeOutputToFile(getOutputString(bagEntry.getValue().getId(), path, totalTime));
		}
	}
	
}
