package com.themangoproject.ui;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.MutableTreeNode;
import com.themangoproject.ui.model.navigator.MangoMutableTreeNode;

/**
 * A TreeSelectionListener to listen for changes.
 * 
 * @author Paul Osborne
 */
public class NavigatorTreeSelectionListener implements TreeSelectionListener {

	JTree tree;

        /**
         * Constructor
         * @param tree The tree
         */
	public NavigatorTreeSelectionListener(JTree tree) {
		this.tree = tree;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event
	 * .TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent tse) {
		MutableTreeNode node = (MutableTreeNode) tree
				.getLastSelectedPathComponent();
		if (node != null) {
			((MangoMutableTreeNode) node).doYourThing((Mango) tree
					.getTopLevelAncestor());
		}
	}

}
