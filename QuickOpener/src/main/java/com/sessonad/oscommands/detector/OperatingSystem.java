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
package com.sessonad.oscommands.detector;

import com.sessonad.oscommands.commands.SupportedFeatures;
import static com.sessonad.oscommands.commands.SupportedFeatures.BROWSE_TO_DIR;
import static com.sessonad.oscommands.commands.SupportedFeatures.BROWSE_TO_FILE;
import java.util.EnumSet;

/**
 *
 * @author SessonaD
 * @author markiewb
 */
public enum OperatingSystem {
    
    WINDOWS     ("cmd /c start ","cmd /c start cd /D ","explorer ", "explorer /select,", EnumSet.of(BROWSE_TO_FILE)),
    MAC_OS      ("open -a /Applications/Utilities/Terminal.app ","open -a /Applications/Utilities/Terminal.app ","open -R ", "open -R ",EnumSet.of(BROWSE_TO_DIR)),
    LINUX_GNOME ("gnome-terminal ","gnome-terminal --working-directory=","nautilus ", "nautilus ",EnumSet.of(BROWSE_TO_DIR)),
    LINUX_KDE   ("konsole ","konsole --workdir ","dolphin ", "dolphin ",EnumSet.of(BROWSE_TO_DIR)),
    LINUX_XFCE  ("exo-open --launch TerminalEmulator ","exo-open --launch TerminalEmulator --working-directory ","thunar ", "thunar ",EnumSet.of(BROWSE_TO_DIR)),
    LINUX_LXDE  ("lxterminal ","lxterminal --working-directory=","pcmanfm ", "pcmanfm ",EnumSet.of(BROWSE_TO_DIR)),
    LINUX_UNKNOWN(null,null,null, null,EnumSet.noneOf(SupportedFeatures.class)),
    UNKNOWN     (null,null,null, null,EnumSet.noneOf(SupportedFeatures.class));
    private final String fileSystemBrowserCommandForFile;
    private final EnumSet<SupportedFeatures> features;
    
    private String shellPrefix;
    private String shellCommand;    
    private String fileSystemBrowserCommand;

    private OperatingSystem(String shellPrefix, String shellCommand, String fileSystemBrowserCommand, String fileSystemBrowserCommandForFile, EnumSet<SupportedFeatures> features) {
        this.shellPrefix = shellPrefix;
        this.shellCommand = shellCommand;
        this.fileSystemBrowserCommand = fileSystemBrowserCommand;
        this.fileSystemBrowserCommandForFile = fileSystemBrowserCommandForFile;
        this.features = features;
    }

    public String getFileSystemBrowserCommand() {
        return fileSystemBrowserCommand;
    }

    public String getFileSystemBrowserCommandForFile() {
        return fileSystemBrowserCommandForFile;
    }

    public String getShellCommand() {
        return shellCommand;
    }

    public String getShellPrefix() {
        return shellPrefix;
    }
    public boolean isFeatured(SupportedFeatures feature){
        return features.contains(feature);
    }
    
    public boolean isLinux(){
        return this.equals(OperatingSystem.LINUX_GNOME)|| this.equals(OperatingSystem.LINUX_KDE)|| this.equals(OperatingSystem.LINUX_LXDE)|| this.equals(OperatingSystem.LINUX_XFCE)|| this.equals(OperatingSystem.LINUX_UNKNOWN);
    }
    
}
