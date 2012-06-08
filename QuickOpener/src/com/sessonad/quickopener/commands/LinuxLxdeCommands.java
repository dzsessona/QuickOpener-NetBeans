package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OperatingSystem;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author SessonaD
 */
public class LinuxLxdeCommands extends Commands{

    @Override
    public void browseInFileSystem(File current) throws Exception {
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)){
            Desktop.getDesktop().open(current);
        }else{
            String fullCommand=OperatingSystem.LINUX_LXDE.getFileSystemBrowserCommand() + current.getAbsolutePath();
            Runtime.getRuntime().exec(fullCommand);
        }        
    }

    @Override
    public void openInShell(String currentPath) throws Exception {
        String fullCommand = OperatingSystem.LINUX_LXDE.getShellCommand() + currentPath;
        Runtime.getRuntime().exec(fullCommand);
    } 
    
}
