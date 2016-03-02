/**
 *
 * @author SessonaD
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
