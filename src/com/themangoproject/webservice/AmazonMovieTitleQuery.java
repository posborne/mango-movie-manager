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

	private String queryString;
	
    /**
     * Instantiate the query object.
     */
    public AmazonMovieTitleQuery() {
    	this.queryString = "";
    }

    /**
     * Get a list of result objects (giving access to title and asin) of
     * movies that match the title search (top 10).
     * 
     * @return a list of search results.
     */
    public List<AmazonMovieTitleResult> getMoviesList(String queryString) {
    	this.queryString = queryString;
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
            movies = null;
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
            		"&Title=" + URLEncoder.encode(queryString, "UTF-8") +
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
        AmazonMovieTitleQuery q = new AmazonMovieTitleQuery();
        List<AmazonMovieTitleResult> results = q.getMoviesList(queryTitle);
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
