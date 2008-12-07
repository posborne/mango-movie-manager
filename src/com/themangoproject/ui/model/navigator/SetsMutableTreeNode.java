/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.ui.model.navigator;

import com.themangoproject.model.MangoController;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Paul Osborne
 */
public class SetsMutableTreeNode extends DefaultMutableTreeNode {
     private List<String> sets;
    
     public SetsMutableTreeNode() {
        super("Sets", true);
        sets = MangoController.getInstance().getAllSets();
        sets.add("fillerSet");
        for (String set : sets) {
            this.add(new DefaultMutableTreeNode(set));
        }
        this.add(new DefaultMutableTreeNode("Add New Set"));
    }
}
