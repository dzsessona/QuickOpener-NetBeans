/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener.actions;

import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import me.dsnet.quickopener.prefs.PrefsUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author SessonaD
 */
public class RunCommand {

    private static boolean isNotBlank(String f) {
        return f != null && !f.isEmpty();
    }
    private final String _command;

    public RunCommand(String command) {
        this._command = command;
    }

    public boolean actionPerformed() {
        if (_command == null) {
            NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_COMMAND, NotifyDescriptor.WARNING_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return false;
        } else {
            final Map<String, String> createPlaceholders = createPlaceholders();
            String command = fillPlaceholders(_command, createPlaceholders);
            //Are all placeholders replaced? -> if not then show a message!
            boolean foundUnreplacedPlaceholder = false;
            for (String placeholder : createPlaceholders.keySet()) {
                if (command.contains(placeholder)) {
                    foundUnreplacedPlaceholder = true;
                    break;
                }
            }

            if (foundUnreplacedPlaceholder) {
                NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_DEFAULT_PARAMETERS, NotifyDescriptor.WARNING_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }
            try {
                String msg = QuickMessages.CONFIRM_COMMAND_PREFIX + command + QuickMessages.CONFIRM_COMMAND_SUFFIX;
                boolean nedToConfirm = Boolean.parseBoolean((PrefsUtil.load(null, "confirmationDialogue", "true")).getValue());
                NotifyDescriptor d = new NotifyDescriptor.Confirmation(msg, "Confirm", NotifyDescriptor.OK_CANCEL_OPTION);
                if (!nedToConfirm || (nedToConfirm && NotifyDescriptor.OK_OPTION == DialogDisplayer.getDefault().notify(d))) {
                    Runtime.getRuntime().exec(command);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                return false;
            }
        }
    }

    private Map<String, String> createPlaceholders() {
        String currentFile = PathFinder.getActivePath(null, false);
        String currentFolder = PathFinder.getActivePath(null, true);
        String relativeFile = PathFinder.getRelativeActivePath(null, false);
        String relativeFolder = PathFinder.getRelativeActivePath(null, true);
        Map<String, String> placeholders = new LinkedHashMap<String, String>();
        placeholders.put("${currentFile}", currentFile);
        placeholders.put("${currentFolder}", currentFolder);
        placeholders.put("${relativeFile}", relativeFile);
        placeholders.put("${relativeFolder}", relativeFolder);
        return placeholders;
    }

    private String fillPlaceholders(String command, Map<String, String> placeholders) {
        //Replace all placeholders
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String pattern = entry.getKey();
            String fileName = entry.getValue();
            /**
             * Replacement for
             * <pre>if(command.contains("${currentFile}") && isNotBlank(currentFile)) {command=command.replace("${currentFile}", currentFile);}
             * </pre>
             */
            if (command.contains(pattern) && isNotBlank(fileName)) {
                command = command.replace(pattern, fileName);
            }
        }
        return command;
    }

    public String getCommandWithReplacedPlaceholders() {
        final Map<String, String> placeholders = createPlaceholders();
        return fillPlaceholders(_command, placeholders);
    }
}
