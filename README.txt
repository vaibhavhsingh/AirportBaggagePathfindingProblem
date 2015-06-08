AirportBaggagePathfindingProblem 
--------------------------------

Following are the pre-requisites to compile and run the code
1. JDK 1.8 (v1.8.0_45) (Program uses various features of Java 1.7 and 1.8)
2. Eclipse Luna (Project is developed with this version of eclipse)
3. ANT 1.8 (This is required if project is executed using ANT)

The project contains build.xml which executes the test cases and the main class.

Project can be easily imported as an eclipse project.

Project uses libraries for unit tests cases. They are placed under lib directory
Unit Test Library
-----------------
lib/hamcrest-all-1.3.jar
lib/hamcrest-core-1.2.jar
lib/junit-4.11.jar

Important Classes
-----------------
AutomatedBaggageSystem.java -> This class is the main class of project. The execution begins from this class.
AutomatedBaggageSystemTest.java -> This class holds various test cases 


Input/Output
------------
Input -> The program expects input to be placed under input directory with filename input.txt
	e.g. input/input.txt

Output -> This program generates output and the output is written under output directory
	e.g. output/output.txt



How to execute ?
----------------
Once the code is checked out there are two ways to execute the code
Pre-requisite : Input file should be present in input/input.txt 

1. Go to root directory "AirportBaggagePathfindingProblem" we can see build.xml. Execute..

>ant

2. Import the project in eclipse.
Open file AutomatedBaggageSystem.java
Execute the class.
