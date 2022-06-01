/*
 * Servlet takes a cast character as an argument
 * Then returns the corresponding string
 */
package io.github.mattw46.movieservlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 *
 * @author Matt W
 */
public class ServletExercise extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String line = null;
        String castName = request.getParameter("name");
        System.out.println("Cast: " + castName);
        
        try {
            DocumentBuilderFactory factory;
            DocumentBuilder parser;
            Document doc;
            ServletContext context = getServletContext();
            InputStream is = context.getResourceAsStream("/WEB-INF/cast.xml");
            factory = DocumentBuilderFactory.newInstance();
            parser = factory.newDocumentBuilder();
            doc = (Document) parser.parse(is);
            
            NodeList nList = doc.getElementsByTagName("Character");
            
            int size = nList.getLength();
            
            String outString;
            
            for (int i = 0; i < size; i++) {
                Node nNode = nList.item(i);
                
                Element characterElement = (Element) nNode;
                outString = characterElement.getElementsByTagName("Name").item(0).getTextContent().toLowerCase();
                
                if (outString.equals(castName.toLowerCase())) {
                    line = characterElement.getElementsByTagName("Name").item(0).getTextContent();
                    
                    NodeList nl = characterElement.getElementsByTagName("Diet");
                    if (nl.getLength() > 0) {
                        String value = nl.item(0).getTextContent();
                        line += " eat " + value + " and";
                    }
                    
                    nl = characterElement.getElementsByTagName("Sound");
                    int length = nl.getLength();
                    boolean firstElement = true;
                    for (int j = 0; j < length; j++) {                  
                        if (firstElement) {
                            line += " make sounds like " + characterElement.getElementsByTagName("Sound").item(j).getTextContent();
                            firstElement = false;
                        }
                        else {
                            line += " and " + characterElement.getElementsByTagName("Sound").item(j).getTextContent();
                        }
                    }
                    break;
                }
                
                System.out.println("Line: " + line);
            }
            
	}
	catch (Exception e) {
            System.out.println("An error Occured");
            System.out.println(e.toString());
	}
        
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<HTML>");
	out.println("<BODY>");
	out.println("<HEAD>");
	out.println("<TITLE>Static Servlet</TITLE>");
	out.println("</HEAD>");
	out.println("<body>");
	out.println("<CENTER><H1>Hello, World</H1></CENTER>");
	out.println("<p>" + line + "</p>");
	out.println("</BODY>");
	out.println("</HTML>");
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Do nothing
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
