package com.sessonad.quickopener.actions.popup;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 *
 * @author SessonaD
 */
public class CustomCommandPopupAction extends AbstractAction{
    
    public CustomCommandPopupAction() {
    }
    
    public CustomCommandPopupAction(String name) {
        super(name);
    }
    
    public CustomCommandPopupAction(String name, Icon icon) {
        super(name, icon);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {                  
                    DialogCustomCommandRun dialogue=new DialogCustomCommandRun(null, true);
                    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    final int x = (screenSize.width - dialogue.getWidth()) / 2;
                    final int y = (screenSize.height - dialogue.getHeight()) / 2;
                    dialogue.setLocation(x, y);
                    dialogue.setVisible(true);
                    
                    String userCommand = (dialogue.getReturnStatus()==DialogCustomCommandRun.RET_OK)?dialogue.getCommand():null;
                    if (userCommand != null) {
                        Runtime.getRuntime().exec(userCommand);
                    }
                } catch (Exception ex) {}
            }
        });
    }
    
}
