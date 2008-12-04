
package com.themangoproject.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
 * @version 12/3/2008
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
    
    /** Tells if the ASIN was valid */
    private boolean asinValid;
        
    /**
     * Constructs an AmazonMovieDetails object from an ASIN number 
     * <code>Asin</code>.  Information about the movie is gathered from Amazon.
     * 
     * @param Asin The ASIN number for the movie.
     */
    public AmazonMovieDetails(String Asin) {
        this.getMovieXMLDocument(Asin);
    }
    
    /**
     * Parse the XML from getMovieXMLDocument and sets fields.
     */
    private void processMovieRequestDocument(Document movieDocument) {
        if (movieDocument.getElementsByTagName("Code").getLength() == 0) {
            this.asinValid = true;
            this.setTitleNode(movieDocument.getElementsByTagName("Title").item(0));
            this.setDirectorNode(movieDocument.getElementsByTagName("Director").item(0));
            this.setRatingNode(movieDocument.getElementsByTagName("AudienceRating").item(0));
            this.setReleaseDateNode(movieDocument.getElementsByTagName("ReleaseDate").item(0));
            this.setRuntimeNode(movieDocument.getElementsByTagName("RunningTime").item(0));
            this.setMovieImageNode(movieDocument.getElementsByTagName("MediumImage").item(0));
            this.setActorNodeList(movieDocument.getElementsByTagName("Actor"));
        } else {
            this.asinValid = false;
        }
    }
    
    /**
     * Gets XML response from Amazon and sends the response document to
     * processMovieRequestDocument.
     */
    private void getMovieXMLDocument(String Asin) {
        try {
            URL url = new URL("http://ecs.amazonaws.com/onca/xml?Service=AWSECommerceService&AWSAccessKeyId=1SWY1ER59KJ70KP59TR2&Operation=ItemLookup&ItemId=" + 
                    Asin + "&IdType=ASIN&ResponseGroup=Medium");
            try {
                HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
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
                            this.asinValid = false;
                        }
                        Document doc = db.parse( in );
                        this.processMovieRequestDocument(doc);    
                    } catch(org.xml.sax.SAXException e ) {
                        // Dang it
                        this.asinValid = false;
                    }
                } else {
                        this.asinValid = false;
                } 
            } catch ( IOException e ) { 
                this.asinValid = false;
            } // Catch
        } catch ( MalformedURLException e ) {  
            this.asinValid = false;
        } // Catch
        this.asinValid = false;
    }
    
    /**
     * 
     * @param titleNode
     */
    private void setTitleNode(Node titleNode) {
        this.titleNode = titleNode;
    }
    
    /**
     * 
     * @return
     */
    public String getTitle() {
        return this.titleNode.getTextContent();
    }
    
    /**
     * 
     * @param directorNode
     */
    private void setDirectorNode(Node directorNode) {
        this.directorNode = directorNode;
    }
    
    /**
     * 
     * @return
     */
    public String getDirector() {
        return this.directorNode.getTextContent();
    }
    
    /**
     * 
     * @param ratingNode
     */
    private void setRatingNode(Node ratingNode) {
        this.ratingNode = ratingNode;
    }
    
    /**
     * 
     * @return
     */
    public String getRating() {
        return "";
    }
    
    /**
     * 
     * @param releaseDateNode
     */
    private void setReleaseDateNode(Node releaseDateNode) {
        this.releaseDateNode = releaseDateNode;
    }
    
    /**
     * 
     * @return
     */
    public String getReleaseDate() {
        return this.releaseDateNode.getTextContent().substring(0, 4);
    }
    
    /**
     * 
     * @param runtimeNode
     */
    private void setRuntimeNode(Node runtimeNode) {
        this.runtimeNode = runtimeNode;
    }
    
    /**
     * 
     * @return
     */
    public String getRuntime() {
        return this.runtimeNode.getTextContent();
    }
    
    /**
     * 
     * @param imageNode
     */
    private void setMovieImageNode(Node imageNode) {
        this.imageNode = imageNode;
    }
    
    /**
     * 
     * @return
     */
    public Icon getMovieImage() {
        Icon image = null;
        try {
            URL url = new URL(this.imageNode.getTextContent());
            image = new ImageIcon(url);
        } catch (MalformedURLException e) {
            // Way Bad
        }
        return image;
    }
    
    /**
     * 
     * @param actorNodes
     */
    private void setActorNodeList(NodeList actorNodes) {
        this.actorNodes = actorNodes;
    }
    
    /**
     * 
     * @return
     */
    public String[] getActors() {
        String[] actors = new String[this.actorNodes.getLength()];
        for (int i = 0; i < this.actorNodes.getLength(); i++) {
            actors[i] = actorNodes.item(i).getTextContent();
        }
        return actors;
    }
    
    public boolean isAsinValid() {
        return this.asinValid;
    }
       
}
