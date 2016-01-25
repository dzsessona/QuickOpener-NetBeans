package com.sessonad.oscommands.commands;

import com.sessonad.oscommands.detector.OperatingSystem;

/**
 *
 * @author SessonaD
 */
public class LinuxKdeCommands extends Commands {

    @Override
    public OperatingSystem getOperatingSystem() {
        return OperatingSystem.LINUX_KDE;
    }
}
