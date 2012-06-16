package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OSDetector;
import com.sessonad.quickopener.OperatingSystem;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

/**
 *
 * @author SessonaD
 */
public abstract class Commands {
    
    static Commands platform;
    
    public abstract OperatingSystem getOperatingSystem();
        
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
    
    public static String getPathFromDataObject(DataObject dataObj){
        return getFileFromDataObject(dataObj).getAbsolutePath();
    }
    
    public static File getFileFromDataObject(DataObject dataObj){
         File current = FileUtil.toFile(dataObj.getPrimaryFile());
         return (current.isDirectory())?current:current.getParentFile();
    }
    
    public static File getMainProjectRoot(){
        Project project = OpenProjects.getDefault().getMainProject();
        FileObject root = project.getProjectDirectory();
        return FileUtil.toFile(root);
    }

    public void openInShell(String currentPath) throws Exception {
        String fullCommand = OperatingSystem.LINUX_KDE.getShellCommand() + currentPath;
        Runtime.getRuntime().exec(fullCommand);
    }
    
    public void browseInFileSystem(File current) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            try {
                Desktop.getDesktop().open(current);
            } catch (IOException e) {
                // Run the process as a fallback, as on
                // some systems Desktop.open fails despite claiming
                // to support it.
                executeFileSystemBrowserCommand(current);
            }
        } else {
            executeFileSystemBrowserCommand(current);
        }
    }

    protected void executeFileSystemBrowserCommand(File current) throws IOException {
        String fullCommand = getOperatingSystem().getFileSystemBrowserCommand() + current.getAbsolutePath();
        Runtime.getRuntime().exec(fullCommand);
    }
    
}
