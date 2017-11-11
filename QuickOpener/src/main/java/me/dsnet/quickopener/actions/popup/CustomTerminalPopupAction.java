/*
 * Copyright (C) 2017 Diego Zambelli Sessona (diego.sessona@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.dsnet.quickopener.actions.popup;

import com.sessonad.oscommands.commands.Commands;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import org.openide.util.NbBundle;

/**
 *
 * @author SessonaD
 */
//@ActionID(category = "Tools",id = "com.sessonad.quickopener.actions.popup.CustomTerminalPopupAction")
//@ActionRegistration(displayName = "#CTL_CustomTerminalPopupAction")
//@ActionReferences({
//    @ActionReference(path = "Shortcuts", name = "O-6")
//})
@NbBundle.Messages("CTL_CustomTerminalPopupAction=Open shell in...")
public class CustomTerminalPopupAction extends AbstractAction implements ActionListener{
    
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
