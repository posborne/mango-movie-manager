package com.themangoproject.ui;

import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableModel;

/**
 * A controller class for various parts of the UI that are accessed
 * and need a convienent way to access them.
 * 
 * @author Paul Osborne, Kyle Ronning
 */
public class UIController {
    // Instance
    private static UIController instance;
    // Mango unique instance
    private Mango mango;

    // Private construtor
    private UIController() {
        mango = Mango.getCreatedInstance();
    }

    /**
     * Gets the instance of the controller.
     * 
     * @return The instance of the controller.
     */
    public static UIController getInstance() {
        if (instance == null) {
            instance = new UIController();
        }
        return instance;
    }

    /**
     * Gets the unique instance of the Mango frame.
     * 
     * @return Mango frame.
     */
    public Frame getMango() {
        return mango;
    }

    /**
     * Gets the view table model.
     * 
     * @param tm
     *            The view table model.
     */
    public void setViewTableModel(TableModel tm) {
        mango.setTableModel(tm);
    }

    /**
     * Gets the view table.
     * 
     * @return The view table.
     */
    public JTable getViewTable() {
        return mango.getTable();
    }

    /**
     * Gets the navigator tree.
     * 
     * @return the navigator tree.
     */
    public JTree getNavigatorTree() {
        if (mango != null) {
            return mango.getNavigatorTree();
        } else {
            return null;
        }
    }

    /**
     * Gets the table item count.
     * 
     * @return The number of items in the table.
     */
    public int getTableItemCount() {
        return mango.getTableItemCount();
    }

    /**
     * Gets the owner id
     * 
     * @return The owner id
     */
    public int getOwnerId() {
        Properties p = null;
        int id = -1;
        try {
            p = new Properties();
            p.load(new FileInputStream("mango.properties"));
            id = Integer.parseInt(p.getProperty("MangoOwner"));
        } catch (Exception e) {
        }
        return id;
    }

    /**
     * Sets the owner id
     * 
     * @param id
     *            The owner id
     */
    public void setOwnerId(int id) {
        Properties p = null;
        try {
            p = new Properties();
            p.setProperty("MangoOwner", "" + id);
            FileOutputStream out = new FileOutputStream(
                    "mango.properties");
            p.store(out, "");
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Value: ");
        int id = Integer.parseInt(scan.nextLine());
        UIController uic = new UIController();
        uic.setOwnerId(id);
        System.out.println(uic.getOwnerId());

    }
}
