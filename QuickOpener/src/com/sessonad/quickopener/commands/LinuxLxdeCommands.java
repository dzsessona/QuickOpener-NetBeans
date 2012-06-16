package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OperatingSystem;

/**
 *
 * @author SessonaD
 */
public class LinuxLxdeCommands extends Commands {

    @Override
    public OperatingSystem getOperatingSystem() {
        return OperatingSystem.LINUX_LXDE;
    }
}
