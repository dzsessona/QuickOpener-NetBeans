/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessonad.quickopener;

/**
 *
 * @author SessonaD
 */
public enum QuickMessages {
    
    NO_FILE_IN_SELECTION ("There are no files associated with the current selection."),
    CONFIRM_COMMAND_PREFIX ("You are about to launch the following command:\n\n"),
    CONFIRM_COMMAND_SUFFIX ("\n\nAre you sure?"),
    DESCRIPTION_MANDATORY ("A description is mandatory."),
    FOLDER_INVALID ("The folder specified is not valid or does not exists."),
    FOLDER_ADDED ("Folder added as favorite."),
    SEPARATOR_NULL ("Separator cannot be empty."),
    CUSTOM_SHELL ("Custom shell cannot be empty."),
    NO_DEFAULT_COMMAND ("No default command set. Set it in tools -> options -> quickopener -> commands"),
    NO_DEFAULT_PARAMETERS ("No files in the selection for this command."),
    DEFAULT_COMMAND_PARAMETERS ("A default command cannot contain parameters."),
    NOT_IN_FILE_SYSTEM ("The file does not exists in the file system.");
    
    private String message;

    private QuickMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message ;
    }
    
}
