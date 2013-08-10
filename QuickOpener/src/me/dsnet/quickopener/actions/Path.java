package me.dsnet.quickopener.actions;

import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        id = "me.dsnet.quickopener.actions.Path"
        )
@ActionRegistration(
        iconBase = "me/dsnet/quickopener/icons/path.png",
        displayName = "#CTL_Path"
        )
@Messages("CTL_Path=Copy path in the clipboard")
public final class Path extends AbstractDataObjectAction  {

    private String getOSSeparator(){
        return System.getProperty("file.separator");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String path=PathFinder.getActivePath(getDataObject(), false);  
        if (path == null) {            
            NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION,NotifyDescriptor.WARNING_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }
        String ossep=getOSSeparator();
        QuickOpenerProperty prop=PrefsUtil.load(null,"generalseparator",ossep);
        if(!prop.getValue().equals(ossep)){
            String torep=(ossep.equals("\\"))?"\\\\":ossep;
            String repl=(prop.getValue().equals("\\"))?"\\\\":prop.getValue();
            path = path.replaceAll(torep, repl);                          
        }
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(path);
            clipboard.setContents(strSel, null);
        }catch (Exception ex) {}
    }
}
