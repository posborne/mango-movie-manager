package com.themangoproject.ui;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.SetListAlreadyExistsException;
import javax.swing.JOptionPane;

/**
 * SetListDialog is a dialog to create a set or list.\
 * 
 * @author Kyle Ronning
 */
public class SetListDialog extends javax.swing.JDialog {

    /** Creates new form SetListDialog */
    public SetListDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        listSetNameTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        listRadioButton = new javax.swing.JRadioButton();
        setRadioButton = new javax.swing.JRadioButton();
        cancelButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Set/List");
        setResizable(false);

        jLabel1.setText("Name");

        jLabel2.setText("Type");

        buttonGroup1.add(listRadioButton);
        listRadioButton.setSelected(true);
        listRadioButton.setText("List");

        buttonGroup1.add(setRadioButton);
        setRadioButton.setText("Set");

        cancelButton.setText("Cancel");
        cancelButton
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        cancelButtonActionPerformed(evt);
                    }
                });

        createButton.setText("Create");
        createButton
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            java.awt.event.ActionEvent evt) {
                        createButtonActionPerformed(evt);
                    }
                });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout
                .setHorizontalGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addComponent(
                                                                                listRadioButton)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(
                                                                                setRadioButton))
                                                        .addComponent(
                                                                listSetNameTF,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                352,
                                                                Short.MAX_VALUE)
                                                        .addComponent(
                                                                jLabel1)
                                                        .addComponent(
                                                                jLabel2)
                                                        .addGroup(
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addComponent(
                                                                                createButton)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(
                                                                                cancelButton)))
                                        .addContainerGap()));
        layout
                .setVerticalGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                listSetNameTF,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(
                                                                listRadioButton)
                                                        .addComponent(
                                                                setRadioButton))
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(
                                                                cancelButton)
                                                        .addComponent(
                                                                createButton))
                                        .addContainerGap(
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
        // Cancel Button
        this.dispose();
    }// GEN-LAST:event_cancelButtonActionPerformed

    private void createButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createButtonActionPerformed
        // Create a new set or list
        String name = this.listSetNameTF.getText();
        if (!name.equals("")) {
            try {
                if (this.listRadioButton.isSelected()) { // List
                    System.out.println("List");
                    MangoController.getInstance().addList(
                            name);
                } else if (this.setRadioButton.isSelected()) { // Set
                    System.out.println("Set");
                    MangoController.getInstance().addSet(
                            name);
                }
                this.dispose();
            } catch (SetListAlreadyExistsException e) {
                JOptionPane.showMessageDialog(this, "A Set/List with that name" +
                        "already exists", "Uh-Oh", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must give it a name!",
                    "Uh-Oh", JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_createButtonActionPerformed

    /**
     * Sets the set radio button selected.
     */
    public void setSetSelected() {
        this.setRadioButton.setSelected(true);
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SetListDialog dialog = new SetListDialog(
                        new javax.swing.JFrame(), true);
                dialog
                        .addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosing(
                                    java.awt.event.WindowEvent e) {
                                System.exit(0);
                            }
                        });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton listRadioButton;
    private javax.swing.JTextField listSetNameTF;
    private javax.swing.JRadioButton setRadioButton;
    // End of variables declaration//GEN-END:variables

}
