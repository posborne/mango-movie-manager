package com.themangoproject.ui.model.navigator;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Paul Osborne
 */
public class SetsMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	private static final long serialVersionUID = -2616587037657452968L;
	private List<String> sets;

	public SetsMutableTreeNode() {
		super("Sets", true);
		fetchSets();
		MangoController.getInstance().addSetsChangeListener(
				new SetsChangeListener());
	}

	private void fetchSets() {
		sets = MangoController.getInstance().getAllSets();
		this.removeAllChildren();
		for (String set : sets) {
			this.add(new SetMutableTreeNode(set));
		}
		this.add(new AddSetMutableTreeNode());
	}

	@Override
	public void doYourThing(Mango mangoPanel) {
		// I guess this doesn't need to do anything
	}
	

	public class SetsChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			SetsMutableTreeNode.this.fetchSets();
			DefaultTreeModel tm = (DefaultTreeModel) UIController.getInstance()
					.getNavigatorTree().getModel();
			tm.nodeStructureChanged(SetsMutableTreeNode.this);
		}
	}


	@Override
	public JPopupMenu getPopupMenu() {
		return null; // I am not a leaf
	}
}
