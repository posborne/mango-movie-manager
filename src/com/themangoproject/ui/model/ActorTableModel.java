package com.themangoproject.ui.model;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.themangoproject.db.h2.ActorExistsInOtherRelationsException;
import com.themangoproject.model.Actor;
import com.themangoproject.model.MangoController;
import com.themangoproject.ui.UIController;

/**
 * Table model encapsulating a view of the Actors contained in the
 * Mango DB backend.
 * 
 * @author Zach Varberg, Paul Osborne
 */
public class ActorTableModel extends AbstractTableModel implements
        MangoTableModelIF {

    /** Generated serial version UID */
    private static final long serialVersionUID = -392463901990643277L;
    private final String columns[] = { "#", "First Name", "Last Name" };

    /** List of actors in the storage backend */
    private List<Actor> actors;

    public ActorTableModel() {
        actors = MangoController.getInstance().getAllActors();
    }

    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return this.actors.size();
    }

    /**
     * Get the value of the object at the specified row and column
     * position in the table model.
     * 
     * @param rowIndex
     *            the row index
     * @param columnIndex
     *            the column index
     * @return The value of the Object corresponding to the specified
     *         row and column index.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Actor a = actors.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return rowIndex + 1;
        case 1:
            return a.getFirstName();
        case 2:
            return a.getLastName();
        default:
            return "???";
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !(columnIndex == 1);
    }

    public Class<? extends Object> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return Integer.class;
        case 1:
            return String.class;
        case 2:
            return String.class;
        default:
            return String.class;
        }
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Actor a = actors.get(rowIndex);
        switch (columnIndex) {
        case 1:
            a.setFirstName((String) value);
            MangoController.getInstance().updateActor(a);
            break;
        case 2:
            a.setLastName((String) value);
            MangoController.getInstance().updateActor(a);
            break;
        default:
            break; // Can't be edited
        }
    }

    @Override
    public void cleanup() {
        // TODO: cleanup
    }

    @Override
    public JPopupMenu getPopupMenu() {
        JMenuItem deleteActorItem = new JMenuItem("Delete Actor");
        deleteActorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = UIController.getInstance()
                        .getViewTable();
                int rowIndex = table.getSelectedRow();
                int modelRow = table.getRowSorter()
                        .convertRowIndexToModel(rowIndex);
                Actor a = actors.get(modelRow);
                try {
                    MangoController.getInstance().deleteActor(a);
                } catch (ActorExistsInOtherRelationsException ex) {
                    if (JOptionPane.YES_OPTION == JOptionPane
                            .showConfirmDialog(
                                    UIController.getInstance()
                                            .getMango(),
                                    "This actor exists in several relations.\nAre you sure you want to delete?",
                                    "Confirm Delete",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE)) {
                        MangoController.getInstance()
                                .forceDeleteActor(a);
                    }
                }
                // TODO: remove this if we get events up and running
                // (this is bad)
                ActorTableModel.this.actors = MangoController
                        .getInstance().getAllActors();
                ActorTableModel.this.fireTableStructureChanged();
            }
        });
        JPopupMenu menu = new JPopupMenu();
        menu.add(deleteActorItem);
        return menu;
    }

    @Override
    public Image getImageForRow(int modelRow) {
        return null; // no images for actors
    }

}
