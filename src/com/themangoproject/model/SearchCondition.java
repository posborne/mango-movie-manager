/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.model;

/**
 * @author Paul Osborne
 */
public class SearchCondition {
    
    private String attribute;
    private String constraint;
    private String stringValue;
    private int intValue;
    
    public SearchCondition(String attribute, String constraint, String value) {
        this.attribute = attribute;
        this.constraint = constraint;
        this.stringValue = value;
    }
    
    public SearchCondition(String attribute, String constraint, int value) {
        this.attribute = attribute;
        this.constraint = constraint;
        this.intValue = value;
    }
    
    public boolean hasIntegerValue() {
        return stringValue == null;
    }
    
    public String conditionAsSQL() {
        StringBuilder sb = new StringBuilder();
        
        // append attribute
        if (attribute.toLowerCase().equals("mango rating")) {
        	attribute = "mango_rating";
        } else if (attribute.toLowerCase().equals("purchase date")) {
        	attribute = "purchase_date";
        }
        sb.append(attribute.toLowerCase() + " ");

        // append condition
        if (constraint.toLowerCase().equals("is less than")) {
            sb.append("<");
        } else if (constraint.toLowerCase().equals("is greater than")) {
            sb.append(">");
        } else if (constraint.toLowerCase().equals("contains")) {
            sb.append("LIKE");
        } else if  (constraint.toLowerCase().equals("does not contain")) {
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
