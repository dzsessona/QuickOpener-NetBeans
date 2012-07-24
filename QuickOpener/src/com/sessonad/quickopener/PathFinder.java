/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener;

import java.io.File; 
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.windows.TopComponent;

/**
 *
 * @author SessonaD
 */
public class PathFinder {
    
    
    public static String getPathFromDataObject(DataObject dataObj,boolean isFolder){        
        try{
            return getFileFromDataObject(dataObj,isFolder).getAbsolutePath();
        }catch(Exception e){
            return null;
        }
    }
    
    public static File getFileFromDataObject(DataObject dataObj, boolean isFolder) {
        try {
            File current = FileUtil.toFile(dataObj.getPrimaryFile());
            if (!isFolder) {
                return current;
            } else {
                return (current.isDirectory()) ? current : current.getParentFile();
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getMyNetbeansConfPath(){        
        try {            
            FileObject root = FileUtil.getConfigRoot();
            return FileUtil.toFile(root).getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getFileFromSelectedNode(boolean isFolder) {
        try {
            TopComponent topActive = TopComponent.getRegistry().getActivated();
            Node[] nodes = topActive.getActivatedNodes();
            if (nodes.length == 1) {
                DataObject dataObj = nodes[0].getLookup().lookup(DataObject.class);
                return (dataObj != null) ? getPathFromDataObject(dataObj, isFolder) : null;
            } else {
                return null;
            }
        } catch (Exception e) {
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
