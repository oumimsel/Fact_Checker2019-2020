import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Class used to check th veracity of the fact used xml file or a wikipedia search
 */
public class FetchFromWikipedia {
	//the url used to search in wiki
	String url = "http://en.wikipedia.org/wiki/";
	// Instantiation of xmlDataStoring class
	XmlDataStoring xml=new XmlDataStoring();

	/**
	 * Method used to fetch the information from wiki and check the veracity of the fact
	 * @param fact : the Fact object
	 * @param statWiki : the wikipedia Statement
	 * @param level: the level of search "0" for infoBox and "1" for all the wikipedia text
	 * @return veracity of the fact
	 */
	
  public String fetchData(Fact fact,String statWiki,int level) {
	  //Variable declaration
	  
	String info="";
	String veracity="0.0";
	String object=fact.getObject().trim();
	String tobeChecked=".*"+object+".*";
	
	/*
	// to search in the xml file
	if(xml.search(fact.getSubject())) {
		System.out.println("check in xml file");
		veracity=xml.checkForInfoXMLFile(fact.getSubject(), fact.getObject(), statWiki);
	}
	else {
	*/
	try {
		
		
		System.out.println("fetching data for:" +fact.getSubject());
		// Jsoup used to parse the given HTML String
		Response res = Jsoup.connect(url + fact.getSubject()).execute();
		String body = res.body();
		//document object represents the HTML DOM
		Document doc = Jsoup.parseBodyFragment(body);
		//Extract all the rows in the infoBox
		Elements infoBox = doc.select(".infobox tr");
		String box="";
		String text="";
		/*
		 // used to store in the xml file
		//loop and iterate through the rows of the infoBox
		for(Element r:infoBox) {
			box =box+r.text() ;
			}
	
		if(level==0) {
			//storing the info in the xml File
		xml.writeXMlFile(fact.getSubject(), fact.getObject(), statWiki, box, "");
		}
		*/
		//get the veracity by sarching the infoBox
		veracity=searchOnInfoBox(infoBox, tobeChecked, statWiki,fact,level);
		//if the veracity is "0.0" and the level is 1 then search also the wiki text
		if(veracity.equals("0.0")&& level==1) {
			Elements wikiText= doc.select(".mw-parser-output > p");
			System.out.println("search in txt");
		     /*
			// used to store info in the xml file
			//processing the wikipedia text
			for(Element t:wikiText) {
				text =text+t.text() ;}
			xml.writeXMlFile(fact.getSubject(), fact.getObject(), statWiki, box, text);
		     */
			veracity=searchOnWikiText(wikiText,object);
		}
	}catch(Exception e) {
		System.out.println("error while fetching information from the wikipedia: "+e);
	}
	
	System.out.println("the veracity "+veracity);
	
	return veracity;
}
  /**
   * Method used to search in the wikipedia infoBox
   * @param rows: Elements of the infoBox
   * @param tobeChecked :object used to be used for pattern matching
   * @param statWiki: wiki statement
   * @param fact: the fact sentence
   * @param level: the level of the search, "0" search in the infoBox, and "1" for searching wikipedia text 
   * @return the veracity either "0.0" if not found or "1.0" if found
   */
  
public String searchOnInfoBox(Elements rows,String tobeChecked,String statWiki,Fact fact,int level) {
	//variable declaration
	String info="";
	String veracity="0.0";
	
	//loop through all the rows of the infoBox
	for(Element r:rows) {
		info =r.text() ;
		System.out.println(info);
		//if a row title contains the object
		if(Pattern.matches(tobeChecked,info)&& level!=0) {
			veracity="1.0";
			break;
		}
		//if a row contains the wiki statement
		else if(info.toLowerCase().contains(statWiki.toLowerCase())) {
		//get the sub rows of this row
		Elements tds = r.select("td");
		//iterate and process the sub rows
		for(Element td:tds) {
		System.out.println("hello"+tds.text());
		System.out.println("the to be cheecked"+fact.getObject());
		//if the sub rows contain the object return 1
		 if(tds.text().contains(fact.getObject())){
			 veracity="1.0";
			 break;
		 }
		 }
		 break;
		 }
		//if the object was not found
		 else {
			 veracity="0.0";
		 }
	}
	return veracity;
}
/**
 * Method used to search in the wikipedia text page without the infoBox
 * @param el :the Elements= doc.select(".mw-parser-output > p");	
 * @param object: the object to be searched in the text
 * @return the veracity of the fact
 */
public String searchOnWikiText(Elements el,String object) {
	//declaration of variable
	String veracity="0.0";
	String text="";
	//loop through all the element of text
	for(Element r:el) {
      text=r.text();
    // System.out.println("text"+text);
      //if the text contains the object then return 1.0
		if(text.contains(object)) {
			veracity="1.0";
			break;
		}
	
	}
	return veracity;
}
}
