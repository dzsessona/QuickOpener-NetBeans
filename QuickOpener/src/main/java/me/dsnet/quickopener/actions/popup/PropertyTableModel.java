package me.dsnet.quickopener.actions.popup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
import java.util.List;
import java.util.prefs.BackingStoreException;
import javax.swing.table.AbstractTableModel;
import org.openide.util.Exceptions;
import org.openide.util.Pair;

/**
 *
 * @author SessonaD, markiewb
 */
public class PropertyTableModel extends AbstractTableModel {

    private boolean dirty;
    private String[] columnNames = {"Name", "Path"};
    private List<Pair<String, String>> data = new ArrayList<>();

    public PropertyTableModel(String prefix) {
        setColumnNames(prefix);
        try {
            List<QuickOpenerProperty> prefs = PrefsUtil.getAllMatching(prefix);
            for (int i = 0; i < prefs.size(); i++) {
                QuickOpenerProperty pref = prefs.get(i);
                String desc = pref.getDescription();
                String val = pref.getValue();
                addOrUpdateItem(desc, val);
            }
        } catch (BackingStoreException ex) {
            //Exceptions.printStackTrace(ex);
        }
        dirty = false;
    }

    public void addItem(String desc, String val) {
        addOrUpdateItem(desc, val);
        this.fireTableDataChanged();
        dirty = true;
    }

    public List<Pair<String, String>> getBackingData() {
        return data;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void removeItem(int index) {
        this.data.remove(index);
        this.fireTableDataChanged();
        dirty = true;
    }

    private void addOrUpdateItem(String desc, String val) {
        int index = findByDescription(desc);
        if (index != -1) {
            //update existing
            this.data.set(index, Pair.of(desc, val));
        } else {
            // add new
            this.data.add(Pair.of(desc, val));
        }
    }

    private int findByDescription(String desc) {
        for (int i = 0; i < data.size(); i++) {
            Pair<String, String> get = data.get(i);
            if (desc.equals(get.first())) {
                return i;
            }
        }
        return -1;
    }

    private void setColumnNames(String prefix) {
        if (prefix.equals("command")) {
            columnNames = new String[]{"Name", "Command"};
        } else {
            columnNames = new String[]{"Name", "Path"};
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        final Pair<String, String> item = data.get(row);
        if (col == 0) {
            return item.first();
        } else {
            return item.second();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return String.class;
    }

}
