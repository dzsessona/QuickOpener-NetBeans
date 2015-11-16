/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener.actions.popup;

import java.util.List;
import java.util.prefs.BackingStoreException;
import javax.swing.table.AbstractTableModel;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;

/**
 *
 * @author SessonaD
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PropertyTableModel extends AbstractTableModel{
    
    String[] columnNames= {"Name","Path"};
    Object[][] data=null;

    public PropertyTableModel(String prefix) {     
        setColumnNames(prefix);
        try {
            QuickOpenerProperty defaultCmd = PrefsUtil.load(null, "favoriteCmd", null);
            String defaultValue = (defaultCmd==null)?null:defaultCmd.getValue();
            List<QuickOpenerProperty> prefs = PrefsUtil.getAllMatching(prefix);
            this.data = new Object[prefs.size()][2];
            for (int i = 0; i < prefs.size(); i++) {
                QuickOpenerProperty pref = prefs.get(i);
                String desc = pref.getDescription();
                String val  = pref.getValue();
                if(defaultValue!=null && defaultValue.equals(val)){
                    desc = "* " + desc;
                }
                this.data[i]=new String[]{desc,val};
            }
        } catch (BackingStoreException ex) {
            //Exceptions.printStackTrace(ex);
        }
    }
    
    private void setColumnNames(String prefix){
        if(prefix.equals("command")){
            columnNames = new String[]{"Name","Command"};
        }else{
            columnNames = new String[]{"Name","Path"};
        }
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return String.class;
    }
    
    
}