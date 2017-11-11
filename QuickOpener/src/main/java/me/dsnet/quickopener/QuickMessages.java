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
package me.dsnet.quickopener;

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
    CUSTOM_FILEMANAGER ("Custom file manager cannot be empty."),
    NO_COMMAND ("No command given."),
    NO_DEFAULT_PARAMETERS ("Some placeholders could not be replaced."),
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
