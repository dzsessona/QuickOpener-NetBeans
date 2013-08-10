package me.dsnet.quickopener.actions;

import com.sessonad.oscommands.commands.Commands;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;


/**
 *
 * @author SessonaD
 */

@ActionID(
        category = "Tools",
        id = "me.dsnet.quickopener.actions.FileSystem"
        )
@ActionRegistration(
        iconBase = "me/dsnet/quickopener/icons/folder-documents-icon.png",
        displayName = "#CTL_FileSystem"
        )
@Messages("CTL_FileSystem=Open in the OS file system browser")
public final class FileSystem extends AbstractDataObjectAction  {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        File toOpen = PathFinder.getActiveFile(getDataObject(), true);
        if (toOpen == null) {
            NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION,NotifyDescriptor.WARNING_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }
        try {
            Commands.getPlatform().browseInFileSystem(toOpen);
        } catch (Exception ex) {}//ex.printStackTrace();}
    }
}
