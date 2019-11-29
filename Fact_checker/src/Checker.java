
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class Checker {
	public static void main(String[] args) throws IOException  {
		File inputFile= new File("src/ressource_text/SNLP2019_training.tsv");
		ReadInputFile read= new ReadInputFile(inputFile);
		read.readFile();
		FactProcessing n=new FactProcessing();
		ArrayList<String> fact=read.getFacts();
		int size=fact.size();
		String fact1="A Hard Day's Night (film) stars Paul McCartney";
		String fact2="Santiago de Cuba is Desi Arnaz's nascence place";
	//	n.proessingFact(fact2);
		
		//System.out.println("the facts are :"+read.factExtraction().toString());
		//FetchFromWikipedia wiki=new FetchFromWikipedia();
		//System.out.println(wiki.fetchData("michael jackson","hi"));
		
		writeResultToFile();
	}
	public static void writeResultToFile() throws IOException {
		//String file="test.ttl";
		String result="test";
		File file = new File("test.ttl");
		
		System.out.println(file.createNewFile());
		System.out.println(file.exists());
		System.out.println(file.getAbsolutePath());
		System.out.println( file.toString());
		
		//if(file.createNewFile()) {
		//System.out.println("create file");
		String Fact_URI="http://swc2017.aksw.org/task2/dataset/Fact-ID";
		String  prop_URI="http://swc2017.aksw.org/hasTruthValue";
		String type="<http://www.w3.org/2001/XMLSchema#double>";
		//<Fact-URI> <prop-URI> "value"^^type 
		String str = "Hello";
	//	result += ( Fact_URI+ e.getKey() + "> " + prop_URI + " \"" + rate(e.getValue()) + "\"" + type + " .\n");
	   // BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    FileWriter writer = new FileWriter(file);
	    writer.write(result);
		System.out.println( file.toString());
		
	     
	    writer.close();
	}
}
