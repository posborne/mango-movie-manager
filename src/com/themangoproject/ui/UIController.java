package com.themangoproject.ui;

import javax.swing.JTable;
import javax.swing.JTree;
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
	
	public JTable getViewTable() {
		return mango.getTable();
	}
	
	public JTree getNavigatorTree() {
		if (mango != null) {
			return mango.getNavigatorTree();
		} else {
			return null;
		}
	}
	
}
