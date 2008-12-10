package com.themangoproject.ui;

import javax.swing.table.TableModel;

public class UIController {
	private static UIController instance;
	private Mango mango;
	
	private UIController() {
		mango = Mango.getCreatedInstance();
	}
	
	public static UIController getInstance() {
		if (instance == null) {
			instance = new UIController();
		}
		return instance;
	}
	
	public void setViewTableModel(TableModel tm) {
		mango.setTableModel(tm);
	}
	
	public TableModel getViewTableModel() {
		return mango.getTableModel();
	}
	
}
