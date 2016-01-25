package com.sessonad.oscommands.commands;


import com.sessonad.oscommands.detector.OSDetector;
import com.sessonad.oscommands.detector.OperatingSystem;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author SessonaD
 * @author markiewb
 */
public abstract class Commands {
    
    static Commands platform;
    
    @SuppressWarnings("unchecked")
    public static <T extends Commands> T getPlatform(){
        if (platform == null)initializePlatform();
        return (T)platform;
    }
    
    static void initializePlatform(){
        OperatingSystem os = OSDetector.detectOS();
        if      (os.equals(OperatingSystem.WINDOWS))    platform = new WindowsCommands();
        else if (os.equals(OperatingSystem.MAC_OS))     platform = new MacOSCommands();
        else if (os.equals(OperatingSystem.LINUX_GNOME))platform = new LinuxGnomeCommands();        
        else if (os.equals(OperatingSystem.LINUX_KDE))  platform = new LinuxKdeCommands();
        else if (os.equals(OperatingSystem.LINUX_LXDE)) platform = new LinuxLxdeCommands();
        else if (os.equals(OperatingSystem.LINUX_XFCE)) platform = new LinuxXfceCommands();
        else if (os.equals(OperatingSystem.LINUX_UNKNOWN)) platform = new LinuxUnknownCommands();
        else if (os.equals(OperatingSystem.UNKNOWN))    platform = new UnknownOSCommands();
    }
    
    public abstract OperatingSystem getOperatingSystem();
    
    public void openInShell(String currentPath) throws Exception {
        String fullCommand = "";
        if(getOperatingSystem().equals(OperatingSystem.WINDOWS)){
            fullCommand = getOperatingSystem().getShellCommand()+"\""+currentPath+"\"";
        }else{
            fullCommand = getOperatingSystem().getShellCommand() + currentPath;
        }
        Runtime.getRuntime().exec(fullCommand);
    }
    
    /**
     * Old function. Only left for backward compatibility.
     *
     * @param current
     * @throws Exception
     */
    @Deprecated
    public void browseInFileSystem(File current) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            try {
                Desktop.getDesktop().open(current);
            } catch (IOException e) {
                executeFileSystemBrowserCommand(current);
            }
        } else {
            executeFileSystemBrowserCommand(current);
        }
    }
    /**
     * Browses to the file or directory (depending on the features of the
     * OS/file-browser). If it does work, then
     * {@link Desktop#open(java.io.File)} is used.
     *
     * @param fileOrDir
     * @throws Exception
     */
    public void browseInFileSystemToFileOrDir(File fileOrDir) throws Exception {
        if (null == fileOrDir) {
            return;
        }

        try {

            Process process = executeFileSystemBrowserCommand(fileOrDir);
            if (null != process) {

                boolean waitFor = process.waitFor(2, TimeUnit.SECONDS);
                if (waitFor) {
                    //everything was fine
                    return;
                } else {
                    //fallback to Desktop.open()
                }
            }
        } catch (Exception e) {
            //execution via executeFileSystemBrowserCommand did not work, so fallback to Desktop.open()
        }

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            try {
                File directory = fileOrDir.isDirectory() ? fileOrDir : fileOrDir.getParentFile();
                Desktop.getDesktop().open(directory);
            } catch (IOException e) {
                //NOP, no fallback anymore
            }
        }

    }

    /**
     * 
     * @param fileOrDir
     * @return exit-code from the process (!=0 means error)
     * @throws IOException 
     */
    protected Process executeFileSystemBrowserCommand(File fileOrDir) throws IOException {
        final boolean isFile = fileOrDir.isFile();
        File directory = isFile ? fileOrDir.getParentFile() : fileOrDir;
        String fullCommand = null;
        if (isFile) {
            if (getOperatingSystem().isFeatured(SupportedFeatures.BROWSE_TO_FILE)) {
                fullCommand = getOperatingSystem().getFileSystemBrowserCommandForFile() + fileOrDir.getAbsolutePath();
            }
            if (getOperatingSystem().isFeatured(SupportedFeatures.BROWSE_TO_DIR)) {
                fullCommand = getOperatingSystem().getFileSystemBrowserCommand() + directory.getAbsolutePath();
            }
        } else {
            //exp
            fullCommand = getOperatingSystem().getFileSystemBrowserCommand() + directory.getAbsolutePath();
        }
        if (null == fullCommand) {
            return null;
        }

        return Runtime.getRuntime().exec(fullCommand);
    }
}
