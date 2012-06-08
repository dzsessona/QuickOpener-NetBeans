package com.sessonad.quickopener.commands;

import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author SessonaD
 */
public class LinuxGnomeCommands extends Commands{

    @Override
    public String getShellCommand() {
        return "gnome-terminal";
    }    
    
    @Override
    public void browseInFileSystem(File current) throws Exception {
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)){
            Desktop.getDesktop().open(current);
        }else{
            String fullCommand="nautilus " + current.getAbsolutePath();
            Runtime.getRuntime().exec(fullCommand);
        }        
    }

    @Override
    public void openInShell(String currentPath) throws Exception {
        String fullCommand=getShellCommand() + " --working-directory=" + currentPath;
        Runtime.getRuntime().exec(fullCommand);
    } 
    
}
