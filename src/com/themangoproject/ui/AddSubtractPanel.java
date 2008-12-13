
package com.themangoproject.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ComboBoxModel;

/**
 * AddSubstractPanel is a panel to collect AddSubtractInnerPanels.
 * 
 * @author  Kyle Ronning
 * @version 12-6-2008
 */
public class AddSubtractPanel extends javax.swing.JPanel {

    /** Creates new form AddSubstractDaddyPanel */
    public AddSubtractPanel() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    /** ComboBoxModels */
    private ComboBoxModel cbm1;
    private ComboBoxModel cbm2;
    /** Editable ComboBoxes */
    private boolean cbEditable1;
    private boolean cbEditable2;
    
    /**
     * Adds an AddSubtractInnerPanel to this panel.
     */
    public AddSubtractInnerPanel addAddSubPanel() {
        AddSubtractInnerPanel asip;
        try {
            asip = new AddSubtractInnerPanel(
                this.cbm1.getClass().newInstance(), 
                this.cbm2.getClass().newInstance());
            asip.setComboBox1Editable(this.cbEditable1);
            asip.setComboBox2Editable(this.cbEditable2);
            this.jPanel1.add(asip, this.jPanel1.getComponentCount() - 1);                        
        } catch (InstantiationException ex) {
            return null;
        } catch (IllegalAccessException ex) {
            return null;
        }

        if (this.jPanel1.getComponentCount() == 3)
            ((AddSubtractInnerPanel)this.jPanel1.getComponent(0)).enableMinusButton();    
        this.jPanel1.revalidate();
        this.jPanel1.repaint();
        
        return asip;
    }
    
    /**
     * Removes an AddSubtractInnerPanel from this panel.
     * @param a The AddSubtractInnerPanel to remove from this panel.
     */
    public void removeAddSubPanel(AddSubtractInnerPanel a) {
        this.jPanel1.remove(a);
        if (this.jPanel1.getComponentCount() == 2) {
            // Disable the one - button
            ((AddSubtractInnerPanel)
                    this.jPanel1.getComponent(0)).disableMinusButton();        
        }        
        this.jPanel1.revalidate();
        this.jPanel1.repaint();
    }
    
    public void removeFirstAddSubPanel() {
        this.jPanel1.remove(0);
        this.jPanel1.revalidate();
        this.jPanel1.repaint();
    }
    
    /**
     * Sets the ComboBoxModels that should be assigned to each new panel and
     * sets the property of each ComboBoxes editable state.
     * 
     * @param cbm1 The first ComboBoxModel
     * @param cbm2 The second ComboBoxModel
     * @param cb1 Set the left ComboBox editable if true.
     * @param cb2 Set the right ComboBox editable if true.
     */
    public void setComboBoxPrefs(ComboBoxModel cbm1, ComboBoxModel cbm2, 
            boolean cb1, boolean cb2) {
        this.cbm1 = cbm1;
        this.cbm2 = cbm2;
        this.cbEditable1 = cb1;
        this.cbEditable2 = cb2;
        AddSubtractInnerPanel asip = new AddSubtractInnerPanel(cbm1, cbm2);
        asip.disableMinusButton();
        asip.setComboBox1Editable(this.cbEditable1);
        asip.setComboBox2Editable(this.cbEditable2);
        this.jPanel1.add(asip);
        // Add a glue component to take up extra space so the panels that
        // are added aren't stretched.
        this.jPanel1.add(Box.createVerticalGlue(), 1);
    }
    
    /**
     * Gets the AddSubtractInnerPanels from this panel.  These panels contain
     * information usefull for searches and other actions.
     * 
     * @return A List of AddSubtractInnerPanels.
     */
    public List<AddSubtractInnerPanel> getInnerPanelsValues() {
        List<AddSubtractInnerPanel> info = new ArrayList<AddSubtractInnerPanel>();
        for (int i = 0; i < this.jPanel1.getComponentCount(); i++) {
            if (this.jPanel1.getComponent(i) instanceof AddSubtractInnerPanel)
                info.add((AddSubtractInnerPanel) this.getComponent(i));
        }
        return info;
    }
    
    /**
     * Creates a new inner panel and trys to select an item with a given name.
     * 
     * @param name The name of the item.
     */
    public void createAndSetSelected(String name) {
        AddSubtractInnerPanel asip = addAddSubPanel();
        asip.setSelectedLeftComboBox(name);
    }
}