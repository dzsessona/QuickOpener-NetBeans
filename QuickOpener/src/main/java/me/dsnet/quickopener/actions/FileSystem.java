package me.dsnet.quickopener.actions;

import com.sessonad.oscommands.commands.Commands;
import me.dsnet.quickopener.QuickMessages;
import java.io.File;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author SessonaD
 * @author markiewb (contributor)
 */
@ActionID(
        category = "Tools",
        id = "me.dsnet.quickopener.actions.FileSystem")
@ActionRegistration(
        lazy = false,
        displayName = "#CTL_FileSystem"
)
@Messages("CTL_FileSystem=Open in File Manager")
public final class FileSystem extends AbstractFileContextAwareAction {

    @StaticResource
    private static final String icon = "me/dsnet/quickopener/icons/folder-documents-icon.png";

    @Override
    public String getName() {
        return Bundle.CTL_FileSystem();
    }

    @Override
    protected String iconResource() {
        return icon;
    }

    @Override
    protected void performAction(Node[] activatedNodes) {
        File file = getFile();
        
        try {
            if (file == null) {
                NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION, NotifyDescriptor.WARNING_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return;
            }
            FileObject fo = FileUtil.toFileObject(FileUtil.normalizeFile(file));
            DataObject dataObj = DataObject.find(fo);
            String path = PathFinder.getActivePath(dataObj, true);
            if (path == null) {
                NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION, NotifyDescriptor.WARNING_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return;
            }

            //open in os shell or in the custom one if defined
            QuickOpenerProperty customShell = PrefsUtil.load(null, "customFileManager", null);
            if (customShell.getValue() == null) {
                Commands.getPlatform().browseInFileSystemToFileOrDir(file);
            } else {
                Runtime.getRuntime().exec(customShell.getValue(), null, new File(path));
            }

        } catch (Exception ex) {
        }//ex.printStackTrace();}
     
    }
    
}
