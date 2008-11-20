/*
 * Mango.java
 *
 * Created on October 30, 2008, 1:54 AM
 */

package com.themangoproject.ui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author  osbpau
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
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        editMenu1 = new javax.swing.JMenu();
        viewMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mango Movie Manager");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(655, 500));

        mainSplitPane.setDividerLocation(220);
        mainSplitPane.setDividerSize(3);

        leftSplitPane.setDividerLocation(400);
        leftSplitPane.setDividerSize(3);
        leftSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        leftSplitPane.setTopComponent(navigatorPanel1);

        jScrollPane2.setViewportView(itemInfoPanel1);

        leftSplitPane.setRightComponent(jScrollPane2);

        mainSplitPane.setLeftComponent(leftSplitPane);

        jScrollPane1.setViewportView(viewPanel1);

        mainSplitPane.setRightComponent(jScrollPane1);

        this.bottomBar1.setParent(this);

        fileMenu1.setText("File");

        jMenuItem1.setText("Add Movie");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem1);

        jMenuItem2.setText("Create List");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem2);

        jMenuItem3.setText("Create Set");
        fileMenu1.add(jMenuItem3);
        fileMenu1.add(jSeparator1);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem4);

        mainMenuBar1.add(fileMenu1);

        editMenu1.setText("Edit");
        mainMenuBar1.add(editMenu1);

        viewMenu1.setText("View");
        mainMenuBar1.add(viewMenu1);

        jMenu2.setText("Help");
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
    JFrame addFrame = new MovieAddEditFrame();
    addFrame.setVisible(true);
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

}//GEN-LAST:event_jMenuItem2ActionPerformed

public void toggleInfoPanel() {
    this.infoPaneOn = !this.infoPaneOn;
    if (!this.infoPaneOn) // If we are going to hid the infopane
        // Record the divider location
        this.dividerLoc = this.leftSplitPane.getDividerLocation();
    // Show or hide the infopane
    this.jScrollPane2.setVisible(this.infoPaneOn);
    // Set divider location
    this.leftSplitPane.setDividerLocation(this.dividerLoc);  
}

public void newMovie() {
    new MovieAddEditFrame().setVisible(true);
}

    /**
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
                new Mango().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.themangoproject.ui.BottomBar bottomBar1;
    private javax.swing.JMenu editMenu1;
    private javax.swing.JMenu fileMenu1;
    private com.themangoproject.ui.ItemInfoPanel itemInfoPanel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
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
    private boolean infoPaneOn = true;
    private int dividerLoc;
}
