package com.themangoproject.ui.model;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.themangoproject.model.Actor;
import com.themangoproject.model.MangoController;

/**
 * This combo box model gives encapsulates the list of all actors that exist.
 * The list should be automatically updated when the a method to change the
 * database occurs, and the client code can retrieve an Actor object from the
 * combo box model from its index.
 * 
 * @author Paul Osborne, Kyle Ronning
 */
public class ActorComboBoxModel extends DefaultComboBoxModel {

	/** generated serial version uid */
	private static final long serialVersionUID = 1841056682059744938L;

	/** List of all actors */
	private List<Actor> actors;
	
	private ChangeListener actorsChangeListener;

	/**
	 * Construct an ActorComboBoxModel.
	 */
	public ActorComboBoxModel() {
		retrieveActors();
		actorsChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				retrieveActors();
			}
		};
		MangoController.getInstance().addActorsChangeListener(actorsChangeListener);
	}
	
	private void retrieveActors() {
		actors = MangoController.getInstance().getAllActors();
		this.fireContentsChanged(actors, 0, actors.size());
	}

	/**
	 * @return the number of actors in the model
	 */
	@Override
	public int getSize() {
		return actors.size();
	}

	/**
	 * @return the actor at the specified index in the model.
	 */
	@Override
	public Object getElementAt(int index) {
		return actors.get(index).toString();
	}
        
        /**
         * Gets the actor at index <code>index</code>
         * @param index
         * @return
         */
    public Object getActorAt(int index) {
        return actors.get(index);
     }
}
