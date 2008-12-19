package com.themangoproject.webservice;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * Abstract class containing basic functionality for any query to Amazon.
 * 
 * @author Paul Osborne
 * @version December 17, 2008
 */
public abstract class AmazonQuery {

    /**
     * Query amazon using the URL returned by getURL(). Return the W3 Document
     * that results from the query.
     * @param queryString 
     * 
     * @return a W3 document with the results of the search or null if there is
     *         an error.
     */
    public Document queryAmazon() {
        try {
            URL url = new URL(getURL());
            HttpURLConnection httpConnection = (HttpURLConnection) url
                    .openConnection();
            int responseCode = httpConnection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            InputStream in = httpConnection.getInputStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(in);
            return doc;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("What huh?");
            return null;
        }
    }

    /**
     * Get the query execution URL.
     * 
     * @return The query execution URL as a string.
     */
    public abstract String getURL();
}
