package com.sessonad.quickopener.actions;

import com.sessonad.oscommands.commands.Commands;
import com.sessonad.quickopener.PathFinder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;


/**
 *
 * @author SessonaD
 */
@Messages("CTL_Terminal=Open in the default OS terminal")
public final class Terminal implements ActionListener {
    
    private final DataObject dataObj;

    public Terminal(DataObject dataObj) {
        this.dataObj = dataObj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path=PathFinder.getPathFromDataObject(dataObj,true);
            if(path == null){
                JOptionPane.showMessageDialog(null, "There are no files associated with the current selection.");
                return;
            }
            Commands.getPlatform().openInShell(path);
        } catch (Exception ex) {} 
    }
}
