/*
 * Parser accepts a xml file named Cast.xml
 * extracts cast members and prints a line for each
 * Taken from Lab04 Comp9321 2011
 */
package io.github.mattw46.xmlparser;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Matt W
 */
public class Parser {
    public static void main(String[] args) {
        DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	File XmlFile;

	try {
            XmlFile = new File(args[0]);
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(XmlFile);
                        
            NodeList nList = doc.getElementsByTagName("Character");
            
            int size = nList.getLength();
            
            String outString;
            
            for (int i = 0; i < size; i++) {
                Node nNode = nList.item(i);
                
                Element characterElement = (Element) nNode;
                outString = characterElement.getElementsByTagName("Name").item(0).getTextContent();
                
                NodeList nl = characterElement.getElementsByTagName("Diet");
                if (nl.getLength() > 0) {
                    String value = nl.item(0).getTextContent();
                    outString += " eat " + value + " and";
                }
                
                nl = characterElement.getElementsByTagName("Sound");
                int length = nl.getLength();
                boolean firstElement = true;
                for (int j = 0; j < length; j++) {                  
                    if (firstElement) {
                        outString += " make sounds like " + characterElement.getElementsByTagName("Sound").item(j).getTextContent();
                        firstElement = false;
                    }
                    else {
                        outString += " and " + characterElement.getElementsByTagName("Sound").item(j).getTextContent();
                    }
                }
                
                System.out.println(outString);
            }
           
                
        }
        catch (Exception e) {
            System.out.println("Error loading XML file");
            System.out.println(e.toString());
        }
        
    }
}
