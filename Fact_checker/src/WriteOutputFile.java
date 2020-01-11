import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

/*
 * Class used to write the result to the output file
 */
public class WriteOutputFile {
	// Urls and type of value 
	public static String Fact_URI="http://swc2017.aksw.org/task2/dataset/";
	public static String  prop_URI="<http://swc2017.aksw.org/hasTruthValue>";
	public static String type="<http://www.w3.org/2001/XMLSchema#double>";
	
	/**
	 * write the result to file
	 * @param input: the test or train file
	 * @param output: the result file
	 * @throws IOException if error occurs during writing of the file
	 */
	
	public static void writeResultToFile(File input,File output) throws IOException {
		// the results   
		String outputResult="";
		// the time in ms now 
		Instant start = Instant.now();
		// instantiating the ReadInputFile class
		ReadInputFile inputFile=new ReadInputFile(input);
		// reading the input file
		inputFile.readFile();
		// getting the hashmap of the IDs and the facts
		HashMap<String,String> facts=inputFile.getFactID();
		//create new file 
		output.createNewFile();
		//if file exists
		if(output.exists()) {
			//proces all the facts of the input file
			for (HashMap.Entry<String, String> fact : facts.entrySet()) {
			//creating a FactProcessing object and checking the veracity of the fact
			FactProcessing processing= new FactProcessing();
			String veracity=processing.factChecking(fact.getValue());
			//write the output
			outputResult =outputResult+( "<"+Fact_URI+fact.getKey()+ ">" + prop_URI + " \"" + veracity + "\""+"^^" + type + " .\n");	 
			}

		// write the results
	    FileWriter writer = new FileWriter(output);
	    writer.write(outputResult); 
	    //get the finish time
	    Instant finish = Instant.now();
	    long timeElapsed = Duration.between(start, finish).toMillis();
	    
	    System.out.println("Done it toook"+timeElapsed+"ms");
		   
	    writer.close();
	}}

}
