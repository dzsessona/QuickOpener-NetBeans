/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener.actions;

import com.sessonad.quickopener.PathFinder;
import com.sessonad.quickopener.QuickMessages;
import com.sessonad.quickopener.prefs.PrefsUtil;
import com.sessonad.quickopener.prefs.QuickOpenerProperty;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author SessonaD
 */
//@ActionID(category = "Tools",id = "com.sessonad.quickopener.actions.RunDefaultCommand")
//@ActionRegistration(displayName = "#CTL_RunDefaultCommand")
//@ActionReferences({
//    @ActionReference(path = "Shortcuts", name = "O-7")
//})
@NbBundle.Messages("CTL_RunDefaultCommand=Run default command...")
public class RunDefaultCommand implements ActionListener{
    
            
    @Override
    public void actionPerformed(ActionEvent e) {
        
        QuickOpenerProperty defaultCmd = PrefsUtil.load(null, "favoriteCmd", null);
        String command = (defaultCmd==null)?null:defaultCmd.getValue();
        
        if (command == null) {            
            NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_DEFAULT_COMMAND,NotifyDescriptor.WARNING_MESSAGE);
            DialogDisplayer.getDefault().notify(d);           
        }else{
            String currentFile=PathFinder.getFileFromSelectedNode(false);
            String currentFolder = PathFinder.getFileFromSelectedNode(true);
            String relativeFile=PathFinder.getRelativePathFromSelectedNode(false);
            String relativeFolder = PathFinder.getRelativePathFromSelectedNode(true);
            if(command.contains("${currentFile}") && currentFile!=null && !currentFile.isEmpty()) {command=command.replace("${currentFile}", currentFile);}  
            if(command.contains("${currentFolder}") && currentFolder!=null && !currentFolder.isEmpty()) { command=command.replace("${currentFolder}", currentFolder);} 
            if(command.contains("${relativeFile}") && relativeFile!=null && !relativeFile.isEmpty()) {command=command.replace("${relativeFile}", relativeFile);}  
            if(command.contains("${relativeFolder}") && relativeFolder!=null && !relativeFolder.isEmpty()) { command=command.replace("${relativeFolder}", relativeFolder);}
            if((command.contains("${relativeFolder}"))||(command.contains("${currentFile}"))||(command.contains("${relativeFile}"))||(command.contains("${currentFolder}"))){
                NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_DEFAULT_PARAMETERS,NotifyDescriptor.WARNING_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return;
            }
            try{               
                String msg = QuickMessages.CONFIRM_COMMAND_PREFIX + command + QuickMessages.CONFIRM_COMMAND_SUFFIX;
                boolean nedToConfirm = Boolean.parseBoolean((PrefsUtil.load(null,"confirmationDialogue","true")).getValue());
                if (!nedToConfirm) {
                    Runtime.getRuntime().exec(command);
                } else {
                    NotifyDescriptor d = new NotifyDescriptor.Confirmation(msg, "Confirm", NotifyDescriptor.OK_CANCEL_OPTION);
                    if (NotifyDescriptor.OK_OPTION == DialogDisplayer.getDefault().notify(d)) {
                        Runtime.getRuntime().exec(command);
                    }
                }                                
            }catch(Exception ex){}            
        }
    }
}
