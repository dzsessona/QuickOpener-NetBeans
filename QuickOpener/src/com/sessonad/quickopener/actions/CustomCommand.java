/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.actions.popup.CustomCommandPopupAction;
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
        
        
        Image iconImage = ImageUtilities.loadImage("com/sessonad/quickopener/icons/custom24.png");
        ImageIcon icon = new ImageIcon(iconImage); 
        CustomCommandPopupAction caction=new CustomCommandPopupAction("Launch custom command...",icon);
        
        //popup
        JPopupMenu popup = new JPopupMenu();        
        popup.add(caction);  
        
        //button
        JButton dropDownButton = DropDownButtonFactory.createDropDownButton(icon,popup); 
        dropDownButton.addActionListener(caction);
        return dropDownButton;
    }

}
