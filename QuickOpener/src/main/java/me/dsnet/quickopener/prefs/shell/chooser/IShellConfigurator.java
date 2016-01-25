/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener.prefs.shell.chooser;

/**
 * Strategy the configuration of a custom shell
 *
 * @author markiewb
 */
public interface IShellConfigurator {

    /**
     * Starts the configurator. In most cases this will open a set up dialog.
     *
     * @return shell command
     */
    String configure();

    /**
     *
     * @return description for showing in the UI
     */
    String getLabel();

    /**
     * Allows a configurator to be available per OS-platform/installed program.
     *
     * @return true, if this configurator is available
     */
    boolean isAvailable();

}
