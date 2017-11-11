/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener;

import java.io.File; 
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject; 
import org.openide.loaders.DataShadow;
import org.openide.loaders.MultiDataObject;
import org.openide.nodes.Node;
import org.openide.windows.TopComponent;



/**
 *
 * @author SessonaD
 */
public class PathFinder {
    
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
    
    public static String getMyNetbeansConfPath(){        
        try {            
            FileObject root = FileUtil.getConfigRoot();
            return FileUtil.toFile(root).getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static File getActiveFile(DataObject dataObj,boolean isFolder){
        try{
            if(dataObj == null){
                return getActiveFileFromSelectedNode(isFolder);
            }else{
                try{
                    return getActiveFileFromDataObject(dataObj,isFolder);
                }catch(NullPointerException npex){
                    return getActiveFileFromSelectedNode(isFolder);
                }                
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getActivePath(DataObject dataObj, boolean isFolder) {
        File found = getActiveFile(dataObj, isFolder);
        return getActivePath(found);
    }

    public static String getActiveProject() {
        File found = getActiveFile(null, false);
        if (null != found && found.exists()) {
            FileObject fo = FileUtil.toFileObject(FileUtil.normalizeFile(found));

            Project owner = FileOwnerQuery.getOwner(fo);
            if (null != owner) {
                File toFile = FileUtil.toFile(owner.getProjectDirectory());
                return toFile.getAbsolutePath();
            }
        }
        return null;
    }
    
    public static String getRelativeActivePath(DataObject dataObj,boolean isFolder){
        try{
            String filePath = getActivePath(dataObj,isFolder);
            String rootPath = getMainProjectRootPath();
            if((filePath != null && !filePath.isEmpty())&&(rootPath != null && !rootPath.isEmpty())&&(filePath.contains(rootPath))){
                return (filePath.substring(rootPath.length() + 1));                
            }else{
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }
    
    private static File getActiveFileFromDataObject(DataObject dataObj,boolean isFolder){        
        if(dataObj == null){ return null;}
        else{
            try{  
                dataObj = getDataObjectAndResolveShadows(dataObj);
                File toReturn = getFileFromDataObject(dataObj,isFolder);
                if (toReturn != null && toReturn.exists()) {
                    return toReturn;
                } else {
                    if ((dataObj instanceof DataFolder)) {
                        DataFolder df = (DataFolder) dataObj;
                        FileObject fobj = df.getPrimaryFile();
                        File found = getFileFromFileObject(fobj);
                        if (found == null) {
                            return null;
                        } else {
                            return isFolder ? found.getParentFile() : found;
                        }
                    } else if (dataObj instanceof MultiDataObject) {
                        MultiDataObject mdo = (MultiDataObject) dataObj;
                        FileObject fobj = mdo.getPrimaryFile();
                        File found = getFileFromFileObject(fobj);
                        if (found == null) {
                            return null;
                        } else {
                            return isFolder ? found.getParentFile() : found;
                        }
                    }
                }                           
            }catch(Exception e){
                return null;
            }
            return null;
        }
    }

    public static String getActivePath(File file) {
        return ((file != null) && (file.exists())) ? file.getAbsolutePath() : null;
    }
    
    //TODO check deprecated api
    private static File getFileFromFileObject(FileObject fobj){
        try{
            if(fobj == null){
                return null;
            }else if(fobj.isRoot()){
                    FileSystem fs =fobj.getFileSystem();
                    String path = fs.getSystemName();
                    File f = new File(path);                    
                    return f;
            }else{
                return getFileFromFileObject(fobj.getParent());
            }
        }catch(FileStateInvalidException e){
            //e.printStackTrace();
            return null;
        }
        
    }
    
    private static File getFileFromDataObject(DataObject dataObj, boolean isFolder) {
        try {
            File current = FileUtil.toFile(dataObj.getPrimaryFile());            
            if (!isFolder) {
                return current;
            } else {
                return (current.isDirectory()) ? current : current.getParentFile();
            }
        } catch (Exception e) {
           // e.printStackTrace();
            return null;
        }
    }
    
    private static File getActiveFileFromSelectedNode(boolean isFolder) {
        try {
            TopComponent topActive = TopComponent.getRegistry().getActivated();
            Node[] nodes = topActive.getActivatedNodes();
            if (nodes.length == 1) {
                final Node node = nodes[0];
                final DataObject dataObject = node.getLookup().lookup(DataObject.class);

                File result;
                // a) try to get File
                result = node.getLookup().lookup(File.class);
                // b) old fallback
                if (null == result) {
                    result = getActiveFileFromDataObject(dataObject, isFolder);
                }
                return result;

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Support favorite nodes too
     * https://github.com/dzsessona/QuickOpener-NetBeans/issues/30
     *
     * @param dataObject
     * @return
     */
    private static DataObject getDataObjectAndResolveShadows(DataObject dataObject) {
        if (dataObject instanceof DataShadow) {
            DataShadow dataShadow = (DataShadow) dataObject;
            return dataShadow.getOriginal();
        }
        return dataObject;
    }
    
}
