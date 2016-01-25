package me.dsnet.quickopener.actions;

import com.sessonad.oscommands.commands.Commands;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import me.dsnet.quickopener.prefs.PrefsUtil;
import me.dsnet.quickopener.prefs.QuickOpenerProperty;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;


/**
 *
 * @author SessonaD
 * @author markiewb (contributor)
 */
@Messages("CTL_Terminal=Open in Terminal")
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
