package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OperatingSystem;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author SessonaD
 */
public class MacOSCommands extends Commands{

    @Override
    public void browseInFileSystem(File current) throws Exception {
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)){
            Desktop.getDesktop().open(current);
        }else{
            String fullCommand=OperatingSystem.MAC_OS.getFileSystemBrowserCommand() + current.getAbsolutePath();
            Runtime.getRuntime().exec(fullCommand);
        }        
    }

    @Override
    public void openInShell(String currentPath) throws Exception {
        String fullCommand = OperatingSystem.MAC_OS.getShellCommand() + currentPath;
        Runtime.getRuntime().exec(fullCommand);
    } 
    
}
