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
public class ListsMutableTreeNode extends DefaultMutableTreeNode {
    
    private List<String> lists;
    
    public ListsMutableTreeNode() {
        super("Lists", true);
        lists = MangoController.getInstance().getAllSets();
        for (String list : lists) {
            this.add(new DefaultMutableTreeNode(list));
        }
        this.add(new AddListMutableTreeNode());
    }
}
