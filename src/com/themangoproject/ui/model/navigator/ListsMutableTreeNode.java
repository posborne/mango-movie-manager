/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.ui.model.navigator;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;

import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Paul Osborne
 */
public class ListsMutableTreeNode extends DefaultMutableTreeNode 
	implements MangoMutableTreeNode {
	
	private static final long serialVersionUID = -2392203223093098396L;
	private List<String> lists;
    
    public ListsMutableTreeNode() {
        super("Lists", true);
        fetchLists();
        // update on changes to list of sets
		MangoController.getInstance().addListsChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ListsMutableTreeNode.this.fetchLists();
			}
		});
    }

	private void fetchLists() {
		this.removeAllChildren();
        lists = MangoController.getInstance().getAllLists();
        for (String list : lists) {
            this.add(new ListMutableTreeNode(list));
        }
        this.add(new AddListMutableTreeNode());
	}

	@Override
	public void doYourThing(Mango mangoPanel) {
		// this does nothing
	}
}
