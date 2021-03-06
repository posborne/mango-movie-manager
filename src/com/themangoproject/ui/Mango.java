package com.themangoproject.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import com.themangoproject.db.h2.H2Util;
import com.themangoproject.db.h2.TestUtility;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.model.MovieDeleteConflict;
import com.themangoproject.ui.model.AllMoviesEditableTableModel;
import com.themangoproject.ui.model.EditableMovieTableModel;
import com.themangoproject.ui.model.MangoTableModelIF;
import com.themangoproject.ui.moviepopup.MoviePopupMenu;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Mango is the main class for the Mango Movie Manager program.
 * 
 * @author Kyle Ronning, Paul Osborne, Zach Varberg
 * @version 1.0
 */
public class Mango extends javax.swing.JFrame {

    private static final long serialVersionUID = 3994487592258060215L;

    /** Creates new form Mango */
    public Mango() {
        H2Util.getInstance().setDatabaseLocation(
                "./db/mango.db;IFEXISTS=TRUE");
        Connection conn = H2Util.getInstance().getConnection();
        if (conn == null) {
            // create the database and initialize schema
            H2Util.getInstance().setDatabaseLocation("./db/mango.db");
            H2Util.getInstance().initializeSchemaOnDb();
//            UIController.getInstance().setOwnerId(-1);
        }
        initComponents();
        this.helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mango.this.helpActionPerformed();
            }
        });
        createdInstance = this;
        this.getTable().getSelectionModel().addListSelectionListener(
                itemInfoPanel);
        this.getTable().addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            public void mouseClicked(MouseEvent e) {
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable table = ((JTable) e.getComponent());
                    int selectedRow = table.rowAtPoint(new Point(e
                            .getX(), e.getY()));
//                    table.setRowSelectionInterval(selectedRow,
//                            selectedRow);
                    JPopupMenu popup = ((MangoTableModelIF) table
                            .getModel()).getPopupMenu();
                    if (popup != null) {
                        popup.show(e.getComponent(), e.getX(), e
                                .getY());
                    }
                }
            }
        });
        // UIController.getInstance().getViewTable().getRowSorter().
        // addRowSorterListener(new RowSorterListener(){
        // public void sorterChanged(RowSorterEvent e) {
        // Mango.this.bottomBar.setLabelInfo(UIController.getInstance().
        // getViewTable().getRowSorter().getViewRowCount() +
        // " items");
        // }
        //            
        // });
        viewMenu.setVisible(false);
    }

    // Created instance
    private static Mango createdInstance;

    /**
     * Gets the created instance of mango
     * 
     * @return mango
     */
    public static Mango getCreatedInstance() {
        return createdInstance;
    }

    /**
     * Sets the table model.
     * 
     * @param tm
     *            The table model
     */
    public void setTableModel(TableModel tm) {
        ((ViewPanel) viewPanel).setTableModel(tm);
        // bottomBar.setLabelInfo(UIController.getInstance().getTableItemCount()
        // + " items");
    }

    /**
     * Gets the table.
     * 
     * @return The table
     */
    public JTable getTable() {
        return ((ViewPanel) viewPanel).getTable();
    }

    /**
     * Gets the number of items in the table.
     * 
     * @return The number of items in the table.
     */
    public int getTableItemCount() {
        return ((ViewPanel) viewPanel).getTable().getRowCount();
    }

    /**
     * Displays a dialog when the help menu item is selected
     */
    private void helpActionPerformed() {
        JOptionPane
                .showMessageDialog(
                        this,
                        "Please e mail any questions or "
                                + "comments to developers@themangoproject.com",
                        "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize
     * the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPane = new javax.swing.JSplitPane();
        leftSplitPane = new javax.swing.JSplitPane();
        itemInfoScrollPane = new javax.swing.JScrollPane();
        itemInfoPanel = new com.themangoproject.ui.ItemInfoPanel();
        navigatorPanel = new com.themangoproject.ui.NavigatorPanel();
        viewPanelScrollPane = new javax.swing.JScrollPane();
        viewPanel = new com.themangoproject.ui.ViewPanel();
        bottomBar = new com.themangoproject.ui.BottomBar();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        addMovieMenuItem = new javax.swing.JMenuItem();
        editSelectedMovieMenuItem = new javax.swing.JMenuItem();
        createSetListMenuItem = new javax.swing.JMenuItem();
        createSavedSearchMenuItem = new javax.swing.JMenuItem();
        fileMenuSeperator = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mango Movie Manager");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(655, 500));

        mainSplitPane.setDividerLocation(220);
        mainSplitPane.setDividerSize(3);

        leftSplitPane.setDividerLocation(400);
        leftSplitPane.setDividerSize(3);
        leftSplitPane
                .setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        leftSplitPane.setResizeWeight(1.0);

        itemInfoScrollPane.setViewportView(itemInfoPanel);

        leftSplitPane.setRightComponent(itemInfoScrollPane);
        leftSplitPane.setLeftComponent(navigatorPanel);

        mainSplitPane.setLeftComponent(leftSplitPane);

        viewPanelScrollPane.setViewportView(viewPanel);

        mainSplitPane.setRightComponent(viewPanelScrollPane);

        fileMenu.setText("File");

        addMovieMenuItem.setAccelerator(javax.swing.KeyStroke
                .getKeyStroke(java.awt.event.KeyEvent.VK_M,
                        java.awt.event.InputEvent.CTRL_MASK));
        addMovieMenuItem
                .setIcon(new javax.swing.ImageIcon(
                        getClass()
                                .getResource(
                                        "/com/themangoproject/ui/images/movieadd.png"))); // NOI18N
        addMovieMenuItem.setText("Add Movie");
        addMovieMenuItem
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        addMovieMenuItemActionPerformed(evt);
                    }
                });
        fileMenu.add(addMovieMenuItem);

        editSelectedMovieMenuItem
                .setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                        java.awt.event.KeyEvent.VK_E,
                        java.awt.event.InputEvent.CTRL_MASK));
        editSelectedMovieMenuItem
                .setIcon(new javax.swing.ImageIcon(
                        getClass()
                                .getResource(
                                        "/com/themangoproject/ui/images/movieedit.png"))); // NOI18N
        editSelectedMovieMenuItem.setText("Edit Selected Movie");
        editSelectedMovieMenuItem
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        editSelectedMovieMenuItemActionPerformed(evt);
                    }
                });
        fileMenu.add(editSelectedMovieMenuItem);

        createSetListMenuItem.setAccelerator(javax.swing.KeyStroke
                .getKeyStroke(java.awt.event.KeyEvent.VK_L,
                        java.awt.event.InputEvent.CTRL_MASK));
        createSetListMenuItem
                .setIcon(new javax.swing.ImageIcon(
                        getClass()
                                .getResource(
                                        "/com/themangoproject/ui/images/setlistadd.png"))); // NOI18N
        createSetListMenuItem.setText("Create Set/List");
        createSetListMenuItem
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        createSetListMenuItemActionPerformed(evt);
                    }
                });
        fileMenu.add(createSetListMenuItem);

        createSavedSearchMenuItem
                .setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                        java.awt.event.KeyEvent.VK_S,
                        java.awt.event.InputEvent.ALT_MASK));
        createSavedSearchMenuItem
                .setIcon(new javax.swing.ImageIcon(
                        getClass()
                                .getResource(
                                        "/com/themangoproject/ui/images/advancedsearch.png"))); // NOI18N
        createSavedSearchMenuItem.setText("Create Saved Search");
        createSavedSearchMenuItem
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        createSavedSearchMenuItemActionPerformed(evt);
                    }
                });
        fileMenu.add(createSavedSearchMenuItem);
        fileMenu.add(fileMenuSeperator);

        exitMenuItem
                .setIcon(new javax.swing.ImageIcon(
                        getClass()
                                .getResource(
                                        "/com/themangoproject/ui/images/door_in.png"))); // NOI18N
        exitMenuItem.setText("Exit");
        exitMenuItem
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        exitMenuItemActionPerformed(evt);
                    }
                });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        viewMenu.setText("View");

        jMenuItem8.setText("All Movies");
        viewMenu.add(jMenuItem8);

        jMenuItem9.setText("My Movies");
        viewMenu.add(jMenuItem9);

        jMenuItem10.setText("Friend's Movie");
        viewMenu.add(jMenuItem10);

        jMenuItem11.setText("Borrowed Movies");
        viewMenu.add(jMenuItem11);

        jMenuItem12.setText("Loaned Movies");
        viewMenu.add(jMenuItem12);

        jMenuItem13.setText("Show People");
        viewMenu.add(jMenuItem13);

        jMenuItem14.setText("Wish List");
        viewMenu.add(jMenuItem14);

        mainMenuBar.add(viewMenu);

        helpMenu.setText("Help");

        aboutMenuItem
                .setAccelerator(javax.swing.KeyStroke
                        .getKeyStroke(
                                java.awt.event.KeyEvent.VK_A,
                                java.awt.event.InputEvent.ALT_MASK
                                        | java.awt.event.InputEvent.SHIFT_MASK));
        aboutMenuItem.setText("About Mango");
        aboutMenuItem
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        aboutMenuItemActionPerformed(evt);
                    }
                });
        helpMenu.add(aboutMenuItem);

        helpMenuItem.setAccelerator(javax.swing.KeyStroke
                .getKeyStroke(java.awt.event.KeyEvent.VK_H,
                        java.awt.event.InputEvent.CTRL_MASK));
        helpMenuItem.setIcon(new javax.swing.ImageIcon(getClass()
                .getResource(
                        "/com/themangoproject/ui/images/help.png"))); // NOI18N
        helpMenuItem.setText("Help");
        helpMenu.add(helpMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainSplitPane,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 750,
                        Short.MAX_VALUE).addComponent(bottomBar,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 750,
                        Short.MAX_VALUE));
        layout
                .setVerticalGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addComponent(
                                                mainSplitPane,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                600, Short.MAX_VALUE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                bottomBar,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitMenuItemActionPerformed
        this.dispose(); // exit the application
    }// GEN-LAST:event_exitMenuItemActionPerformed

    private void addMovieMenuItemActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addMovieMenuItemActionPerformed
        // Add a new movie
        MovieAddEditDialog maed = new MovieAddEditDialog(this, true);
        maed.setLocationRelativeTo(this);
        maed.setVisible(true);
    }// GEN-LAST:event_addMovieMenuItemActionPerformed

    private void createSetListMenuItemActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createSetListMenuItemActionPerformed
        // Show dialog to create a new set or list
        SetListDialog sld = new SetListDialog(this, true);
        sld.setLocationRelativeTo(this);
        sld.setVisible(true);
    }// GEN-LAST:event_createSetListMenuItemActionPerformed

    private void createSavedSearchMenuItemActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createSavedSearchMenuItemActionPerformed
        // Add Saved Search
        SavedSearchDialog ssd = new SavedSearchDialog(this, true);
        ssd.setLocationRelativeTo(this);
        ssd.setVisible(true);
    }// GEN-LAST:event_createSavedSearchMenuItemActionPerformed

    private void editSelectedMovieMenuItemActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_editSelectedMovieMenuItemActionPerformed
        // Edit Selected Movie
        MovieAddEditDialog maed = new MovieAddEditDialog(this, true);
        maed.setLocationRelativeTo(this);
        maed.setVisible(true);
    }// GEN-LAST:event_editSelectedMovieMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_aboutMenuItemActionPerformed
        AboutMango am = new AboutMango(this, true);
        am.setLocationRelativeTo(this);
        am.setVisible(true);
    }// GEN-LAST:event_aboutMenuItemActionPerformed

    /**
     * Toggle the InfoPanel from hidden to showing and vice versa.
     * Called when a button is pressed.
     */
    public void toggleInfoPanel() {
        this.infoPaneOn = !this.infoPaneOn;
        if (!this.infoPaneOn) {// If we are going to hide the infopane
            // Record the divider location
            this.dividerLoc = this.leftSplitPane.getDividerLocation();
            this.leftSplitPane.setDividerSize(0);
            this.bottomBar.setInfoIcon(0);
        } else {
            this.leftSplitPane.setDividerSize(3);
            this.bottomBar.setInfoIcon(1); // make better later
        }
        // Set divider location
        this.leftSplitPane.setDividerLocation(this.dividerLoc);
        // Show or hide the infopane
        this.itemInfoScrollPane.setVisible(this.infoPaneOn);
    }

    /**
     * Launches the main program. Sets the look and feel to the system
     * look and feel.
     * 
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
        }
        // Just use the default ugly Look and Feel then
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mango m = new Mango();
                m
                        .setIconImage(new javax.swing.ImageIcon(
                                getClass()
                                        .getResource(
                                                "/com/themangoproject/ui/images/mangoicon.png"))
                                .getImage());
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addMovieMenuItem;
    private com.themangoproject.ui.BottomBar bottomBar;
    private javax.swing.JMenuItem createSavedSearchMenuItem;
    private javax.swing.JMenuItem createSetListMenuItem;
    private javax.swing.JMenuItem editSelectedMovieMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSeparator fileMenuSeperator;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private com.themangoproject.ui.ItemInfoPanel itemInfoPanel;
    private javax.swing.JScrollPane itemInfoScrollPane;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JSplitPane leftSplitPane;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JSplitPane mainSplitPane;
    private com.themangoproject.ui.NavigatorPanel navigatorPanel;
    private javax.swing.JMenu viewMenu;
    private com.themangoproject.ui.ViewPanel viewPanel;
    private javax.swing.JScrollPane viewPanelScrollPane;
    // End of variables declaration//GEN-END:variables

    // My field variables
    private boolean infoPaneOn = true;
    private int dividerLoc;

    /**
     * Gets the navigator tree
     * 
     * @return The navigator tree
     */
    public JTree getNavigatorTree() {
        return this.navigatorPanel.getNavigatorTree();
    }

}
