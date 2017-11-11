package me.dsnet.quickopener.actions;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author SessonaD
 * @author markiewb (contributor)
 */
@ActionID(
        category = "Tools",
        id = "me.dsnet.quickopener.actions.Path")
@ActionRegistration(
        lazy = false,
        displayName = "#CTL_Path"
)
@Messages("CTL_Path=Copy Path")
public final class Path extends AbstractFileContextAwareAction {

    @StaticResource
    private static final String icon = "me/dsnet/quickopener/icons/path.png";

    @Override
    public String getName() {
        return Bundle.CTL_Path();
    }

    @Override
    protected String iconResource() {
        return icon;
    }

    @Override
    protected void performAction(Node[] activatedNodes) {
        File file = getFile();

        String path = PathFinder.getActivePath(file);
        if (path == null) {
            NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION, NotifyDescriptor.WARNING_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }
        String ossep = getOSSeparator();
        QuickOpenerProperty prop = PrefsUtil.load(null, "generalseparator", ossep);
        if (!prop.getValue().equals(ossep)) {
            String torep = (ossep.equals("\\")) ? "\\\\" : ossep;
            String repl = (prop.getValue().equals("\\")) ? "\\\\" : prop.getValue();
            path = path.replaceAll(torep, repl);
        }
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(path);
            clipboard.setContents(strSel, null);
        } catch (HeadlessException ex) {
        }
    }

    private String getOSSeparator() {
        return System.getProperty("file.separator");
    }

}
