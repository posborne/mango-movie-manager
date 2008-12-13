package com.themangoproject.ui.model;

public interface MangoTableModelIF {
	/**
	 * This method is called when a table model is being removed. If any
	 * listeners need to be removed or anything like that, this method will take
	 * care of it.
	 */
	public void clenup();
}
