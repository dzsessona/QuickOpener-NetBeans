package me.dsnet.quickopener.prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author SessonaD
 */
public class PrefsUtil {
    
    public static void store(String key, String value) {

        final Preferences pref = NbPreferences.forModule(QuickOpenerPanel.class);
        pref.put(key, value);
    }
    
    public static void removeSingleProperty(String key){
        NbPreferences.forModule(QuickOpenerPanel.class).remove(key);
    }
    
    public static void removeAll(String prefix) throws BackingStoreException{
        List<QuickOpenerProperty> commands = getAllMatching("command");
        List<QuickOpenerProperty> folders = getAllMatching("folder");
        NbPreferences.forModule(QuickOpenerPanel.class).clear();
        if (prefix.equals("command")) {
            for (QuickOpenerProperty p : folders) {
                String desc = "folder" + p.getDescription().replaceAll(" ", "_");
                store(desc, p.getValue());
            }
        } else if (prefix.equals("folder")) {
            for (QuickOpenerProperty p : commands) {
                String desc = "command" + p.getDescription().replaceAll(" ", "_");
                store(desc, p.getValue());
            }
        }
    }
    
    public static QuickOpenerProperty load(String prefix,String key, String defaultVal){
        String desc =(prefix==null)?key:key.replace(prefix, "");
        desc =desc.replaceAll("_", " ");
        return new QuickOpenerProperty(desc,NbPreferences.forModule(QuickOpenerPanel.class).get(key, defaultVal));
    }
    
    public static List<QuickOpenerProperty> getAllMatching(String prefix) throws BackingStoreException{
        List<QuickOpenerProperty> list = new ArrayList<QuickOpenerProperty> ();
        for(String key:NbPreferences.forModule(QuickOpenerPanel.class).keys()){
            if(key.startsWith(prefix)){
                String desc =key.replace(prefix, "");
                desc =desc.replaceAll("_", " ");
                String val=NbPreferences.forModule(QuickOpenerPanel.class).get(key, "");
                list.add(new QuickOpenerProperty(desc,val));                
            }
        }
        return list;
    }
    
    
    
}
