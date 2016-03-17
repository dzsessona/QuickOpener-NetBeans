package me.dsnet.quickopener.actions;

import com.sessonad.oscommands.commands.Commands;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
import java.io.File;
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
import org.openide.util.Utilities;

/**
 *
 * @author SessonaD
 * @author markiewb (contributor)
 */
@ActionID(
        category = "Tools",
        id = "me.dsnet.quickopener.actions.Terminal")
@ActionRegistration(
        lazy = false,
        displayName = "#CTL_Terminal"
)
@Messages("CTL_Terminal=Open in Terminal")
public final class Terminal extends AbstractFileContextAwareAction {

    @StaticResource
    private static final String icon = "me/dsnet/quickopener/icons/terminal.png";

    @Override
    public String getName() {
        return Bundle.CTL_Terminal();
    }

    @Override
    protected String iconResource() {
        return icon;
    }

    @Override
    protected void performAction(Node[] activatedNodes) {
        File file = getFile();

        try {
            FileObject fo = FileUtil.toFileObject(FileUtil.normalizeFile(file));
            DataObject dataObj = DataObject.find(fo);
            String path = PathFinder.getActivePath(dataObj, true);
            if (path == null) {
                NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION, NotifyDescriptor.WARNING_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return;
            }
            //open in os shell or in the custom one if defined
            QuickOpenerProperty customShell = PrefsUtil.load(null, "customShell", null);
            if (customShell.getValue() == null) {
                Commands.getPlatform().openInShell(path);
            } else {
                customShellOpen(customShell.getValue(), path);
            }
        } catch (Exception ex) {
        }
    }

    private void customShellOpen(String customShell, String workingDir) throws Exception {
        String shellTitle = "";
        String fullCommand;
        if (Utilities.isWindows()) {
            //support git bash this way
            //cmd /c start /D workingDIR "" D:\tools\Git\bin\sh.exe --login -i
            fullCommand = String.format("cmd /c start /D %s \"%s\" %s", workingDir, shellTitle, customShell);
        } else {
            fullCommand = customShell;
        }

        Runtime.getRuntime().exec(fullCommand, null, new File(workingDir));
    }

}
