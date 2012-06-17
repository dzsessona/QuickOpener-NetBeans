package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OperatingSystem;

/**
 *
 * @author SessonaD
 */
public class WindowsCommands extends Commands {

    @Override
    public OperatingSystem getOperatingSystem() {
        return OperatingSystem.WINDOWS;
    }
}
