
package com.themangoproject.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * AmazonMovieDetails is a simple class that uses the Amazon Associates Web
 * Service from Amazon to get information about a certain movie given an
 * Amazon ASIN number.
 * 
 * @author Kyle Ronning
 * @version 12/7/2008
 */
public class AmazonMovieDetails {
    
    /** Node containing the title */
    private Node titleNode;
    
    /** Node containing the director name */
    private Node directorNode;
    
    /** Node containing the movie rating */
    private Node ratingNode;
    
    /** Node containing the movie runtime */
    private Node runtimeNode;
    
    /** Node containing the movie release date */
    private Node releaseDateNode;
    
    /** Node containing the movie image */
    private Node imageNode;
    
    /** NodeList containing all actor Nodes */
    private NodeList actorNodes;
           
    /**
     * Constructs an AmazonMovieDetails object to get information about a movie
     *  from Amazon.  Call getMovieXMLDocument passing in an ASIN number.
     */
    public AmazonMovieDetails() {
        this.titleNode = null;
        this.directorNode = null;
        this.ratingNode = null;
        this.runtimeNode = null;
        this.releaseDateNode = null;
        this.imageNode = null;
        this.actorNodes = null;
    }
    
    /**
     * Parse the XML from getMovieXMLDocument and sets fields.
     * 
     * @param  movieDocument The XML returned by the request from 
     * getMovieXMLDocument.
     */
    private boolean processMovieRequestDocument(Document movieDocument) {
        if (movieDocument.getElementsByTagName("Code").getLength() == 0) {
            this.setTitleNode(movieDocument.
                    getElementsByTagName("Title").item(0));
            this.setDirectorNode(movieDocument.
                    getElementsByTagName("Director").item(0));
            this.setRatingNode(movieDocument.
                    getElementsByTagName("AudienceRating").item(0));
            this.setReleaseDateNode(movieDocument.
                    getElementsByTagName("ReleaseDate").item(0));
            this.setRuntimeNode(movieDocument.
                    getElementsByTagName("RunningTime").item(0));
            this.setMovieImageNode(movieDocument.
                    getElementsByTagName("MediumImage").item(0));
            this.setActorNodeList(movieDocument.
                    getElementsByTagName("Actor"));
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Gets XML response from Amazon.  If the method returns true then the XML
     * document is valid and the getter methods will be 
     * 
     * @param Asin The ASIN number to use in the request to Amazon.
     */
    public boolean getMovieXMLDocument(String Asin) {
        try {
            URL url = new URL("http://ecs.amazonaws.com/onca/xml?Service=" + 
                "AWSECommerceService&AWSAccessKeyId=1SWY1ER59KJ70KP59TR2&" + 
                "Operation=ItemLookup&ItemId=" + 
                Asin + "&IdType=ASIN&ResponseGroup=Medium");
            try {
                HttpURLConnection httpConnection = 
                        (HttpURLConnection)url.openConnection();
                int responseCode = httpConnection.getResponseCode ( ) ;

                if ( responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream in = httpConnection.getInputStream ( ) ;

                    try {
                        DocumentBuilderFactory dbf = 
                                DocumentBuilderFactory.newInstance ( ) ;
                        DocumentBuilder db = null;
                        try {
                            db = dbf.newDocumentBuilder();
                        } catch (ParserConfigurationException ex) {
                            return false;
                        }
                        Document doc = db.parse( in );
                        boolean valid = this.processMovieRequestDocument(doc); 
                        return valid;
                    } catch(org.xml.sax.SAXException e ) {
                        // Dang it
                        return false;
                    }
                } else {
                    return false;
                } 
            } catch ( IOException e ) { 
                return false;
            } // Catch
        } catch ( MalformedURLException e ) {  
            return false;
        } // Catch
    }
    
    /**
     * Sets the title node.
     * @param titleNode The title node.
     */
    private void setTitleNode(Node titleNode) {
        this.titleNode = titleNode;
    }
    
    /**
     * Gets the String value of the title.  If the title doesn't exist or 
     * getMovieXMLDocument returned false an empty String is returned.
     * 
     * @return The String value of the title.
     */
    public String getTitle() {
        if (this.titleNode == null)
            return "";
        return this.titleNode.getTextContent();
    }
    
    /**
     * Sets the director node.
     * 
     * @param directorNode The director node.
     */
    private void setDirectorNode(Node directorNode) {
        this.directorNode = directorNode;
    }
    
    /**
     * Gets the String value of the director.  If the director doesn't exist or 
     * getMovieXMLDocument returned false exist an empty String is returned.
     * 
     * @return The String value of the director.
     */
    public String getDirector() {
        if (this.directorNode == null)
            return "";
        return this.directorNode.getTextContent();
    }
    
    /**
     * Sets the rating node.
     * @param ratingNode The rating node.
     */
    private void setRatingNode(Node ratingNode) {
        this.ratingNode = ratingNode;
    }
    
    /**
     * Gets the String value of the rating.  If the rating doesn't exist or 
     * getMovieXMLDocument returned false an empty String is returned.
     * 
     * @return The String value of the rating.
     */
    public String getRating() {
        if (this.ratingNode == null)
            return "";
        String rating = this.ratingNode.getTextContent().replaceFirst(" .*", "");
        return rating;
        //return this.ratingNode.getTextContent();
    }
    
    /**
     * Sets the release date node.
     * @param releaseDateNode The release date node.
     */
    private void setReleaseDateNode(Node releaseDateNode) {
        this.releaseDateNode = releaseDateNode;
    }
    
    /**
     * Gets the String value of the release date.  If the release date doesn't
     * exist or getMovieXMLDocument returned false an empty String is returned.
     * 
     * @return The String value of the release date.
     */
    public String getReleaseDate() {
        if (this.releaseDateNode == null)
            return "";
        return this.releaseDateNode.getTextContent().substring(0, 4);
    }
    
    /**
     * Set the runtime node.
     * 
     * @param runtimeNode The runtime node.
     */
    private void setRuntimeNode(Node runtimeNode) {
        this.runtimeNode = runtimeNode;
    }
    
    /**
     * Gets the String value of the runtime.  If the runtime doesn't exist or 
     * getMovieXMLDocument returned false and empty String is returned.
     * 
     * @return The String value of the runtime.
     */
    public String getRuntime() {
        if (this.runtimeNode == null)
            return "";
        return this.runtimeNode.getTextContent();
    }
    
    /**
     * Sets the movie image node.
     * 
     * @param imageNode The movie image node.
     */
    private void setMovieImageNode(Node imageNode) {
        this.imageNode = imageNode;
    }
    
    /**
     * Gets image as an Icon object.  If there is no image null returned.
     * @return The image as an Icon object.
     */
    public Icon getMovieImage() {
        if (this.imageNode == null) 
            return null;
        Icon image;
        try {
            URL url = new URL(this.imageNode.getTextContent());
            image = new ImageIcon(url);
        } catch (MalformedURLException e) {
            image = null;
        }
        return image;
    }
    
    /**
     * Sets the actor nodes
     * @param actorNodes The actor nodes
     */
    private void setActorNodeList(NodeList actorNodes) {
        this.actorNodes = actorNodes;
    }
    
    /**
     * Gets a List of actor names as Strings.  If there are no actors or 
     * getMovieXMLDocument returned false an empty List is returned. 
     * 
     * @return A List of actor names.
     */
    public List<String> getActors() {
        List<String> actors = new ArrayList<String>();
        if (this.actorNodes == null)
            return actors;
        for (int i = 0; i < this.actorNodes.getLength(); i++) {
            actors.add(actorNodes.item(i).getTextContent());
        }
        return actors;
    }
    
    /**
     * A simple application to get information about a movie from Amazon with
     * a provided ASIN number.
     * 
     * @param args String arguments.
     */   
    public static void main(String[] args) {
        //"B0019CSXAM" -- How I met your mother, no director
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter ASIN: ");
        String asin = scan.nextLine();
        AmazonMovieDetails a = new AmazonMovieDetails();
        boolean valid = a.getMovieXMLDocument(asin);
        if (valid) {
            System.out.println("Title: " + a.getTitle());
            System.out.println("Director: " + a.getDirector());
            System.out.println("Rating: " + a.getRating());
            System.out.println("Release Date: " + a.getReleaseDate());
            System.out.println("Runtime: " + a.getRuntime());
            if (a.getMovieImage() == null)
                System.out.println("Image: Null");
            else
                System.out.println("Image: " + a.getMovieImage().toString());
            System.out.println("Actors:");
            List<String> actors = a.getActors();
            for (int i = 0; i < actors.size(); i++) {
                System.out.println("\tActor: " + actors.get(i));
            }
        } else {
            System.out.println("Something failed in the request: calls to " + 
                    "getters will\nreturn empty strings, movie image will " + 
                    "return null.");
        }
    }
}
