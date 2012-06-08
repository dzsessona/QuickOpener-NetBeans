package com.sessonad.quickopener.commands;

import com.sessonad.quickopener.OSDetector;
import com.sessonad.quickopener.OperatingSystem;
import java.io.File;
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
    
    
    public abstract void browseInFileSystem(File current) throws Exception;
    
    public abstract void openInShell(String currentPath) throws Exception;
        
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
    
}
