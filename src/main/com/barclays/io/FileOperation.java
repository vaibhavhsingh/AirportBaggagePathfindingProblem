package com.barclays.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.barclays.abms.Airport;
import com.barclays.abms.Bags;
import com.barclays.abms.Departure;
import com.barclays.abms.Node;

/**
 * This is an utility class to deal with File related operations.
 * 
 * @author Vaibhav
 *
 */
public class FileOperation {

	/**
	 * This method loads the input from input file placed under input directory. It assumes the input to be ./input/input.txt
	 * Currently its kept as hardcoded but can be made configurable if required by passing argument.
	 * @return
	 */
	public static Airport loadInputToSetupAirport(){
		Airport airport = new Airport();		
		try(BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))) {
			String condition = null;
			String line ;
			int i = 0;
		    boolean flag = false;
		    String arr[] = null;
		    while((line = reader.readLine()) != null){
		    	flag = false;
		    	if(line.contains("# Section:")){
		        	condition = line.substring("# Section:".length()).trim();
		            flag = true;
		        }
		    	arr = line.split(" ");
		    	if(!flag){
			    	switch(condition){			    	
				    	case "Conveyor System" : 
				    			airport.addConveyorBelt("B-"+(++i), arr[0], arr[1], Integer.parseInt(arr[2]));
				    			break;
				    	case "Departures" :
				    			airport.getDepartures().put(arr[0], new Departure(arr[0],new Node(arr[1]),arr[2],arr[3]));
				    			break;
				    	case "Bags" :
				    			airport.getBags().put(arr[2], new Bags(arr[0],new Node(arr[1]),arr[2]));
				    			break;
			    	}
		    	}
		    }
		    airport.getDepartures().put("ARRIVAL", new Departure("ARRIVAL", new Node("BaggageClaim"), "DEN","XX:XX"));
		}catch (IOException e) {
		    e.printStackTrace();
		}
		return airport;
	}
	
	/**
	 * This method writes the output to output directory in output.txt.
	 * @param output
	 */
	public static void writeOutputToFile(String output){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("./output/output.txt", true)))) {
		    out.println(output);
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * This is a cleanup method to clean previous output.
	 */
	public static void cleanUp(){
		File file = new File("./output/output.txt");
		if(file.exists()){
			file.delete();
		}
		File outputDir = new File("./output");
		outputDir.mkdir();
	}
}
