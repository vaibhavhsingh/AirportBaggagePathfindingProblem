package com.barclays.abms;

import java.util.ArrayList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.barclays.abms.Airport;
import com.barclays.abms.AutomatedBaggageSystem;
import com.barclays.abms.ConveyorBelt;
import com.barclays.abms.Node;

/**
 * This is Test class to test the important methods 
 * @author Vaibhav
 *
 */
public class AutomatedBaggageSystemTest {

	private List<Node> nodes;
	private List<ConveyorBelt> conveyorBelts;
	private Airport graph;
	private AutomatedBaggageSystem baggageSystem;

	@Before
	public void setup() {
		nodes = new ArrayList<Node>();
		conveyorBelts = new ArrayList<ConveyorBelt>();

		Node A1 = new Node("A1");
		Node A2 = new Node("A2");
		Node A3 = new Node("A3");
		Node A4 = new Node("A4");
		Node A5 = new Node("A5");
		Node A6 = new Node("A6");
		Node A7 = new Node("A7");
		Node A8 = new Node("A8");
		Node A9 = new Node("A9");
		Node A10 = new Node("A10");
		Node BaggageClaim = new Node("BaggageClaim");
		Node Concourse_A_Ticketing = new Node("Concourse_A_Ticketing");

		nodes.add(A1);
		nodes.add(A2);
		nodes.add(A3);
		nodes.add(A4);
		nodes.add(A5);
		nodes.add(A6);
		nodes.add(A7);
		nodes.add(A8);
		nodes.add(A9);
		nodes.add(A10);
		nodes.add(BaggageClaim);
		nodes.add(Concourse_A_Ticketing);

		conveyorBelts.add(new ConveyorBelt("E1", Concourse_A_Ticketing, A5, 5));
		conveyorBelts.add(new ConveyorBelt("E11", A5, BaggageClaim, 5));
		conveyorBelts.add(new ConveyorBelt("E2", A5, A10, 4));
		conveyorBelts.add(new ConveyorBelt("E3", A5, A1, 6));
		conveyorBelts.add(new ConveyorBelt("E4", A1, A2, 1));
		conveyorBelts.add(new ConveyorBelt("E5", A2, A3, 1));
		conveyorBelts.add(new ConveyorBelt("E6", A3, A4, 1));
		conveyorBelts.add(new ConveyorBelt("E7", A10, A9, 1));
		conveyorBelts.add(new ConveyorBelt("E8", A9, A8, 1));
		conveyorBelts.add(new ConveyorBelt("E9", A8, A7, 1));
		conveyorBelts.add(new ConveyorBelt("E10", A7, A6, 1));

		graph = new Airport(nodes, conveyorBelts, Collections.emptyMap(),Collections.emptyMap());
		baggageSystem = new AutomatedBaggageSystem(graph);
	}

	@Test
	public void testPath0001() {
		String expectedPath = "Concourse_A_Ticketing A5 A1";
		int expectedTime = 11;
		Node Concourse_A_Ticketing = new Node("Concourse_A_Ticketing");
		Node A1 = new Node("A1");

		LinkedList<Node> path = baggageSystem.getShortestPath(Concourse_A_Ticketing,
				A1);

		int totalTime = baggageSystem.getShortestDistance(Concourse_A_Ticketing, A1);

		System.out.println("0001 " + path + ":" + totalTime);
		assertThat(getPath(path),is(expectedPath));
		assertThat(totalTime,is(expectedTime));
	}

	@Test
	public void testPath0002() {
		String expectedPath = "A5 A1 A2 A3 A4";
		int expectedTime = 9;
		Node A4 = new Node("A4");
		Node A5 = new Node("A5");

		LinkedList<Node> path = baggageSystem.getShortestPath(A5, A4);
		int totalTime = baggageSystem.getShortestDistance(A5, A4);
		System.out.println("0002 " + path + ":" + totalTime);
		assertThat(getPath(path),is(expectedPath));
		assertThat(totalTime,is(expectedTime));
	}

	@Test
	public void testPath0003() {
		String expectedPath = "A2 A1";
		int expectedTime = 1;
		Node A2 = new Node("A2");
		Node A1 = new Node("A1");

		LinkedList<Node> path = baggageSystem.getShortestPath(A2, A1);
		int totalTime = baggageSystem.getShortestDistance(A2, A1);
		System.out.println("0003 " + path + ":" + totalTime);
		assertThat(getPath(path),is(expectedPath));
		assertThat(totalTime,is(expectedTime));
	}

	@Test
	public void testPath0004() {
		String expectedPath = "A8 A9 A10 A5";
		int expectedTime = 6;
		Node A8 = new Node("A8");
		Node A5 = new Node("A5");

		LinkedList<Node> path = baggageSystem.getShortestPath(A8, A5);
		int totalTime = baggageSystem.getShortestDistance(A8, A5);
		System.out.println("0004 " + path + ":" + totalTime);
		assertThat(getPath(path),is(expectedPath));
		assertThat(totalTime,is(expectedTime));
	}

	@Test
	public void testPath0005() {
		String expectedPath = "A7 A8 A9 A10 A5 BaggageClaim";
		int expectedTime = 12;
		Node A7 = new Node("A7");
		Node BaggageClaim = new Node("BaggageClaim");

		LinkedList<Node> path = baggageSystem.getShortestPath(A7, BaggageClaim);
		int totalTime = baggageSystem.getShortestDistance(A7, BaggageClaim);
		System.out.println("0005 " + path + ":" + totalTime);
		assertThat(getPath(path),is(expectedPath));
		assertThat(totalTime,is(expectedTime));
	}
	
	private String getPath(LinkedList<Node> path){
		StringBuilder sb = new StringBuilder();
		for(Node node : path){
			sb.append(node);
		}
		return sb.toString().trim();
	}
}
