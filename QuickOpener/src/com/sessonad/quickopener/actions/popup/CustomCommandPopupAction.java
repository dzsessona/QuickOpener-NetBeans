/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener.actions.popup;

import com.sessonad.quickopener.actions.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

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
            public void run() {
                JOptionPane.showConfirmDialog(null, "dssssssssssssssssssssssssssssssssssssssss");
            }
        });
    }
    
}
