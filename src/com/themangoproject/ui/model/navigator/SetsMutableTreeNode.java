/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.ui.model.navigator;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;

import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Paul Osborne
 */
public class SetsMutableTreeNode extends DefaultMutableTreeNode 
	implements MangoMutableTreeNode {
	
	private static final long serialVersionUID = -2616587037657452968L;
	private List<String> sets;
    
    public SetsMutableTreeNode() {
        super("Sets", true);
        sets = MangoController.getInstance().getAllSets();
        for (String set : sets) {
            this.add(new SetMutableTreeNode(set));
        }
        this.add(new AddSetMutableTreeNode());
    }

	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO: I guess this doesn't need to do anything
	}
}
