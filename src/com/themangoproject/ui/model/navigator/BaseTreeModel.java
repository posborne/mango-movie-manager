package com.themangoproject.ui.model.navigator;

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
