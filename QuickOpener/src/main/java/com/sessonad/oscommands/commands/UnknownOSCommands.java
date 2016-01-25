package com.sessonad.oscommands.commands;

import com.sessonad.oscommands.detector.OperatingSystem;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author SessonaD
 */
public class UnknownOSCommands extends Commands {

    @Override
    public void browseInFileSystem(File current) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            Desktop.getDesktop().open(current);
        } else {
            throw new Exception("Not supported yet in this Operating System");
        }
    }

    @Override
    public void openInShell(String currentPath) throws Exception {
        throw new Exception("Not supported yet in this Operating System");
    }

    @Override
    public OperatingSystem getOperatingSystem() {
        return OperatingSystem.UNKNOWN;
    }
}
