package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.PathFinder;
import com.sessonad.quickopener.QuickMessages;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;


/**
 *
 * @author SessonaD
 */
@Messages("CTL_Path=Copy path in the clipboard")
public final class Path implements ActionListener {

    private final DataObject dataObj;

    public Path(DataObject dataObj) {
        this.dataObj = dataObj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String path=PathFinder.getPathFromDataObject(dataObj,false);
        if (path == null) {
            NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION);
            DialogDisplayer.getDefault().notify(d);
            return;
        }
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(path);
            clipboard.setContents(strSel, null);
        }catch (Exception ex) {}
    }
}
