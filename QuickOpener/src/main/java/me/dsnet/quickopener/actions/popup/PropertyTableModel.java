/*
 * Copyright (C) 2017 Diego Zambelli Sessona (diego.sessona@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.dsnet.quickopener.actions.popup;

import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
import java.util.List;
import java.util.prefs.BackingStoreException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author SessonaD
 */
public class PropertyTableModel extends AbstractTableModel {

    String[] columnNames = {"Name", "Path"};
    Object[][] data = null;

    public PropertyTableModel(String prefix) {
        setColumnNames(prefix);
        try {
            List<QuickOpenerProperty> prefs = PrefsUtil.getAllMatching(prefix);
            this.data = new Object[prefs.size()][2];
            for (int i = 0; i < prefs.size(); i++) {
                QuickOpenerProperty pref = prefs.get(i);
                String desc = pref.getDescription();
                String val = pref.getValue();
                this.data[i] = new String[]{desc, val};
            }
        } catch (BackingStoreException ex) {
            //Exceptions.printStackTrace(ex);
        }
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
