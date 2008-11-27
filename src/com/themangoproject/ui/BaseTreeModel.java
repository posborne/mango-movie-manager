package com.themangoproject.ui;

import javax.swing.tree.DefaultTreeModel;


/**
 *
 * @author Paul Osborne
 */
public class BaseTreeModel extends DefaultTreeModel  {
    public BaseTreeModel() {
        super(new NavigatorRootNode());
    }
}
