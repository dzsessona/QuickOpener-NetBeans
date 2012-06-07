package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.commands.AbstractCommands;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
@ActionID(category = "Tools",id = "com.sessonad.quickopener.actions.Path")
@ActionRegistration(iconBase = "com/sessonad/quickopener/icons/path.png",displayName = "#CTL_Path")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1422, separatorAfter = 1424),
    @ActionReference(path = "Toolbars/File", position = 700),
    @ActionReference(path = "Shortcuts", name = "O-3")
})
@Messages("CTL_Path=Copy path in the clipboard")
public final class Path implements ActionListener {

    private final DataObject dataObj;

    public Path(DataObject dataObj) {
        this.dataObj = dataObj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String path=AbstractCommands.getPathFromDataObject(dataObj);
        if (path != null) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(path);
            clipboard.setContents(strSel, null);
        }
    }
}
