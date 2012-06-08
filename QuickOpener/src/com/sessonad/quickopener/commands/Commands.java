package com.sessonad.quickopener.commands;

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
    

    abstract String getShellCommand();
    
    public abstract void browseInFileSystem(File current) throws Exception;
    
    public abstract void openInShell(String currentPath) throws Exception;
        
    @SuppressWarnings("unchecked")
    public static <T extends Commands> T getPlatform(){
        if (platform == null)initializePlatform();
        return (T)platform;
    }
    
    static void initializePlatform(){
        String osName = System.getProperty("os.name").toLowerCase();
        if      (osName.contains("windows"))platform = new WindowsCommands();
        else if (osName.contains("linux"))  platform = new LinuxGnomeCommands();
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
