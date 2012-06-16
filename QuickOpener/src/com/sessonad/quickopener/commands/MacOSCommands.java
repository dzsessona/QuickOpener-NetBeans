package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OperatingSystem;

/**
 *
 * @author SessonaD
 */
public class MacOSCommands extends Commands {

    @Override
    public OperatingSystem getOperatingSystem() {
        return OperatingSystem.MAC_OS;
    }
}
