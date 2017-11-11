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
package com.sessonad.oscommands.detector;

/**
 *
 * @author SessonaD
 */
public class OSDetector {
    
    public static OperatingSystem detectOS(){
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("windows")){
            return OperatingSystem.WINDOWS;
        }else if(osName.contains("mac os")){
            return OperatingSystem.MAC_OS;
        }else if (osName.contains("linux")){
            return detectLinuxGUI();
        }else{
            return OperatingSystem.UNKNOWN;
        }
    }
    
    private static OperatingSystem detectLinuxGUI() {
        try {
            if (checkProcessNames("gnome-session", "gnome-session-binary")) {
                return OperatingSystem.LINUX_GNOME;
            }else if (checkProcessNames("ksmserver", "kdeinit4")) {
                return OperatingSystem.LINUX_KDE;
            }else if (checkProcessNames("xfce4-session", "xfce-mcs-manage")) {
                return OperatingSystem.LINUX_XFCE;
            }else if (checkProcessNames("lxsession")) {
                return OperatingSystem.LINUX_LXDE;
            }else{
                return OperatingSystem.LINUX_UNKNOWN;
            }
        } catch (Exception e) {
            return OperatingSystem.LINUX_UNKNOWN;
        }
    }
        
    private static boolean checkProcessNames(String... processNames) throws Exception {
        for (String processName : processNames) {
            if (executePidOfCommand(processName)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean executePidOfCommand(String processName) throws Exception {
        Process process = Runtime.getRuntime().exec(new String[]{"pidof", processName});
        return process.waitFor() == 0;
    }
    
}
