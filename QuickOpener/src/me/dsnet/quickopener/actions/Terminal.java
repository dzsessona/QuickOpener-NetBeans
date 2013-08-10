package me.dsnet.quickopener.actions;

import com.sessonad.oscommands.commands.Commands;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
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
        id = "me.dsnet.quickopener.actions.Terminal"
        )
@ActionRegistration(
        iconBase = "me/dsnet/quickopener/icons/terminal.png",
        displayName = "#CTL_Terminal"
        )
@Messages("CTL_Terminal=Open in the default OS terminal")
public final class Terminal extends AbstractDataObjectAction  {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path=PathFinder.getActivePath(getDataObject(), true);
            if(path == null){
                NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_FILE_IN_SELECTION,NotifyDescriptor.WARNING_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return;
            }
            //open in os shell or in the custom one if defined
            QuickOpenerProperty customShell=PrefsUtil.load(null,"customShell",null);
            if(customShell.getValue()==null){
                Commands.getPlatform().openInShell(path);
            }else{
                customShellOpen(customShell.getValue(),path);
            }            
        } catch (Exception ex) {} 
    }
    
    private void customShellOpen(String customShellPrefix, String path)throws Exception{
        String fullCommand = customShellPrefix + path;
        Runtime.getRuntime().exec(fullCommand);
    }
}
