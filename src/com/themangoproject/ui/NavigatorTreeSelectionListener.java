package com.themangoproject.ui;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.MutableTreeNode;

import com.themangoproject.ui.model.navigator.AddListMutableTreeNode;

/**
 * 
 * @author Paul Osborne
 */
public class NavigatorTreeSelectionListener implements TreeSelectionListener {

	JTree tree;
	
	public NavigatorTreeSelectionListener(JTree tree) {
		this.tree = tree;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent tse) {
		MutableTreeNode node = (MutableTreeNode) 
			tree.getLastSelectedPathComponent();
		
		if (node instanceof AddListMutableTreeNode) {
			// Show dialog to create a new set or list
		    SetListDialog sld =
		            new SetListDialog((Mango)tree.getTopLevelAncestor(), true);
		    sld.setLocationRelativeTo((Mango)tree.getTopLevelAncestor());
		    sld.setVisible(true);
		}
	}

}
