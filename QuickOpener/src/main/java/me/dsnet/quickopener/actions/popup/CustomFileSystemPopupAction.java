package me.dsnet.quickopener.actions.popup;

import com.sessonad.oscommands.commands.Commands;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import org.openide.util.NbBundle;

/**
 *
 * @author SessonaD
 */
//@ActionID(category = "Tools",id = "com.sessonad.quickopener.actions.popup.CustomFileSystemPopupAction")
//@ActionRegistration(displayName = "#CTL_CustomFileSystemPopupAction")
//@ActionReferences({
//    @ActionReference(path = "Shortcuts", name = "O-5")
//})
@NbBundle.Messages("CTL_CustomFileSystemPopupAction=Open filesystem in...")
public class CustomFileSystemPopupAction extends AbstractAction implements ActionListener{
    
    public CustomFileSystemPopupAction() {
    }
    
    public CustomFileSystemPopupAction(String name) {
        super(name);
    }
    
    public CustomFileSystemPopupAction(String name, Icon icon) {
        super(name, icon);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DialogCustomFileSystem dialogue=new DialogCustomFileSystem(null, true);
                    
                    //center on screen
                    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    final int x = (screenSize.width - dialogue.getWidth()) / 2;
                    final int y = (screenSize.height - dialogue.getHeight()) / 2;
                    dialogue.setLocation(x, y);
                    dialogue.setVisible(true);
                    
                    String userCommand = (dialogue.getReturnStatus()==DialogCustomFileSystem.RET_OK)?dialogue.getCommand():null;
                    if (userCommand != null && !userCommand.isEmpty()) {
                        File userFile=new File(userCommand);
                        Commands.getPlatform().browseInFileSystem(userFile);
                    }
                } catch (Exception ex) {}
            }
        });
    }    
}
