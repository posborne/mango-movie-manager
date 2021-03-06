package com.themangoproject.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * The NavigatorPanel is the tree on the left to view your movie
 * collection.
 * 
 * @author Paul Osborne
 */
public class NavigatorPanel extends javax.swing.JPanel {

    /** Creates new form NavigatorPanel */
    public NavigatorPanel() {
        initComponents();
        DefaultTreeCellRenderer tR = new DefaultTreeCellRenderer();
        tR.setClosedIcon(new ImageIcon(getClass().getResource(
                                "/com/themangoproject/ui/images/closed.png")));
        tR.setOpenIcon(new ImageIcon(getClass().getResource(
                                  "/com/themangoproject/ui/images/open.png")));
//        tR.setLeafIcon(new ImageIcon(getClass().getResource(
//                                  "/com/themangoproject/ui/images/film.png")));
        tR.setLeafIcon(new ImageIcon(getClass().getResource(
                                  "/com/themangoproject/ui/images/leaf.png")));
//        tR.setBackground(Color.green);
//        tR.setFont(Font.getFont("Comic Sans MS"));
//        tR.setFont(new FontUIResource("Comic Sans MS", Font.PLAIN, 12));
//        this.jScrollPane1
        this.navigatorTree.setShowsRootHandles(false);
        this.navigatorTree.setToggleClickCount(1);
        this.navigatorTree.setCellRenderer(tR);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        navigatorTree = new com.themangoproject.ui.NavigatorJTree();

        jScrollPane1.setViewportView(navigatorTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 199,
                        Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 414,
                        Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.themangoproject.ui.NavigatorJTree navigatorTree;

    // End of variables declaration//GEN-END:variables

    /**
     * Gets the navigator tree.
     * 
     * @return the navigator tree.
     */
    public JTree getNavigatorTree() {
        return this.navigatorTree;
    }

}
