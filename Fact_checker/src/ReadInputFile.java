import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;



public class ReadInputFile {
    File inputFile;
	HashMap<String,String> factID=new HashMap<String,String>();
	HashMap<String,Boolean> factVeracity= new HashMap<String,Boolean>(); 
	ArrayList<String> facts=new ArrayList<>();
	
	public ReadInputFile(File inputFile) {
		super();
		this.inputFile = inputFile;
	}

	
	public void readFile()  {
		 BufferedReader br = null;
	        String fact = "";
	        boolean veracity=false;
	        try {
	            br = new BufferedReader(new FileReader(inputFile));
	             while( (fact = br.readLine()) != null){
	             //   System.out.println(fact);

					String[] factTriplet=fact.split("\t");
					
					
				   if(factTriplet[0].matches("\\d+")) {
						
					factID.put(factTriplet[0],factTriplet[1]);
					facts.add(factTriplet[1]);
					if(factTriplet[2]=="1.0"){veracity=true;}
					factVeracity.put(factTriplet[0], veracity);
	            }}
	        } catch (FileNotFoundException e) {
	            System.err.println("Please check for the presence of file in the path specified "+e);
	          
	        } catch (IOException e) {
	            System.err.println("Unable to read the file "+e);
	            
	        }
		
	}
	
	
	public  ArrayList<String> getFacts() {
		return facts;
	}


	public void setFacts(ArrayList<String> facts) {
		this.facts = facts;
	}


	public HashMap<String,String> factExtraction(){

		return factID;
		
	}
	public HashMap<String,Boolean> veracityExtraction(){
		
		return factVeracity;
		
	}
	
}
