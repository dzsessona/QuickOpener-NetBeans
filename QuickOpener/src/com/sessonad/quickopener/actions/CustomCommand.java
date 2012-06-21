/**
 *
 * @author SessonaD
 */
package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.actions.popup.CustomCommandPopupAction;
import com.sessonad.quickopener.actions.popup.CustomFileSystemPopupAction;
import com.sessonad.quickopener.actions.popup.CustomTerminalPopupAction;
import java.awt.Component;
import java.awt.Image;
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
        
        Image iconImage = ImageUtilities.loadImage("com/sessonad/quickopener/icons/custom.png");
        ImageIcon icon = new ImageIcon(iconImage); 
        Image iconImage2 = ImageUtilities.loadImage("com/sessonad/quickopener/icons/folder-documents-icon.png");
        ImageIcon icon2 = new ImageIcon(iconImage2);
        Image iconImage3 = ImageUtilities.loadImage("com/sessonad/quickopener/icons/terminal.png");
        ImageIcon icon3 = new ImageIcon(iconImage3);
        
        CustomCommandPopupAction cAction=new CustomCommandPopupAction("Launch custom command...",icon);
        CustomTerminalPopupAction tAction=new CustomTerminalPopupAction("Open shell in...",icon3);
        CustomFileSystemPopupAction fAction=new CustomFileSystemPopupAction("Open filesystem in...",icon2);
        
        //popup
        JPopupMenu popup = new JPopupMenu();        
        popup.add(cAction); 
        popup.addSeparator();
        popup.add(tAction);
        popup.add(fAction);
        
        //button
        JButton dropDownButton = DropDownButtonFactory.createDropDownButton(icon,popup); 
        dropDownButton.addActionListener(cAction);
        return dropDownButton;
    }

}
