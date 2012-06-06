/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.common.QuickUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Tools",id = "com.sessonad.quickopener.actions.FileSystem")
@ActionRegistration(iconBase = "com/sessonad/quickopener/icons/folder-documents-icon.png",displayName = "#CTL_FileSystem")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1419),
    @ActionReference(path = "Toolbars/File", position = 600),
    @ActionReference(path = "Shortcuts", name = "O-2")
})
@Messages("CTL_FileSystem=Open in the OS file system browser")
public final class FileSystem implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        File f = QuickUtils.getCurrentFile();
        File toOpen = (f != null) ? QuickUtils.getParentFolder(f) : QuickUtils.getMainProjectRoot();
        try {
            QuickUtils.getDesktop().open(toOpen);
        } catch (Exception ex) {
        }
    }
}
