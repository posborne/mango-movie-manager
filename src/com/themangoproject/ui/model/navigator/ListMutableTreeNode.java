package com.themangoproject.ui.model.navigator;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.ListEditableMovieTableModel;

/**
 * Tree node for representing a launching the action of display a
 * list.
 * 
 * @author Paul Osborne
 */
public class ListMutableTreeNode extends DefaultMutableTreeNode
        implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -6490024778496666267L;

    /** List label being represented */
    private String listLabel;

    /** Construct the node */
    public ListMutableTreeNode(String listLabel) {
        super(listLabel);
        this.listLabel = listLabel;
    }

    /**
     * Set the table to display the contents of the list this node is
     * representing
     */
    @Override
    public void doYourThing(Mango mangoPanel) {
        UIController.getInstance().setViewTableModel(
                new ListEditableMovieTableModel(listLabel));
    }

    /**
     * Get a popup menu with actions on the list. Right now the
     * options are: - Delete List - Rename List
     */
    @Override
    public JPopupMenu getPopupMenu() {
        JMenuItem removeListItem = new JMenuItem("Delete List");
        removeListItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) e
                        .getSource(),
                        "Are you sure you want to remove the list \""
                                + listLabel + "\"?",
                        "Confirm List Deletion",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    MangoController.getInstance().deleteList(
                            listLabel);
                }
            }
        });

        // Rename Set Item
        JMenuItem renameListItem = new JMenuItem("Rename List");
        renameListItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog(
                        (Component) e.getSource(), "Rename \""
                                + listLabel + "\" to?",
                        "Rename List", JOptionPane.PLAIN_MESSAGE);
                if (newName != null) {
                    MangoController.getInstance().renameList(
                            listLabel, newName);
                    listLabel = newName;
                    TreePath path = new TreePath(
                            ListMutableTreeNode.this.getPath());
                    UIController.getInstance().getNavigatorTree()
                            .getModel().valueForPathChanged(path,
                                    newName);
                }
            }
        });

        JPopupMenu listPopup = new JPopupMenu();
        listPopup.add(removeListItem);
        listPopup.add(renameListItem);
        return listPopup;
    }

}
