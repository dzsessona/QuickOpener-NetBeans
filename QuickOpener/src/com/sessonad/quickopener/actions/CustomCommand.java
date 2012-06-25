/**
 *
 * @author SessonaD
 */
package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.actions.popup.CustomCommandPopupAction;
import com.sessonad.quickopener.actions.popup.CustomFileSystemPopupAction;
import com.sessonad.quickopener.actions.popup.CustomTerminalPopupAction;
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

//@ActionID(category="Tools",id="com.sessonad.quickopener.actions.CustomCommand")
//@ActionRegistration(iconBase="com/sessonad/quickopener/actions/custom.png",displayName="#CTL_CustomCommand")
//@ActionReferences({@ActionReference(path="Toolbars/QuickOpener", position=800)})
@Messages("CTL_CustomCommand=Launch custom command")
public final class CustomCommand implements Presenter.Toolbar {
    
    public CustomCommand(){        
    }
    
    @Override
    public Component getToolbarPresenter() {
        
        final ImageIcon run16 = ImageUtilities.loadImageIcon("com/sessonad/quickopener/icons/run.png", false); 
        final ImageIcon run32 = ImageUtilities.loadImageIcon("com/sessonad/quickopener/icons/run24.png", false);
        final ImageIcon folder = ImageUtilities.loadImageIcon("com/sessonad/quickopener/icons/folder-documents-icon.png",false);
        final ImageIcon terminal = ImageUtilities.loadImageIcon("com/sessonad/quickopener/icons/terminal.png",false);
        
        CustomCommandPopupAction cAction=new CustomCommandPopupAction("Launch custom command...",run16);
        CustomTerminalPopupAction tAction=new CustomTerminalPopupAction("Open shell in...",folder);
        CustomFileSystemPopupAction fAction=new CustomFileSystemPopupAction("Open filesystem in...",terminal);
        
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
