package com.sessonad.quickopener.actions.popup;

import com.sessonad.oscommands.commands.Commands;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 *
 * @author SessonaD
 */
public class CustomTerminalPopupAction extends AbstractAction{
    
    public CustomTerminalPopupAction() {
    }
    
    public CustomTerminalPopupAction(String name) {
        super(name);
    }
    
    public CustomTerminalPopupAction(String name, Icon icon) {
        super(name, icon);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DialogCustomTerminal dialogue=new DialogCustomTerminal(null, true);  
                    
                    //center on screen
                    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    final int x = (screenSize.width - dialogue.getWidth()) / 2;
                    final int y = (screenSize.height - dialogue.getHeight()) / 2;
                    dialogue.setLocation(x, y);
                    dialogue.setVisible(true);
                    
                    String userCommand = (dialogue.getReturnStatus()==DialogCustomTerminal.RET_OK)?dialogue.getCommand():null;
                    if (userCommand != null && !userCommand.isEmpty()) {
                        Commands.getPlatform().openInShell(userCommand);
                    }
                } catch (Exception ex) {}
            }
        });
    }
    
}
