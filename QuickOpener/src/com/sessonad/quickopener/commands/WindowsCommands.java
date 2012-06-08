package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OperatingSystem;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author SessonaD
 */
public class WindowsCommands extends Commands{

    @Override
    public void browseInFileSystem(File current) throws Exception {
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)){
            Desktop.getDesktop().open(current);
        }else{
            throw new UnsupportedOperationException("Desktop not supported in this version.");
        }        
    }

    @Override
    public void openInShell(String currentPath) throws Exception {
        String fullCommand=OperatingSystem.WINDOWS.getShellCommand() + currentPath;
        Runtime.getRuntime().exec(fullCommand);
    } 
    
}
