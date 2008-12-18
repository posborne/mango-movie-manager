package com.themangoproject.webservice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Class for executing and retrieving the results of a query to Amazon for
 * movies that have title similar to the search title
 * 
 * @author Paul Osborne
 * @version December 17, 2008
 */
public class AmazonMovieTitleQuery extends AmazonQuery {

    /** The title to query for */
    private String queryTitle;

    /**
     * Instantiate the query object.
     */
    public AmazonMovieTitleQuery(String title) {
        this.queryTitle = title;
    }

    /**
     * Set the title to query for.
     * 
     * @param queryTitle
     *            The title that should be queried for.
     */
    public void setQueryTitle(String queryTitle) {
        this.queryTitle = queryTitle;
    }

    /**
     * Get the currently set query title
     * 
     * @return the current query title
     */
    public String getQueryTitle() {
        return queryTitle;
    }
    
    /**
     * Get a list of result objects (giving access to title and asin) of
     * movies that match the title search (top 10).
     * 
     * @return a list of search results.
     */
    public List<AmazonMovieTitleResult> getMoviesList() {
        List<AmazonMovieTitleResult> movies = new ArrayList<AmazonMovieTitleResult>();
        Document d = this.queryAmazon();
        if (d != null) {
            NodeList items = d.getElementsByTagName("Item");
            for (int i = 0; i < items.getLength(); i++) {
                Element item = (Element)items.item(i);
                String title = item.getElementsByTagName("Title").item(0).getTextContent();
                String asin = item.getElementsByTagName("ASIN").item(0).getTextContent();
                movies.add(new AmazonMovieTitleResult(title, asin));
            }
        } else {
            System.out.println("Something went wrong");
        }
        return movies;
    }

    /**
     * For the query to AWS we will be doing an ItemSearch on the DVD index with
     * response being item attributes.
     * 
     * @return The appropriate AWS search URL.
     */
    @Override
    public String getURL() {
        String associatesID = AmazonMovieUtility.getInstance().getAssociatesID();
        try {
            return "http://ecs.amazonaws.com/onca/xml" +
            		"?Service=AWSECommerceService" +
            		"&AWSAccessKeyId=" + associatesID +
            		"&Operation=ItemSearch" +
            		"&Title=" + URLEncoder.encode(queryTitle, "UTF-8") +
            		"&SearchIndex=DVD" +
            		"&ResponseGroup=ItemAttributes";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Search for Title: ");
        String queryTitle = scan.nextLine();
        AmazonMovieTitleQuery q = new AmazonMovieTitleQuery(queryTitle);
        List<AmazonMovieTitleResult> results = q.getMoviesList();
        for (AmazonMovieTitleResult r : results) {
            System.out.println(r.getTitle() + " - " + r.getASIN());
        }
    }

    /**
     * Result object for movie query to Amazon.
     */
    public class AmazonMovieTitleResult {
        private String title;
        private String asin;
        public AmazonMovieTitleResult(String title, String asin) {
            this.title = title;
            this.asin = asin;
        }
        
        public String getASIN() {
            return asin;
        }
        public String getTitle() {
            return title;
        }

    }
    
}
