import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;


/*
 * This class is used to process every fact and determine its subject,
 *  object and statement so te search can be done in xml file or in wikipedia.
 */

public class FactProcessing {
	//instantiating the FetchFromWikipedia class 
 FetchFromWikipedia wiki=new FetchFromWikipedia();
	
/**
 * This method is used to determine the subject, object from the fact sentence and create the Fact object 
 * @param statement: mentioned in the the original fact sentence
 * @param fact : the fact sentence 
 * @param statForWiki : the statement that will be used to search in wiki, ex: Born, Died, History Career...
 * @return the Fact object
 */
public Fact factProcessing(String statement,String fact,String statForWiki) {
	// declaration of variable 
	String veracity="";
	String object="";
	String subject="";
	//"is" is used a separator
	String separator=" is ";
	//take the index of "is"
	int separatorIndex = fact.indexOf(separator); 
	//take the index of the statement
    int statIndex=fact.indexOf(statement);
    
    //if the statement is "Stars" at the beginning of the sentence
    if(statement.equals("Stars")) {
     String sep=" has been ";
     int sepIndex = fact.indexOf(sep);
   	 object=fact.substring(sepIndex+sep.length());
   	 subject=fact.substring(statIndex+statement.length(),sepIndex);
    }else {
    //if the statement is "stars" in the middle of a sentence	
     if(statement.equals("stars")) {
    	 subject=fact.substring(0,statIndex);
    	 object=fact.substring(statIndex+statement.length());
     }else {
     // other statements	 
    // if the statement is at the end of the sentence	 
    if((statIndex+statement.length())==fact.length()) {
	    object = fact.substring(0, separatorIndex);
        subject = fact.substring(separatorIndex+separator.length(),statIndex);	
	}else {
		//statement is at the middle of the sentence
		subject=fact.substring(0,statIndex);
		object = fact.substring(separatorIndex+separator.length());
		
	}}}
    //create the Fact object
	Fact factPartioned=new Fact(subject,statement,object);
	
	return factPartioned;
}
 /**
  * This method is used to determine the wiki statement or the statement that we will be using to search in wikipedia
  * to check the veracity of the fact. ex: Oscar Peterson death place is Mississauga. the subject is "Oscar Peterson", object is
  * "Mississauga", the statement is "death place" and the wiki statement is "Died". 
  * @param fact1 : the fact sentence
  * @return the veracity of the fact.
  */

public String factChecking(String fact1) {
	//variable declaration
	String statement="";
	String wikiStatement="";
	//the level is "0" if we want to check the fact only in the infoBox of wikipedia, and "1" if we want to check all the wikipedia text
	int level=0;
	//delete the unnecessary characters from the fact sentence
	String fact=fact1.trim().replaceAll("(('s)|')+|[\\.]$", "");
	System.out.println("******************************");
	System.out.println(" the fact is " +fact);
	/*
	 * determine the wiki statement according to the original statement
	 */
	
	//if the statement is "stars"
    if(fact.contains("stars")) {
    	statement="stars";
    	wikiStatement="Starring";
    	level=1;
    } if(fact.contains("Stars")) {
    	statement="Stars";
    	wikiStatement="Starring";
    	level=1;
    }
  //if the statement is "team"
    if(fact.contains("team")) {
    	statement="team";
    	wikiStatement="Career history";
    	level=0;
    }
  //if the statement is "nascence place"
    else if(fact.contains("nascence place")){
    	statement="nascence place";
    	wikiStatement="Born"; 	
    	level=0;
    }
   // if the statement is "last place"
    else if(fact.contains("last place")){
    	statement="last place";
    	wikiStatement="Died";
    	level=0;
    }
    // if the statement is "innovation place"
    else if(fact.contains("innovation place")){
    	statement="innovation place";
    	wikiStatement="Founded";
    	level=1;
    }
 // if the statement is "birth place"
    else if(fact.contains("birth place")){
    	statement="birth place";
    	wikiStatement="Born";
    	level=0;
    }
 // if the statement is "generator"
    else if(fact.contains("generator")){
    	statement="generator";
    	level=0;
    	wikiStatement="Author";
    }
 // if the statement is "author"
    else if(fact.contains("author")){
    	statement="author";
    	level=1;
    	wikiStatement="Author"; 	
    }
 // if the statement is "death place"
    else if(fact.contains("death place")){
    	statement="death place";
    	wikiStatement="Died";
    	level=0;
    }
 // if the statement is "subsidiary"
    else if(fact.contains("subsidiary")){
    	statement="subsidiary";
    	wikiStatement="Subsidiaries";
    	level=1;
    }
 // if the statement is "spouse"
    else if(fact.contains("spouse")){
    	statement="spouse";
    	level=0;
    	wikiStatement="Spouse(s)";
    }
 // if the statement is "better half"
    else if(fact.contains("better half")){
    	statement="better half";
    	level=0;
    	wikiStatement="Spouse(s)";
    }
 // if the statement is "award"
    else if(fact.contains("award")){
    	statement="award";
    	wikiStatement="Awards";
    	level=1;
    }
 // if the statement is "foundation place"
    else if(fact.contains("foundation place")){
    	statement="foundation place";
    	wikiStatement="Founded";
    	level=1;
    }
 // if the statement is "office"
    else if(fact.contains("office")){
    	statement="office";
    	wikiStatement="Prime Minister";
    	level=1;
    }
 // if the statement is "role"
    else if(fact.contains("role")){
    	statement="role";
    	wikiStatement="Prime Minister";
    	level=1;
    }
 // if the statement is "squad"
    else if(fact.contains("squad")){
    	statement="squad";
    	wikiStatement="Career history";
    	level=1;
    }
 // if the statement is "better half"
    else if(fact.contains("better half")){
    	statement="better half";
    	wikiStatement="Spouse(s)";
    	level=0;
    }else if(fact.contains("subordinate")){
    	statement="subordinate";
    	wikiStatement="Subsidiaries";
    	level=1;
    }
 // if the statement is "honour"
    else if(fact.contains("honour")){
    	statement="honour";
    	wikiStatement="Awards";
    	level=1;
    }
    //create the Fact object
	Fact f=factProcessing(statement, fact,wikiStatement);
	/*
	 * after checking the veracity in the class FetchFromWikipedia.java return the result
	 * fetchData method from FetchFromWikipedia class takes the variable : f(Fact), wikiStatement, and the level of the search
	 */
   return	wiki.fetchData(f, wikiStatement,level);
}


}


