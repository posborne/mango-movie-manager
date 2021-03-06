package com.themangoproject.model;

/**
 * SearchCondition is a class that translates from information
 * gathered in the SavedSearchDialog to SQL compatible phrases.
 * 
 * @author Paul Osborne
 */
public class SearchCondition {

    /** these are private */
    private String attribute;
    private String constraint;
    private String stringValue;
    private int intValue;

    /**
     * Constructor for a search constraint with a string value.
     * 
     * @param attribute
     * @param constraint
     * @param value
     */
    public SearchCondition(String attribute, String constraint,
            String value) {
        this.attribute = attribute;
        this.constraint = constraint;
        this.stringValue = value;
    }

    /**
     * Constructor for a search constraint with an int value.
     * 
     * @param attribute
     * @param constraint
     * @param value
     */
    public SearchCondition(String attribute, String constraint,
            int value) {
        this.attribute = attribute;
        this.constraint = constraint;
        this.intValue = value;
    }

    /**
     * Returns true if the search involves an integer.
     * 
     * @return True if the search involves an integer.
     */
    public boolean hasIntegerValue() {
        return stringValue == null;
    }

    /**
     * Creates the condition as SQL from the information provided in
     * the constructor.
     * 
     * @return A string of SQL.
     */
    public String conditionAsSQL() {
        StringBuilder sb = new StringBuilder();

        // append attribute
        if (attribute.toLowerCase().equals("mango rating")) {
            attribute = "M.mango_rating";
        } else if (attribute.toLowerCase().equals("purchase date")) {
            attribute = "M.purchase_date";
        } else if (attribute.toLowerCase().equals("actor first name")) {
        	attribute = "A.first_name";
        } else if (attribute.toLowerCase().equals("actor last name")) {
        	attribute = "A.last_name";
        } else if (attribute.toLowerCase().equals("genre")) {
        	attribute = "G.name";
        } else if (attribute.toLowerCase().equals("character")) {
        	attribute = "AR.character";
        } else if (attribute.toLowerCase().equals("title")) {
        	attribute = "M.title";
        } else if (attribute.toLowerCase().equals("director")) {
        	attribute = "M.director";
        } else if (attribute.toLowerCase().equals("rating")) {
        	attribute = "M.rating";
        } else if (attribute.toLowerCase().equals("runtime")) {
        	attribute = "M.runtime";
        } else if (attribute.toLowerCase().equals("year")) {
        	attribute = "M.year";
        } else if (attribute.toLowerCase().equals("description")) {
        	attribute = "M.custom_description";
        } else if (attribute.toLowerCase().equals("condition")) {
        	attribute = "M.condition";
        } else if (attribute.toLowerCase().equals("type")) {
        	attribute = "M.type";
        }
        sb.append(attribute.toLowerCase() + " ");

        // append condition
        if (constraint.toLowerCase().equals("is less than")) {
            sb.append("<");
        } else if (constraint.toLowerCase().equals("is greater than")) {
            sb.append(">");
        } else if (constraint.toLowerCase().equals("contains")) {
            sb.append("LIKE");
        } else if (constraint.toLowerCase()
                .equals("does not contain")) {
            sb.append("NOT LIKE");
        } else if (constraint.toLowerCase().equals("is")) {
            sb.append("=");
        } else if (constraint.toLowerCase().equals("is not")) {
            sb.append("<>");
        } else { // no match, this is a bad constraint
            return "";
        }

        // append value
        if (this.hasIntegerValue()) {
            sb.append(" " + intValue);
        } else {
            if (constraint.toLowerCase().contains("contain")) {
                sb.append(" '%" + stringValue + "%'");
            } else {
                sb.append(" '" + stringValue + "'");
            }
        }
        return sb.toString();
    }
}
