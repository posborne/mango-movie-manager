package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultTreeModel;

/**
 * The Navigator Tree Model base.
 * 
 * @author Paul Osborne
 */
public class BaseTreeModel extends DefaultTreeModel {
    /** Generated UID */
    private static final long serialVersionUID = -4245509182622434994L;

    /** Construct the base tree model */
    public BaseTreeModel() {
        super(new NavigatorRootNode());
    }
}
