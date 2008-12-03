/*
 * Mango.java
 *
 * Created on October 30, 2008, 1:54 AM
 */

package com.themangoproject.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Mango is the main class for the Mango Movie Manager program.
 * 
 * @author  Kyle Ronning, Paul Osborne
 * @version 1.0
 */
public class Mango extends javax.swing.JFrame {

    /** Creates new form Mango */
    public Mango() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPane = new javax.swing.JSplitPane();
        leftSplitPane = new javax.swing.JSplitPane();
        navigatorPanel1 = new com.themangoproject.ui.NavigatorPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemInfoPanel1 = new com.themangoproject.ui.ItemInfoPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewPanel1 = new com.themangoproject.ui.ViewPanel();
        bottomBar1 = new com.themangoproject.ui.BottomBar();
        mainMenuBar1 = new javax.swing.JMenuBar();
        fileMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        viewMenu1 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mango Movie Manager");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(655, 500));

        mainSplitPane.setDividerLocation(220);
        mainSplitPane.setDividerSize(3);

        leftSplitPane.setDividerLocation(400);
        leftSplitPane.setDividerSize(3);
        leftSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        leftSplitPane.setResizeWeight(1.0);
        leftSplitPane.setTopComponent(navigatorPanel1);

        jScrollPane2.setViewportView(itemInfoPanel1);

        leftSplitPane.setRightComponent(jScrollPane2);

        mainSplitPane.setLeftComponent(leftSplitPane);

        jScrollPane1.setViewportView(viewPanel1);

        mainSplitPane.setRightComponent(jScrollPane1);

        fileMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/movieadd.png"))); // NOI18N
        jMenuItem1.setText("Add Movie");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/movieedit.png"))); // NOI18N
        jMenuItem3.setText("Edit Selected Movie");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem3);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/setlistadd.png"))); // NOI18N
        jMenuItem2.setText("Create Set/List");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem2);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/advancedsearch.png"))); // NOI18N
        jMenuItem7.setText("Create Saved Search");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem7);
        fileMenu1.add(jSeparator1);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/door_in.png"))); // NOI18N
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem4);

        mainMenuBar1.add(fileMenu1);

        viewMenu1.setText("View");

        jMenuItem8.setText("All Movies");
        viewMenu1.add(jMenuItem8);

        jMenuItem9.setText("My Movies");
        viewMenu1.add(jMenuItem9);

        jMenuItem10.setText("Friend's Movie");
        viewMenu1.add(jMenuItem10);

        jMenuItem11.setText("Borrowed Movies");
        viewMenu1.add(jMenuItem11);

        jMenuItem12.setText("Loaned Movies");
        viewMenu1.add(jMenuItem12);

        jMenuItem13.setText("Show People");
        viewMenu1.add(jMenuItem13);

        jMenuItem14.setText("Wish List");
        viewMenu1.add(jMenuItem14);

        mainMenuBar1.add(viewMenu1);

        jMenu2.setText("Help");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem5.setText("About Mango");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/help.png"))); // NOI18N
        jMenuItem6.setText("Help");
        jMenu2.add(jMenuItem6);

        mainMenuBar1.add(jMenu2);

        setJMenuBar(mainMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .addComponent(bottomBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    this.dispose(); // exit the application
}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    // Add a new movie
    MovieAddEditDialog maed = new MovieAddEditDialog(this, true);
    maed.setLocationRelativeTo(this);
    maed.setVisible(true);
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    // Show dialog to create a new set or list
    SetListDialog sld = new SetListDialog(this, true);
    sld.setLocationRelativeTo(this);
    sld.setVisible(true);
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    // Add Saved Search
    SavedSearchDialog ssd = new SavedSearchDialog(this, true);
    ssd.setLocationRelativeTo(this);
    ssd.setVisible(true);
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    // Edit Selected Movie
    MovieAddEditDialog maed = new MovieAddEditDialog(this, true);
    maed.setLocationRelativeTo(this);
    maed.setVisible(true);
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    AboutMango am = new AboutMango(this, true);
    am.setLocationRelativeTo(this);
    am.setVisible(true);
}//GEN-LAST:event_jMenuItem5ActionPerformed

/**
 * Toggle the InfoPanel from hidden to showing and vice versa.  Called when a 
 * button is pressed.
 */
public void toggleInfoPanel() {
    this.infoPaneOn = !this.infoPaneOn;
    if (!this.infoPaneOn) {// If we are going to hide the infopane
        // Record the divider location
        this.dividerLoc = this.leftSplitPane.getDividerLocation();
        this.leftSplitPane.setDividerSize(0);
        this.bottomBar1.setInfoIcon(0);
    } else {
        this.leftSplitPane.setDividerSize(3);
        this.bottomBar1.setInfoIcon(1);  // make better later
    }
    // Set divider location
    this.leftSplitPane.setDividerLocation(this.dividerLoc);  
    // Show or hide the infopane
    this.jScrollPane2.setVisible(this.infoPaneOn);
}

    /**
     * Launches the main program.  Sets the look and feel tot he system look
     * and feel.
     * 
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        }
        // Just use the default ugly Look and Feel then
        catch (UnsupportedLookAndFeelException e) {
        // handle exception
        }
        catch (ClassNotFoundException e) {
        // handle exception
        }
        catch (InstantiationException e) {
        // handle exception
        }
        catch (IllegalAccessException e) {
        // handle exception
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mango m = new Mango();
                m.setIconImage(new javax.swing.ImageIcon(getClass().
                        getResource("/com/themangoproject/ui/images/mangoicon.png")).getImage());
                m.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.themangoproject.ui.BottomBar bottomBar1;
    private javax.swing.JMenu fileMenu1;
    private com.themangoproject.ui.ItemInfoPanel itemInfoPanel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane leftSplitPane;
    private javax.swing.JMenuBar mainMenuBar1;
    private javax.swing.JSplitPane mainSplitPane;
    private com.themangoproject.ui.NavigatorPanel navigatorPanel1;
    private javax.swing.JMenu viewMenu1;
    private com.themangoproject.ui.ViewPanel viewPanel1;
    // End of variables declaration//GEN-END:variables

    // My field variables
    private boolean infoPaneOn = true;
    private int dividerLoc;
    
}
