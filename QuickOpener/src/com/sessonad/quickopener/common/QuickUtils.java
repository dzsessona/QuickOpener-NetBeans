package com.sessonad.quickopener.common;

import java.awt.Desktop;
import java.io.File;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.nodes.Node.Property;
import org.openide.nodes.Node.PropertySet;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author SessonaD
 */
public class QuickUtils {
    
    private static Desktop desktop;
    
    static{
        initializeDesktop();
    }
    
    public static File getCurrentFile() {
        TopComponent topActive = TopComponent.getRegistry().getActivated();
        if (topActive.getName().equals("Projects")) {            
            return getFileFromProjects(topActive.getActivatedNodes()[0]);
        } else if (topActive.getName().equals("Files")) {
            return getFileFromFiles(topActive.getActivatedNodes()[0]);
        } else if (WindowManager.getDefault().isOpenedEditorTopComponent(topActive)) {
            return getFileFromEditor(topActive);
        } else {
            return getMainProjectRoot();
        }
    }
    
    private static File getFileFromProjects(Node node){        
        if (node instanceof FilterNode) {
            return getfromFilterNode((FilterNode)node);
        }else{
            File toReturn=null;
            return toReturn;
        }        
    }
    
    private static File getFileFromFiles(Node node){
        if (node instanceof FilterNode) {
            return getfromFilterNode((FilterNode)node);
        }else{            
            File toReturn=null;
            return toReturn;
        } 
        
            
    }
    
    private static File getfromFilterNode(FilterNode node) {
        File s = null;
        try {
            PropertySet[] props = node.getPropertySets();
            outer:
            for (PropertySet p : props) {
                for (Property pr : p.getProperties()) {
                    if (pr.getName().equals("files")) {
                        s = new File(((String[]) pr.getValue())[0]);
                        break outer;
                    } else if (pr.getName().equals("ROOT_PATH")) {
                        s = new File((String) pr.getValue());
                        break outer;
                    } else if (pr.getName().equals("sortMode")) {
                        return getFileFromProjects(node.getChildren().getNodeAt(0));
                    }
                }
            }
        } catch (Exception ex) {
        }
        return s;
    }
    
    private static File getFileFromEditor(TopComponent active){
        String path = active.getToolTipText();
        if(path!=null && !path.isEmpty())
            return new File(path.split(" ")[0]);
        else
            return null;
    }
    
    public static File getMainProjectRoot(){
        Project project = OpenProjects.getDefault().getMainProject();
        FileObject fo = project.getProjectDirectory();
        return new File(fo.getPath());
    }
    
    public static File getParentFolder(File f){
        if (!f.isDirectory()){
            return getParentFolder(f.getParentFile());
        }else{
            return f;
        }        
    }
        
    static void initializeDesktop(){
        if(Desktop.isDesktopSupported()){
            desktop = Desktop.getDesktop();
        }else{
            desktop = null;
        }
    }
    
    public static Desktop getDesktop(){
        if (Desktop.isDesktopSupported() && desktop !=null){
            return desktop;
        }else if(!Desktop.isDesktopSupported()){
            return null;
        }else{
            initializeDesktop();
            return desktop;
        }
    }
    
    public static String getCommandOS(){
        String command=null;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")){
            command = "cmd /c start cd ";
        }else if (os.toLowerCase().contains("windows")){
            command = "gnome-terminal --working-directory=";
        }
        return command;
    }
    
}
