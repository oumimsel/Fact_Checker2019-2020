import java.io.File;

import javax.lang.model.element.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
;

/*
 * Class used to store and search for info in the xml file
 */
public class XmlDataStoring {
     // variable declaation
	 public static final String xmlFile = "src/ressource_text/xmlfile.xml";
	 File fileXML = new File(xmlFile);
	 //create a DocumentBuilderFactory instance
	 DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	 //declare DocumentBuilder
     DocumentBuilder icBuilder;
    
    /*
     * create the xml file
     */
   public void createXmlFile() {
      
       try {
           icBuilder = icFactory.newDocumentBuilder();
           //create the Document from the DocumentBuilder
           Document doc = icBuilder.newDocument();

        // fact element
           org.w3c.dom.Element root =  doc.createElement("Facts");
           doc.appendChild((Node) root);

           // subject element
           org.w3c.dom.Element fact = doc.createElement("fact");
           ((Node) root).appendChild((Node) fact);
           // object element
           org.w3c.dom.Element subject=  doc.createElement("subject");
           ((Node) fact).appendChild((Node) subject);

           // infoBox element
            org.w3c.dom.Element infoBox =  doc.createElement("infoBox");
            ((Node) fact).appendChild((Node) infoBox);

           // text elements
           org.w3c.dom.Element text =  doc.createElement("text");
            ((Node) fact).appendChild((Node) text);

           // create the xml file
           //transform the DOM Object to an XML File          
           Transformer transformer = TransformerFactory.newInstance().newTransformer();
           transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
           DOMSource source = new DOMSource(doc);
           StreamResult console = new StreamResult(fileXML);
           transformer.transform(source, console);

           System.out.println("\nXML file Created Successfully..");

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   /**
    * method used to write information w got from wikipedia into xml
    * @param subj: the subject
    * @param obj : the object
    * @param stat: the wiki statement
    * @param box: the infoBox information from wiki
    * @param txt : the wikipedia text
    */
   public void writeXMlFile(String subj,String box,String txt) {
   	 try {
   		    //Instantiate the DocumentBuilder
            DocumentBuilder icBuilder = icFactory.newDocumentBuilder();
            // Document created from parsing the existing Xml file
            Document doc = icBuilder.parse(fileXML);
            org.w3c.dom.Element root = doc.getDocumentElement();
         // subject element
            org.w3c.dom.Element fact = doc.createElement("fact");
           
            // object element
            org.w3c.dom.Element subject=  doc.createElement("subject");
            subject.appendChild(doc.createTextNode(subj));
            ((Node) fact).appendChild((Node) subject);
 
            // infoBox element
             org.w3c.dom.Element infoBox =  doc.createElement("infoBox");
             infoBox.appendChild(doc.createTextNode(box));
             ((Node) fact).appendChild((Node) infoBox);
 
            // text elements
            org.w3c.dom.Element text =  doc.createElement("text");
            text.appendChild(doc.createTextNode(txt));
             ((Node) fact).appendChild((Node) text);
 
             ((Node) root).appendChild((Node) fact);
             
            
            // write the DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
 
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
 
            StreamResult streamResult = new StreamResult(fileXML);
            transformer.transform(domSource, streamResult);
 
        } catch (Exception pce) {
            pce.printStackTrace();
        }
    
   }
   
   /**
    * Method used to search for a certain object if it exists before searchinf in wikipedia
    * @param subjec: the subject to be searched
    * @return true or false
    */
   public boolean search(String subjec) {
  	 boolean found=false;
  	  try {
  		     //Instantiate the DocumentBuilder
  		     DocumentBuilder documentBuilder = icFactory.newDocumentBuilder();
             //create the document from parsing the existing xm file
  		     Document doc = documentBuilder.parse(fileXML);
  		     doc.getDocumentElement().normalize();
              //get the list of all informations which had the tag "fact"
  		     NodeList nodeList = doc.getElementsByTagName("fact");

  		     //process the node list to check if the subject exists
  		     for (int itr = 0; itr < nodeList.getLength(); itr++) {
  		      // get the node from the node list
  		      Node node = nodeList.item(itr);
  		    //get the Elements of this node
  		      org.w3c.dom.Element eElement = (org.w3c.dom.Element) node;
  		     // if the element tag is subject check if its value equal to the name of the subjec
                if((eElement.getElementsByTagName("subject").item(0).getTextContent()).equals(subjec)) {
  		        	 found=true;
  		        	 break;
  		         }
  		         		     }
  		 } catch (Exception e) {
  		     e.printStackTrace();
  		 }

  	System.out.println("the system search is"+found);
  	 return found;
   }


   /**
    * After founding the sbject we check if it contains the object
    * @param sub: the subject
    * @param obj: the oject
    * @param statement: the wiki statement
    * @return the veracity
    */
public String checkForInfoXMLFile( String sub,String obj,String statement) {
	 String veracity="0.0";
	  try {
		  
             DocumentBuilder documentBuilder = icFactory.newDocumentBuilder();
             Document doc = documentBuilder.parse(fileXML);
             doc.getDocumentElement().normalize();
             //get the list of all informations which had the tag "fact"
             NodeList nodeList = doc.getElementsByTagName("fact");
       
             //process the node list to check if the subject exists
    		 for (int itr = 0; itr < nodeList.getLength(); itr++) {
    			 // get the node from the node list
                  Node node = nodeList.item(itr);
                  //get the Elements of this node
                  org.w3c.dom.Element eElement = (org.w3c.dom.Element) node;
	                 // if the element tag is subject check if its value equal to the name of the sub
                  if((eElement.getElementsByTagName("subject").item(0).getTextContent()).equals(sub)) {
		               // get the value of elements with infoBox and text tag name
	             	 String infobox=eElement.getElementsByTagName("infoBox").item(0).getTextContent();
    	             String txt=eElement.getElementsByTagName("text").item(0).getTextContent();
     	               // check if the object exists
    	              if(infobox.contains(obj) || txt.contains(obj)) {
    	             	 veracity="1.0";
    	              	 break;
                        	 }
	                   }

	         		     }
	 } catch (Exception e) {
	     e.printStackTrace();
	 }
	 
return veracity;
}

		}
