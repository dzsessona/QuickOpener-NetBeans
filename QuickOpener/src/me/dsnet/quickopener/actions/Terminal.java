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
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;


/**
 *
 * @author SessonaD
 */
@Messages("CTL_Terminal=Open in the default OS terminal")
public final class Terminal implements ActionListener {
    
    private final DataObject dataObj;

    public Terminal(DataObject dataObj) {
        this.dataObj = dataObj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path=PathFinder.getActivePath(dataObj,true);
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
