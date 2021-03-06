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
import com.themangoproject.ui.model.SetEditableMovieTableModel;

/**
 * Node for encapsulating a movie set.
 * 
 * @author Paul Osborne
 */
public class SetMutableTreeNode extends DefaultMutableTreeNode
        implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -7036137034290049259L;
    /** The label for the set */
    private String setLabel;

    /**
     * Construct a set node for the set with the given label.
     * 
     * @param setLabel
     */
    public SetMutableTreeNode(String setLabel) {
        super(setLabel);
        this.setLabel = setLabel;
    }

    /**
     * Make the table display the movies in the set this node
     * represents.
     */
    @Override
    public void doYourThing(Mango mangoPanel) {
        UIController.getInstance().setViewTableModel(
                new SetEditableMovieTableModel(setLabel));
    }

    /**
     * @return a popup menu with actions relating the the set this
     *         node represents
     */
    @Override
    public JPopupMenu getPopupMenu() {
        // Delete Set Item
        JMenuItem removeSetItem = new JMenuItem("Delete Set");
        removeSetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) e
                        .getSource(),
                        "Are you sure you want to remove the set \""
                                + setLabel + "\"?",
                        "Confirm Set Deletion",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    MangoController.getInstance().deleteSet(setLabel);
                }
            }
        });

        // Rename Set Item
        JMenuItem renameSetItem = new JMenuItem("Rename Set");
        renameSetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog(
                        (Component) e.getSource(), "Rename \""
                                + setLabel + "\" to?", "Rename Set",
                        JOptionPane.PLAIN_MESSAGE);
                if (newName != null) {
                    MangoController.getInstance().renameSet(setLabel,
                            newName);
                    setLabel = newName;
                    TreePath path = new TreePath(
                            SetMutableTreeNode.this.getPath());
                    UIController.getInstance().getNavigatorTree()
                            .getModel().valueForPathChanged(path,
                                    newName);
                }
            }
        });

        JPopupMenu setPopup = new JPopupMenu();
        setPopup.add(removeSetItem);
        setPopup.add(renameSetItem);
        return setPopup;
    }

}
