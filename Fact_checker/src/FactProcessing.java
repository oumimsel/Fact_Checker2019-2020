import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class FactProcessing {
 
	FetchFromWikipedia wiki=new FetchFromWikipedia();
	

public void factPartitioning(String statement,String fact,String statForWiki) {
	 String object="";
	 String subject="";
	 int statIndex=0;
	 int separatorIndex = fact.indexOf("is");

	 statIndex=fact.indexOf(statement);
	if((statIndex+statement.length())==fact.length()) {
	    object = fact.substring(0, separatorIndex);
      subject = fact.substring(separatorIndex+2,statIndex);	
	}else {
		subject=fact.substring(0, statIndex);
		object = fact.substring(separatorIndex);
		
	}
	Fact factPartioned=new Fact(subject,statement,object);
	wiki.searchOnWiki(factPartioned,statForWiki);
}


public void proessingFact(String fact1) {
	String statement="";
	String wikiStatement="";
		String fact=fact1.trim().replaceAll("(('s)|\\.|')+","");
	System.out.println(" the fact is " +fact);
		
    if(fact.contains("stars")) {
    	statement="stars";
    	wikiStatement="Starring";
    	
    }else if(fact.contains("nascence place")){
    	statement="nascence place";
    	wikiStatement="Born"; 	
    }else if(fact.contains("last place")){
    	statement="last place";
    	wikiStatement="Died";
    }else if(fact.contains("innovation place")){
    	statement="innovation place";
    	wikiStatement="Born";
    }
    else if(fact.contains("author")){
    	statement="author";
   		
   	
    	
    }else if(fact.contains("death place")){
    	statement="death place";
    	wikiStatement="Died";
    }else if(fact.contains("subsidiary")){
    	statement="subsidiary";
    	wikiStatement="Subsidiary";
    }else if(fact.contains("spouse")){
    	statement="spouse";
    	wikiStatement="Spouse(s)";
    }else if(fact.contains("better half")){
    	statement="Spouse(s)";
    	wikiStatement="Spouse(s)";
    }else if(fact.contains("award")){
    	statement="Awards";
    }else if(fact.contains("foundation place")){
    	statement="foundation place";
    	wikiStatement="Headquarters";
    }else if(fact.contains("office")){
    	statement="office";
    	wikiStatement="Officce";
    }else if(fact.contains("role")){
    	statement="role";
    	wikiStatement="Role";
    }else if(fact.contains("birth place")){
    	statement="born";
    	wikiStatement="Born";
    }else if(fact.contains("squad")){
    	statement="squad";
    	statement="Carrer";
    }else if(fact.contains("innovation place")){
    	statement="innovation place";
    	wikiStatement="Headquarters";
    }else if(fact.contains("generator")){
    	statement="generator";
    }else if(fact.contains("better half")){
    	statement="better half";
    	wikiStatement="Spouse(s)";
    }else if(fact.contains("subordinate")){
    	statement="subordinate";
    	wikiStatement="Subsidiary";
    }
	factPartitioning(statement, fact,wikiStatement);
	
}


}




/*
*	 //Loading the tokenizer model 
	         InputStream inputStreamTokenizer = new 
	         FileInputStream("src/lib/da-token.bin"); 
	        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
	      //Instantiating the TokenizerME class 
	      TokenizerME tokenizer = new TokenizerME(tokenModel); 
	      String tokens[] = tokenizer.tokenize(fact); 
	      System.out.println("the tockens is");
	      for(int i=0;i<tokens.length;i++) {
	    	  System.out.print(" "+tokens[i]);
	      }
	      InputStream inputStreamNameFinder = new 
	       FileInputStream("src/lib/en-ner-person.bin");       
	      TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder); 
	        
	      //Instantiating the NameFinderME class 
	      NameFinderME nameFinder = new NameFinderME(model);      
	        
	      //Finding the names of a location 
	      Span nameSpans[] = nameFinder.find(tokens);     
	      if(nameSpans==null){
	    	  
	    	  System.out.println("name not found");
	      }
	      //Printing the spans of the locations in the sentence 
	      for(Span s: nameSpans)        
	         System.out.println("the name is "+s.toString()+"  "+tokens[s.getStart()]);	

*/