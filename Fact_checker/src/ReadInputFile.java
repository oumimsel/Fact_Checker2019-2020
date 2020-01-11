import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Class used to read each sentence of the input file (.tsv)
 */

public class ReadInputFile {
	//variable declaration
    File inputFile;
    // hash map used to map the ID of the fact to the sentence
	HashMap<String,String> factID=new HashMap<String,String>();
	//list of facts
	ArrayList<String> facts=new ArrayList<>();
	
	/**
	 * Constructor
	 * @param inputFile
	 */
	public ReadInputFile(File inputFile) {
		super();
		this.inputFile = inputFile;
	}

	/*
	 * method used to read the input file
	 */
	public void readFile()  {
		 BufferedReader br = null;
	        String fact = "";
	        //reading the file with a BufferedReader
	        try {
	            br = new BufferedReader(new FileReader(inputFile));
	             while( (fact = br.readLine()) != null){
	             //   System.out.println(fact);
                    // split the ID from the sentence
					String[] factTriplet=fact.split("\t");
					// if the factTriplet starts with a number 
				   if(factTriplet[0].matches("\\d+")) {	
					   //map ID to the sentence
					factID.put(factTriplet[0],factTriplet[1]);
					//add the sentence to the fact list
					facts.add(factTriplet[1]);
					}
				   }
	             //catch exception
	        } catch (FileNotFoundException e) {
	            System.err.println("Please check for the presence of file in the path specified "+e);
	          
	        } catch (IOException e) {
	            System.err.println("Unable to read the file "+e);
	            
	        }
		
	}
	/*
	 * Getters and Setters
	 */
	
	//get the facts list
	public  ArrayList<String> getFacts() {
		return facts;
	}
	// set the fact 
	public void setFacts(ArrayList<String> facts) {
		this.facts = facts;
	}
    // get the hashMap
	public HashMap<String,String> factExtraction(){
	return factID;
		}
	
	public HashMap<String, String> getFactID() {
		return factID;
	}
     //set the FactID
	public void setFactID(HashMap<String, String> factID) {
		this.factID = factID;
	}
	
}
