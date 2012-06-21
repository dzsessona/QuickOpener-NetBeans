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
                    String userpath = JOptionPane.showInputDialog("Open shell in...");
                    if (userpath != null && !userpath.isEmpty()) {
                        Commands.getPlatform().openInShell(userpath);
                    }
                } catch (Exception ex) {}
            }
        });
    }
    
}
