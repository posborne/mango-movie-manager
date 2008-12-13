package com.themangoproject.ui;

import com.themangoproject.ui.model.navigator.BaseTreeModel;
import javax.swing.*;

/**
 * Custom JTree for navigation stuff.  A lot of this is handled in the models, 
 * but there was enough with all the listeners and such to justify a separate
 * class.
 * 
 * @author Paul Osborne
 */
public class NavigatorJTree extends JTree {
    /**
     * Construct the JTree with the base model and set things so that this
     * stinker shows up how we want it to.
     */
    public NavigatorJTree() {
        super(new BaseTreeModel());
        this.setRootVisible(false);
        this.addTreeSelectionListener(new NavigatorTreeSelectionListener(this));
        this.setShowsRootHandles(true); // make sure we have plus sign
    }
}
