package com.barclays.abms;

/**
 * This class denotes one location in Airport. 
 * 
 * @author Vaibhav
 *
 */
public class Node {
	private String nodeName;

	public Node(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

	@Override
	public boolean equals(Object obj) {
		Node v = (Node) obj;
		if (this.nodeName.equals(v.getNodeName())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 83;
		int result = 1;
		result = prime * result
				+ ((nodeName == null) ? 0 : nodeName.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return nodeName + " ";
	}
}