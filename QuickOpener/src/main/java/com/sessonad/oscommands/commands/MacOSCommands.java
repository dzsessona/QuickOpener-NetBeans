package com.sessonad.oscommands.commands;

import com.sessonad.oscommands.detector.OperatingSystem;

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
