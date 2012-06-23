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
import org.openide.nodes.Node;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

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
    
    public static String getMyNetbeansConfPath(){        
        try {            
            FileObject root = FileUtil.getConfigRoot();
            return FileUtil.toFile(root).getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getFileFromSelectedNode(){
        TopComponent topActive = TopComponent.getRegistry().getActivated();
        Node[]nodes  = topActive.getActivatedNodes();
        if(nodes.length==1){            
            DataObject dataObj=nodes[0].getLookup().lookup(DataObject.class); 
            return (dataObj!=null)?getPathFromDataObject(dataObj):null;
        }else{
            return null;
        }
    }
    
    public static File getMainProjectRoot(){
        try {
            Project project = OpenProjects.getDefault().getMainProject();
            FileObject root = project.getProjectDirectory();
            return FileUtil.toFile(root);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getMainProjectRootPath(){
        try {
            return getMainProjectRoot().getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
    
}
