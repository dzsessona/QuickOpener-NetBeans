/*
 * Copyright (C) 2017 Diego Zambelli Sessona (diego.sessona@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
