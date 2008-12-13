package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultTreeModel;

/**
 * @author Paul Osborne
 */
public class BaseTreeModel extends DefaultTreeModel  {
	private static final long serialVersionUID = -4245509182622434994L;
	public BaseTreeModel() {
        super(new NavigatorRootNode());
    }
}
