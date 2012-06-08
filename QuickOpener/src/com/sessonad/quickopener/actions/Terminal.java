package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.commands.Commands;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;


/**
 *
 * @author SessonaD
 */
@ActionID(category = "Tools",id = "com.sessonad.quickopener.actions.Terminal")
@ActionRegistration(iconBase = "com/sessonad/quickopener/icons/terminal.png",displayName = "#CTL_Terminal")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1413),
    @ActionReference(path = "Toolbars/File", position = 500),
    @ActionReference(path = "Shortcuts", name = "O-1")
})
@Messages("CTL_Terminal=Open in the default OS terminal")
public final class Terminal implements ActionListener {
    
    private final DataObject dataObj;

    public Terminal(DataObject dataObj) {
        this.dataObj = dataObj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path=Commands.getPathFromDataObject(dataObj);
            Commands.getPlatform().openInShell(path);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
    }
}
