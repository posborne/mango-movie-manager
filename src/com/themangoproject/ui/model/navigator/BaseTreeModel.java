package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultTreeModel;

import com.themangoproject.ui.Mango;

/**
 * @author Paul Osborne
 */
public class BaseTreeModel extends DefaultTreeModel implements MangoMutableTreeNode  {
	private static final long serialVersionUID = -4245509182622434994L;
	public BaseTreeModel() {
        super(new NavigatorRootNode());
    }
	@Override
	public void doYourThing(Mango mangoPanel) {
		// I'm just a hidden node... I don't need to do anything.
	}
}
