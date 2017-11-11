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
package me.dsnet.quickopener.actions;

import me.dsnet.quickopener.actions.popup.CustomCommandPopupAction;
import me.dsnet.quickopener.actions.popup.CustomFileSystemPopupAction;
import me.dsnet.quickopener.actions.popup.CustomTerminalPopupAction;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;

/**
 *
 * @author SessonaD
 */
@Messages("CTL_CustomCommand=Launch custom command")
public final class ToolbarPresenter implements Presenter.Toolbar {
    
    public ToolbarPresenter(){        
    }
    
    @Override
    public Component getToolbarPresenter() {
        
        final ImageIcon run16 = ImageUtilities.loadImageIcon("me/dsnet/quickopener/icons/run.png", false); 
        final ImageIcon run32 = ImageUtilities.loadImageIcon("me/dsnet/quickopener/icons/run24.png", false);
        final ImageIcon folder = ImageUtilities.loadImageIcon("me/dsnet/quickopener/icons/folder-documents-icon-cu.png",false);
        final ImageIcon terminal = ImageUtilities.loadImageIcon("me/dsnet/quickopener/icons/terminal-cu.png",false);
        
        CustomCommandPopupAction cAction=new CustomCommandPopupAction("Launch custom command...",run16);
        CustomTerminalPopupAction tAction=new CustomTerminalPopupAction("Open shell in...",terminal);
        CustomFileSystemPopupAction fAction=new CustomFileSystemPopupAction("Open filesystem in...",folder);
        
        //popup
        JPopupMenu popup = new JPopupMenu();        
        popup.add(cAction); 
        popup.addSeparator();
        popup.add(tAction);
        popup.add(fAction);
        
        //button
        final JButton dropDownButton = DropDownButtonFactory.createDropDownButton(run16,popup);
        dropDownButton.addActionListener(cAction);
        dropDownButton.addPropertyChangeListener("PreferredIconSize", new PropertyChangeListener(){ 
            @Override 
            public void propertyChange(PropertyChangeEvent evt) 
            { 
                boolean useSmallIcon = true; 
                final Object prop = evt.getNewValue(); 
                if (prop instanceof Integer) { 
                    if(((Integer)prop).intValue()==24) { 
                        useSmallIcon = false; 
                    } 
                }                 
                if(useSmallIcon){ 
                    dropDownButton.setIcon(run16);
                }else { 
                    dropDownButton.setIcon(run32);
                } 
            } 
        }); 
        return dropDownButton;
    }

}
