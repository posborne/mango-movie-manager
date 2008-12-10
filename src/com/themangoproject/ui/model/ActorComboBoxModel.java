package com.themangoproject.ui.model;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import com.themangoproject.model.Actor;
import com.themangoproject.model.MangoController;

/**
 * This combo box model gives encapsulates the list of all actors that exist.
 * The list should be automatically updated when the a method to change the
 * database occurs, and the client code can retrieve an Actor object from the
 * combo box model from its index.
 * 
 * @author Paul Osborne
 */
public class ActorComboBoxModel extends DefaultComboBoxModel {

	/** generated serial version uid */
	private static final long serialVersionUID = 1841056682059744938L;

	/** List of all actors */
	private List<Actor> actors;

	/**
	 * Construct an ActorComboBoxModel.
	 */
	public ActorComboBoxModel() {
		actors = MangoController.getInstance().getAllActors();
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
        
        public Object getActorAt(int index) {
            return actors.get(index);
        }

}
