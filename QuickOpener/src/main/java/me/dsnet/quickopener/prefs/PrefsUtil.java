/*
 * Copyright (C) 2017 Diego Zambelli Sessona (diego.sessona@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.dsnet.quickopener.prefs;

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
    
    public static void removeSingleProperty(String key){
        NbPreferences.forModule(QuickOpenerPanel.class).remove(key);
    }
    
    public static void remove(String key) throws BackingStoreException{
        List<QuickOpenerProperty> commands = getAllMatching("command");
        List<QuickOpenerProperty> folders = getAllMatching("folder");
        NbPreferences.forModule(QuickOpenerPanel.class).clear();
        for(QuickOpenerProperty p:commands){
            String desc="command" + p.getDescription().replaceAll(" ", "_");
            if(! desc.equals(key))
            store(desc,p.getValue());
        } 
        for(QuickOpenerProperty p:folders){
            String desc="folder" + p.getDescription().replaceAll(" ", "_");
            if(! desc.equals(key))
            store(desc,p.getValue());
        }
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
        List<QuickOpenerProperty> list = new ArrayList<> ();
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
