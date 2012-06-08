package com.sessonad.quickopener;

/**
 *
 * @author SessonaD
 */
public enum OperatingSystem {
    
    WINDOWS     ("cmd /c start cd ",null),
    MAC_OS      ("open -a /Applications/Utilities/Terminal.app ","open -R "),
    LINUX_GNOME ("gnome-terminal --working-directory=","nautilus "),
    LINUX_KDE   ("konsole --workdir ","konqueror "),
    LINUX_XFCE  ("exo-open --launch TerminalEmulator --working-directory ","thunar "),
    LINUX_LXDE  ("lxterminal --working-directory=","pcmanfm "),
    LINUX_UNKNOWN(null,null),
    UNKNOWN     (null,null);
    
    private String shellCommand;
    private String fileSystemBrowserCommand;

    private OperatingSystem(String shellCommand, String fileSystemBrowserCommand) {
        this.shellCommand = shellCommand;
        this.fileSystemBrowserCommand = fileSystemBrowserCommand;
    }

    public String getFileSystemBrowserCommand() {
        return fileSystemBrowserCommand;
    }

    public String getShellCommand() {
        return shellCommand;
    }

}
