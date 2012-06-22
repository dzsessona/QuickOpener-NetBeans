/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener.prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import org.openide.util.NbPreferences;

/**
 *
 * @author SessonaD
 */
public class PrefsUtil {
    
    public static void store(String key, String val){
        NbPreferences.forModule(QuickOpenerPanel.class).put(key,val);
    }
    
    public static QuickOpenerProperty load(String prefix,String key, String defaultVal){
        return new QuickOpenerProperty((prefix==null)?key:key.replace(prefix, ""),NbPreferences.forModule(QuickOpenerPanel.class).get(key, defaultVal));
    }
    
    public static List<QuickOpenerProperty> getAllMatching(String prefix) throws BackingStoreException{
        List<QuickOpenerProperty> list = new ArrayList<QuickOpenerProperty> ();
        for(String key:NbPreferences.forModule(QuickOpenerPanel.class).keys()){
            if(key.startsWith(prefix)){
                String desc =key.replace(prefix, "");
                String val=NbPreferences.forModule(QuickOpenerPanel.class).get(key, "");
                list.add(new QuickOpenerProperty(desc,val));                
            }
        }
        return list;
    }
    
    
    
}
