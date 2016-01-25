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
    
}
