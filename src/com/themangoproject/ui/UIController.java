package com.themangoproject.ui;

import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;
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

    public Frame getMango() {
        return mango;
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
        
        public int getTableItemCount() {
            return mango.getTableItemCount();
        }
        
        public int getOwnerId() {
            Properties p = null;
            int id = -1;
            try{
                p = new Properties();
                p.load(new FileInputStream("mango.properties"));
                id = Integer.parseInt(p.getProperty("MangoOwner"));
            } catch (Exception e) {
            }  
            return id;
        }
	
        public void setOwnerId(int id) {            
            Properties p = null;
            try{
                p = new Properties();
                p.setProperty("MangoOwner", "" + id);
                FileOutputStream out = new FileOutputStream("mango.properties");
                p.store(out,"");
            } catch (Exception e) {
                
            }  
        }
        
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Value: ");
            int id = Integer.parseInt(scan.nextLine());
            UIController uic = new UIController();
            uic.setOwnerId(id);
            System.out.println(uic.getOwnerId());
            
        }
}
