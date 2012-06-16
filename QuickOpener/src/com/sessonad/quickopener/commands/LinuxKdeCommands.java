package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OperatingSystem;

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
