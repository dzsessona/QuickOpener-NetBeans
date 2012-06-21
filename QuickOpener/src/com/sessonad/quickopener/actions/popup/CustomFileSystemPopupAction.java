package com.sessonad.quickopener.actions.popup;

import com.sessonad.oscommands.commands.Commands;
import com.sessonad.quickopener.actions.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author SessonaD
 */
public class CustomFileSystemPopupAction extends AbstractAction{
    
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
                    String userpath = JOptionPane.showInputDialog("Open folder...");
                    if (userpath != null && !userpath.isEmpty()) {
                        File toOpen = new File(userpath);
                        Commands.getPlatform().browseInFileSystem(toOpen);
                    }
                } catch (Exception ex) {}
            }
        });
    }
    
}
