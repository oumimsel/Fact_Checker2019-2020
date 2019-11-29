import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FetchFromWikipedia {
	
	String url = "http://en.wikipedia.org/wiki/";
	
  public String fetchData(String subject,String statWiki) {
	String info="";
	String wiki=".*"+statWiki+".*";
	try {
		System.out.println();
		Response res = Jsoup.connect(url + subject).execute();
		String body = res.body();
		Document doc = Jsoup.parseBodyFragment(body);
		Elements el = doc.select(".infobox tr");
		Elements rows = el.select("tr");
		for(Element r:rows) {
			info =r.text() ;
			if(info.contains("Born")) {
			//System.out.println(info);
			 Elements tds = r.select("td");
			 System.out.println(tds.text());
			 if(Pattern.matches(".*Michael.*", tds.text())){
				 System.out.println("match");
			 }
		}
		}
		
	}catch(Exception e) {
		System.out.println("error while fetching information from the wikipedia: "+e);
	}
	return info;
}
public void searchOnWiki(Fact fact,String statforWiki) {
	
}

}
