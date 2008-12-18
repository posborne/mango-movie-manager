package com.themangoproject.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * AmazonMovieDetails is a simple class that uses the Amazon Associates Web
 * Service from Amazon to get information about a certain movie given an Amazon
 * ASIN number.
 * 
 * @author Kyle Ronning
 * @version 12/7/2008
 */
public class AmazonMovieASINQuery extends AmazonQuery {

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

    /** The currently set ASIN to search on */
    private String asin;

    /**
     * Is the query in a good state? That is, is there an ASIN set and does that
     * ASIN match to one in the amazon database?
     */
    private boolean goodState;

    /**
     * Constructs an AmazonMovieDetails object to get information about a movie
     * from Amazon. Call getMovieXMLDocument passing in an ASIN number.
     * 
     * @param asin
     *            The ASIN to query on.
     */
    public AmazonMovieASINQuery(String asin) {
        this.titleNode = null;
        this.directorNode = null;
        this.ratingNode = null;
        this.runtimeNode = null;
        this.releaseDateNode = null;
        this.imageNode = null;
        this.actorNodes = null;
        this.goodState = false;
        this.asin = asin;
    }

    /**
     * Execute the query. If the query is successful the state will be good, if
     * not it will be bad.
     */
    public void executeSearch() {
        boolean success = processMovieRequestDocument(this.queryAmazon());
        this.goodState = success;
    }

    /**
     * Set the ASIN to be the specified string
     * 
     * @param asin
     *            The new asin value.
     */
    public void setASIN(String asin) {
        this.asin = asin;
        this.goodState = false;
    }

    /**
     * Return a boolean telling whether the object is in a good state. The
     * object is in a good state when the object has been populated with values
     * successfully grabbed from amazon for the currently set ASIN. If the ASIN
     * is changed, the previous data will no longer be accessible.
     * 
     * @return true if the object is in a good state and false if not.
     */
    public boolean isValid() {
        return this.goodState;
    }

    /**
     * Parse the XML from getMovieXMLDocument and sets fields.
     * 
     * @param movieDocument
     *            The XML returned by the request from getMovieXMLDocument.
     */
    private boolean processMovieRequestDocument(Document movieDocument) {
        if (movieDocument.getElementsByTagName("Code").getLength() == 0) {
            this.titleNode = movieDocument.getElementsByTagName("Title").item(0);
            this.directorNode = movieDocument.getElementsByTagName("Director")
                    .item(0);
            this.ratingNode = movieDocument
                    .getElementsByTagName("AudienceRating").item(0);
            this.releaseDateNode = movieDocument.getElementsByTagName(
                    "ReleaseDate").item(0);
            this.runtimeNode = movieDocument.getElementsByTagName("RunningTime")
                    .item(0);
            this.imageNode = movieDocument.getElementsByTagName("MediumImage")
                    .item(0).getFirstChild();
            this.actorNodes = movieDocument.getElementsByTagName("Actor");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the String value of the title. If the title doesn't exist or
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
     * Gets the String value of the director. If the director doesn't exist or
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
     * Gets the String value of the rating. If the rating doesn't exist or
     * getMovieXMLDocument returned false an empty String is returned.
     * 
     * @return The String value of the rating.
     */
    public String getRating() {
        if (this.ratingNode == null)
            return "";
        String rating = this.ratingNode.getTextContent().replaceFirst(" .*", "");
        return rating;
        // return this.ratingNode.getTextContent();
    }

    /**
     * Gets the String value of the release date. If the release date doesn't
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
     * Gets the String value of the runtime. If the runtime doesn't exist or
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
     * Returns the url were the image for the movie is located.
     * 
     * @return The url where the movie image is located.
     */
    public URL getMovieURL() {
        if (this.imageNode == null)
            return null;
        URL url;
        try {
            url = new URL(this.imageNode.getTextContent());
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }

    /**
     * Gets image as an Icon object. If there is no image null returned.
     * 
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
     * Gets a List of actor names as Strings. If there are no actors or
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
     * Run the test application for AWS.
     * 
     * @param args
     *            Command line arguments (not used)
     */
    public static void main(String[] args) {
        // "B0019CSXAM" -- How I met your mother, no director
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter ASIN: ");
        String asin = scan.nextLine();
        AmazonMovieASINQuery a = new AmazonMovieASINQuery(asin);
        a.executeSearch();
        if (a.isValid()) {
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
            System.out.println("Something failed in the request: calls to "
                    + "getters will\nreturn empty strings, movie image will "
                    + "return null.");
        }
    }

    @Override
    public String getURL() {
        return "http://ecs.amazonaws.com/onca/xml?Service="
                + "AWSECommerceService&AWSAccessKeyId="
                + AmazonMovieUtility.getInstance().getAssociatesID() + "&"
                + "Operation=ItemLookup&ItemId=" + asin
                + "&IdType=ASIN&ResponseGroup=Medium";
    }
}
