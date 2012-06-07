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
    
       
    private static String osName;
    private static Desktop desktop;
    public enum Actions{BROWSE,TERMINAL} 
    
    static{
        initializeDesktop();
        getOSName();
    }
    
    static void initializeDesktop(){
        desktop = (Desktop.isDesktopSupported())?Desktop.getDesktop():null;
    }
    
    static void getOSName(){
        osName = System.getProperty("os.name").toLowerCase();
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
    
    public static String getCommandOS(QuickUtils.Actions action){
        String command=null;
        if(action == QuickUtils.Actions.TERMINAL){
            if (osName.contains("windows")){
                command = "cmd /c start cd ";
            }else if (osName.contains("linux")){
                command = "gnome-terminal --working-directory=";
            }
        }else if(action == QuickUtils.Actions.BROWSE){
            if (osName.contains("linux")){
                command = "nautilus ";
            }
        }        
        return command;
    }
    
}
