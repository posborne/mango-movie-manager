package com.themangoproject.ui.model.navigator;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Paul Osborne
 */
public class SetsMutableTreeNode extends DefaultMutableTreeNode
		implements MangoMutableTreeNode {

	private static final long serialVersionUID = -2616587037657452968L;
	private List<String> sets;

	public SetsMutableTreeNode() {
		super("Sets", true);
		fetchSets();
		
		MangoController.getInstance().addSetsChangeListener(
				new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent ce) {
						fetchSets();
						((DefaultTreeModel) UIController.getInstance()
								.getNavigatorTree().getModel())
								.nodeStructureChanged(SetsMutableTreeNode.this);
					}
			});
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
}
