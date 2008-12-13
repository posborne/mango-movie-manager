package com.themangoproject.ui.model.navigator;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.ListEditableMovieTableModel;

/**
 * @author Paul Osborne
 */
public class ListMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	private static final long serialVersionUID = -6490024778496666267L;
	private String listLabel;

	public ListMutableTreeNode(String listLabel) {
		super(listLabel);
		this.listLabel = listLabel;
	}

	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(
				new ListEditableMovieTableModel(listLabel));
	}

	@Override
	public JPopupMenu getPopupMenu() {
		JMenuItem removeListItem = new JMenuItem("Delete List");
		removeListItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog((Component)e.getSource(),
						"Are you sure you want to remove the list \"" + listLabel + "\"?",
						"Confirm List Deletion",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					MangoController.getInstance().deleteList(listLabel);
				}
			}
		});
		
		JPopupMenu listPopup = new JPopupMenu();
		listPopup.add(removeListItem);
		return listPopup;
	}

}
