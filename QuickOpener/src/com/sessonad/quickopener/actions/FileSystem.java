package com.sessonad.quickopener.actions;

import com.sessonad.oscommands.commands.Commands;
import com.sessonad.quickopener.PathFinder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
@Messages("CTL_FileSystem=Open in the OS file system browser")
public final class FileSystem implements ActionListener {

    private final DataObject dataObj;

    public FileSystem(DataObject dataObj) {
        this.dataObj = dataObj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        File toOpen = PathFinder.getFileFromDataObject(dataObj);
        try {
            Commands.getPlatform().browseInFileSystem(toOpen);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
