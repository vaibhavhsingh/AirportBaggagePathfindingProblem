package com.barclays.abms;

/**
 * This class denotes the path from one {@link Node} to other {@link Node}.
 * 
 * @author Vaibhav
 *
 */
public class ConveyorBelt {
	private String beltName;
	private Node source;
	private Node target;
	private int travelTime;

	public ConveyorBelt(String beltName, Node source, Node target,
			int travelTime) {
		this.beltName = beltName;
		this.source = source;
		this.target = target;
		this.travelTime = travelTime;
	}

	public String getBeltName() {
		return beltName;
	}

	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	public int getTravelTime() {
		return travelTime;
	}

	@Override
	public String toString() {
		return "ConveyorBelt [beltName=" + beltName + ", source=" + source
				+ ", target=" + target + ", weight=" + travelTime + "]";
	}
}
