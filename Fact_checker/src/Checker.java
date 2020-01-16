
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
		// file declaration 
		File inputFileTrain= new File("src/ressource_text/SNLP2019_training.tsv");
		File outputTrain=new File("src/results/trainResult.ttl");
		File inputFileTest= new File("src/ressource_text/SNLP2019_test.tsv");
		File outputTest=new File("src/results/testResult.ttl");
		
		//write the results
		// WriteOutputFile.writeResultToFile(inputFileTrain,outputTrain);
	     WriteOutputFile.writeResultToFile(inputFileTest,outputTest);
	    
	}
		
}
