/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

/**
 *
 * @author SessonaD
 */
public class PathFinder {
    
    
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
