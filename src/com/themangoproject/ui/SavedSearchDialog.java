
package com.themangoproject.ui;

import com.themangoproject.model.AdvancedSearch;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.model.SearchCondition;
import com.themangoproject.ui.model.AttributesComboBoxModel;
import com.themangoproject.ui.model.ConstraintComboBoxModel;
import com.themangoproject.ui.model.SavedSearchEditableTableModel;
import com.themangoproject.ui.model.SearchEditableTableModel;
import com.themangoproject.ui.model.UnsavedSearchEditabledTableModel;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author  Kyle Ronning, Paul Osborne
 */
public class SavedSearchDialog extends javax.swing.JDialog {

    /** Generated serial UID */
	private static final long serialVersionUID = -8915616635728028158L;
	
	/** Creates new form SavedSearchDialog */
    public SavedSearchDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.addSubtractSearchPanel.setComboBoxPrefs(new AttributesComboBoxModel(),
                new ConstraintComboBoxModel(), false, false);
    }

    private AdvancedSearch buildAdvancedSearch() {
        List<AddSubtractInnerPanel> panels = this.addSubtractSearchPanel.getInnerPanelsValues();
        AdvancedSearch as = new AdvancedSearch();
        for (AddSubtractInnerPanel panel : panels) {
            JComboBox leftCB = (JComboBox) panel.getLeftComboObject();
            JComboBox rightCB = (JComboBox) panel.getRightComboObject();
            String attribute = (String) leftCB.getSelectedItem();
            String condtion = (String) rightCB.getSelectedItem();
            String value = (String)panel.getTextFieldText();
            if(attribute == null || condtion == null || value == null){
                throw new IllegalArgumentException("You can't have empty fields!!");
            }

            try {
                int intval = Integer.parseInt(value);
                as.addSearchCondition(new SearchCondition(attribute, condtion, intval));
            } catch (NumberFormatException e) {
                as.addSearchCondition(new SearchCondition(attribute, condtion, value));
            }
        }
        return as;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        searchNameTF = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        searchCriterionPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addSubtractSearchPanel = new com.themangoproject.ui.AddSubtractPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        executeSearchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Saved Search");
        setResizable(false);

        jLabel1.setText("Name");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        searchCriterionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Constraint");

        jLabel2.setText("Attribute");

        jLabel4.setText("Value");

        javax.swing.GroupLayout searchCriterionPanelLayout = new javax.swing.GroupLayout(searchCriterionPanel);
        searchCriterionPanel.setLayout(searchCriterionPanelLayout);
        searchCriterionPanelLayout.setHorizontalGroup(
            searchCriterionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCriterionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchCriterionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchCriterionPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel3)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel4))
                    .addComponent(addSubtractSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        searchCriterionPanelLayout.setVerticalGroup(
            searchCriterionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCriterionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchCriterionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSubtractSearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        executeSearchButton.setText("Execute Search");
        executeSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                        .addComponent(executeSearchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addComponent(jLabel1)
                    .addComponent(searchCriterionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 527, Short.MAX_VALUE)
                    .addComponent(searchNameTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, closeButton, saveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(searchCriterionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(saveButton)
                    .addComponent(cancelButton)
                    .addComponent(executeSearchButton))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelButton, closeButton, saveButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
    try{
        AdvancedSearch as = buildAdvancedSearch();
        String searchLabel = searchNameTF.getText();
        if (!searchLabel.equals("")){
            MangoController.getInstance().saveSearch(searchLabel, as.getSearchQuery());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "You cannot save with no name!",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    } catch (IllegalArgumentException e){
        JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Input", JOptionPane.WARNING_MESSAGE);
    }
    
    //    UIController.getInstance().setViewTableModel(new SavedSearchEditableTableModel(searchLabel));

}//GEN-LAST:event_saveButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    // Cancel button
    this.dispose();
}//GEN-LAST:event_cancelButtonActionPerformed

private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    // Close Button
    // Make sure save has been called
    this.dispose();
}//GEN-LAST:event_closeButtonActionPerformed

private void executeSearchButtonActionPerformed(java.awt.event.ActionEvent evt) { 
    try {
        AdvancedSearch as = buildAdvancedSearch();
        if(as.getSearchQuery() != null)
            UIController.getInstance().setViewTableModel(new UnsavedSearchEditabledTableModel(as.getSearchQuery()));
    } catch (IllegalArgumentException e){
        JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Input", JOptionPane.WARNING_MESSAGE);
    }
    //System.out.println(as.getSearchQuery());

}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SavedSearchDialog dialog = new SavedSearchDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.themangoproject.ui.AddSubtractPanel addSubtractSearchPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton executeSearchButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel searchCriterionPanel;
    private javax.swing.JTextField searchNameTF;
    // End of variables declaration//GEN-END:variables

    /**
     * A meethod to get the information to construct a saved search
     * Change the name of this method
     */
//    public void getSavedSearchInfo() {
//        
//    }
}
