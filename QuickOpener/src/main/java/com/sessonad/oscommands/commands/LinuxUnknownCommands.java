package com.sessonad.oscommands.commands;

import com.sessonad.oscommands.detector.OperatingSystem;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author SessonaD
 */
public class LinuxUnknownCommands extends Commands {

    @Override
    public void browseInFileSystem(File current) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            Desktop.getDesktop().open(current);
        } else {
            throw new Exception("Not supported yet in this distro of Linux");
        }
    }

    @Override
    public void openInShell(String currentPath) throws Exception {
        throw new Exception("Not supported yet in this distro of Linux");
    }

    @Override
    public OperatingSystem getOperatingSystem() {
        return OperatingSystem.LINUX_UNKNOWN;
    }
}
