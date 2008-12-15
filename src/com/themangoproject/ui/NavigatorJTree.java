package com.themangoproject.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.themangoproject.ui.model.navigator.BaseTreeModel;
import com.themangoproject.ui.model.navigator.MangoMutableTreeNode;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * Custom JTree for navigation stuff.  A lot of this is handled in the models, 
 * but there was enough with all the listeners and such to justify a separate
 * class.
 * 
 * @author Paul Osborne
 */
public class NavigatorJTree extends JTree {

	/** Generated Serial UID */
	private static final long serialVersionUID = 6808162320368721944L;

	/**
     * Construct the JTree with the base model and set things so that this
     * stinker shows up how we want it to.
     */
    public NavigatorJTree() {
        this.setModel(new BaseTreeModel());
        this.setRootVisible(false);
        this.addTreeSelectionListener(new NavigatorTreeSelectionListener(this));
        this.setShowsRootHandles(true); // make sure we have plus sign
        this.addMouseListener(new NavigatorPopupListener());
    }
    
    /**
     * Listener for popup actions on the JTree.
     * 
     * @author Paul Osborne
     */
    public class NavigatorPopupListener implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {	}
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { }
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}
		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}
		
		/**
		 * Show the popup if the event is a popup trigger
		 * @param e The mouse event.
		 */
		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				int treeRow = NavigatorJTree.this.getRowForLocation(e.getX(), e.getY());
				TreePath treePath = NavigatorJTree.this.getPathForLocation(e.getX(), e.getY());
				if (treeRow > 0) {
					DefaultMutableTreeNode tn = (DefaultMutableTreeNode) treePath.getLastPathComponent();
					if (tn.isLeaf()) {
						MangoMutableTreeNode mangoTN = (MangoMutableTreeNode) tn;
						JPopupMenu popup = mangoTN.getPopupMenu();
						if (popup != null) {
							popup.show((JComponent)e.getSource(), e.getX(), e.getY());
						}
					}
				}
			}
		}
	}
}
