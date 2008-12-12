package com.themangoproject.ui;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.ui.model.EditableMovieTableModel;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.JFileChooser;

/*
 * BottomBar.java
 *
 * Created on November 8, 2008, 3:45 PM
 */



/**
 * The BottomBar is the panel along the bottom of the program that has action
 * buttons and a status label in the middle of the panel.
 * 
 * @author  Kyle Ronning, Paul Osborne 
 * @version 1.0
 */
public class BottomBar extends javax.swing.JPanel {

    /** Creates new form BottomBar */
    public BottomBar() {
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

        statusLabel = new javax.swing.JLabel();
        addMovieButton = new javax.swing.JButton();
        editMovieButton = new javax.swing.JButton();
        addListSetButton = new javax.swing.JButton();
        createSavedSearchButton = new javax.swing.JButton();
        showHideButton = new javax.swing.JButton();
        associateImageButton = new javax.swing.JButton();

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("# Movies / # Actors");

        addMovieButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/movieadd.png"))); // NOI18N
        addMovieButton.setToolTipText("Add New Movie");
        addMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMovieButtonActionPerformed(evt);
            }
        });

        editMovieButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/movieedit.png"))); // NOI18N
        editMovieButton.setToolTipText("Edit Selected Movie");
        editMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMovieButtonActionPerformed(evt);
            }
        });

        addListSetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/setlistadd.png"))); // NOI18N
        addListSetButton.setToolTipText("Create Set/List");
        addListSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addListSetButtonActionPerformed(evt);
            }
        });

        createSavedSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/advancedsearch.png"))); // NOI18N
        createSavedSearchButton.setToolTipText("Create Saved Search");
        createSavedSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSavedSearchButtonActionPerformed(evt);
            }
        });

        showHideButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/arrow_down.png"))); // NOI18N
        showHideButton.setToolTipText("Show/Hide Info Panel");
        showHideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHideButtonActionPerformed(evt);
            }
        });

        associateImageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/themangoproject/ui/images/picture_add.png"))); // NOI18N
        associateImageButton.setToolTipText("Upload Thumbnail Image");
        associateImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                associateImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addMovieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editMovieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addListSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createSavedSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showHideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(associateImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addListSetButton, addMovieButton, associateImageButton, createSavedSearchButton, editMovieButton, showHideButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addMovieButton)
            .addComponent(editMovieButton)
            .addComponent(addListSetButton)
            .addComponent(createSavedSearchButton)
            .addComponent(showHideButton)
            .addComponent(associateImageButton)
            .addComponent(statusLabel)
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addListSetButton, addMovieButton, associateImageButton, createSavedSearchButton, editMovieButton, showHideButton, statusLabel});

    }// </editor-fold>//GEN-END:initComponents

private void addMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMovieButtonActionPerformed
    // Create a new movie dialog
    MovieAddEditDialog maed = 
            new MovieAddEditDialog((Mango)this.getTopLevelAncestor(), true);
    maed.setLocationRelativeTo((Mango)this.getTopLevelAncestor());
    maed.setVisible(true);
}//GEN-LAST:event_addMovieButtonActionPerformed

private void editMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMovieButtonActionPerformed
    MovieAddEditDialog maed = 
            new MovieAddEditDialog((Mango)this.getTopLevelAncestor(), true);
    maed.setLocationRelativeTo((Mango)this.getTopLevelAncestor());
    
    JTable table = UIController.getInstance().getViewTable();
    int selRow = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
    Movie m = ((EditableMovieTableModel)table.getModel()).getMovieFromRow(selRow);
    maed.populateData(m);
    // Add code to gather information about a movie given the cell that is
    // selected in the table
    
    maed.setVisible(true);
}//GEN-LAST:event_editMovieButtonActionPerformed

private void addListSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addListSetButtonActionPerformed
    // Show dialog to create a new set or list
    SetListDialog sld =
            new SetListDialog((Mango)this.getTopLevelAncestor(), true);
    sld.setLocationRelativeTo((Mango)this.getTopLevelAncestor());
    sld.setVisible(true);
}//GEN-LAST:event_addListSetButtonActionPerformed

private void createSavedSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSavedSearchButtonActionPerformed
    // Show dialog to create a saved search
    SavedSearchDialog ssd = 
            new SavedSearchDialog((Mango)this.getTopLevelAncestor(), true);
    ssd.setLocationRelativeTo((Mango)this.getTopLevelAncestor());
    ssd.setVisible(true);
}//GEN-LAST:event_createSavedSearchButtonActionPerformed

private void showHideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showHideButtonActionPerformed
    // Hide or show the InfoPanel
    ((Mango)this.getTopLevelAncestor()).toggleInfoPanel();
}//GEN-LAST:event_showHideButtonActionPerformed

private void associateImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_associateImageButtonActionPerformed
    // Create a new upload thumbnail dialog
    //TODO: add to correct movie
    JFileChooser jfc = new JFileChooser();
    jfc.addChoosableFileFilter(new ImageFilter());
    jfc.setAcceptAllFileFilterUsed(false);
    int i = jfc.showOpenDialog(this);
    File file = null;
    
    if(i == JFileChooser.APPROVE_OPTION){
        Image image = null;
        InputStream is = null;
        try {
            
            file = jfc.getSelectedFile();
            
            image = ImageIO.read(file);
            if(image != null){
               image = image.getScaledInstance(160, 160, Image.SCALE_DEFAULT);
               
               BufferedImage im = Pictures.toBufferedImage(image);
               file = new File("temp.png");
               ImageIO.write(im, "png", file);
               
                is = new FileInputStream(file);
            } else {
                JOptionPane.showMessageDialog(associateImageButton, "Error uploading picture:\nIncorrect file type.");
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(associateImageButton, "Error uploading picture");
            Logger.getLogger(BottomBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(associateImageButton, "Error uploading picture");
            Logger.getLogger(BottomBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        JTable table = UIController.getInstance().getViewTable();
        int selRow = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
        Movie m = ((EditableMovieTableModel)UIController.getInstance().getViewTable().getModel()).getMovieFromRow(selRow);
        
        MangoController.getInstance().setImageForMovie(m,is);  
        //TODO:  Not the best way to do this, but it works.
        UIController.getInstance().getViewTable().clearSelection();
        UIController.getInstance().getViewTable().setRowSelectionInterval(selRow, selRow);
    }
    /*
     * This is test code to make sure the image is being uploaded correctly.
     * 
    JDialog dia = new JDialog();
    JLabel lab = new JLabel();
    lab.setIcon(new ImageIcon(image));
    dia.add(lab);
    dia.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dia.pack();
    dia.setVisible(true);
    */
    
//    if (i == JFileChooser.APPROVE_OPTION)
//        System.out.println("OK");
//    else
//        System.out.println("Cancel");
    // Add code to add thumbnail image to this movie
}//GEN-LAST:event_associateImageButtonActionPerformed




/**
 * Sets the icon to the appropriate arrow.  Up arrow if the InfoPanel is hidden.
 * Down arrow if the InfoPanel is showing.
 * 
 * @param i The icon to display.
 */
public void setInfoIcon(int i) {
    if (i == 0)
        this.showHideButton.setIcon(new javax.swing.ImageIcon(getClass().
                getResource("/com/themangoproject/ui/images/arrow_up.png")));
    else if (i == 1)
        this.showHideButton.setIcon(new javax.swing.ImageIcon(getClass().
                getResource("/com/themangoproject/ui/images/arrow_down.png")));
}

/**
 * Sets the label to show current status information. I.e. # of movies.
 * 
 * @param label The text to show in the label for status information.
 */
public void setLabelInfo(String label) {
    this.statusLabel.setText(label);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addListSetButton;
    private javax.swing.JButton addMovieButton;
    private javax.swing.JButton associateImageButton;
    private javax.swing.JButton createSavedSearchButton;
    private javax.swing.JButton editMovieButton;
    private javax.swing.JButton showHideButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
