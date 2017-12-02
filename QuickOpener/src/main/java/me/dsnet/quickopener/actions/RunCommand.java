/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener.actions;

import java.io.File;
import java.io.IOException;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import me.dsnet.quickopener.prefs.PrefsUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JEditorPane;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.Node;
import org.openide.text.NbDocument;
import org.openide.windows.TopComponent;

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
                NotifyDescriptor d = new NotifyDescriptor.Message(QuickMessages.NO_DEFAULT_PARAMETERS + " \nCommand was: " + command, NotifyDescriptor.WARNING_MESSAGE);
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
            } catch (IOException ex) {
                return false;
            }
        }
    }

    private Map<String, String> createPlaceholders() {
        String currentFile = PathFinder.getActivePath(null, false);
        String currentFolder = PathFinder.getActivePath(null, true);
        String relativeFile = PathFinder.getRelativeActivePath(null, false);
        String relativeFolder = PathFinder.getRelativeActivePath(null, true);
        String currentProjectFolder = PathFinder.getActiveProject();
        String mainProjectFolder = PathFinder.getMainProjectRootPath();
        Map<String, String> placeholders = new LinkedHashMap<>();

        if (null != currentFile && new File(currentFile).exists()) {
            File file = new File(currentFile);
            FileObject fo = FileUtil.toFileObject(FileUtil.normalizeFile(file));
            placeholders.put("${fileNameExt}", fo.getNameExt());
            placeholders.put("${fileName}", fo.getName());
            placeholders.put("${fileExt}", fo.getExt());
        } else {
            placeholders.put("${fileNameExt}", "");
            placeholders.put("${fileName}", "");
            placeholders.put("${fileExt}", "");
        }
        //${line}:${column} ${line0}:${column0} '${selectedText}' ${fileNameExt} ${fileName} ${fileExt} ${file} ${folder} ${relativeFile} ${relativeFolder} ${projectFolder} ${mainProjectFolder}
        placeholders.put("${file}", currentFile);
        placeholders.put("${folder}", currentFolder);
        placeholders.put("${relativeFile}", relativeFile);
        placeholders.put("${relativeFolder}", relativeFolder);
        placeholders.put("${projectFolder}", currentProjectFolder);
        placeholders.put("${mainProjectFolder}", mainProjectFolder);

        JTextComponent editor = getCurrentEditor();
        int caret = (null != editor) ? editor.getCaretPosition() : -1;
        StyledDocument sdocument = (null != editor && (editor.getDocument() instanceof StyledDocument)) ? (StyledDocument) editor.getDocument()
                : null;
        if (null != sdocument) {

            int line0 = NbDocument.findLineNumber(sdocument, caret);
            int column0 = NbDocument.findLineColumn(sdocument, caret);
            String selectedText = (null != editor) ? editor.getSelectedText(): "";
            placeholders.put("${line0}", "" + line0);
            placeholders.put("${line}", "" + (line0 + 1));
            placeholders.put("${column0}", "" + column0);
            placeholders.put("${column}", "" + (column0 + 1));
            placeholders.put("${selectedText}", null != selectedText ? selectedText : "");
        } else {
            // add defaults for editor related placeholders
            placeholders.put("${line0}", "");
            placeholders.put("${line}", "");
            placeholders.put("${column0}", "");
            placeholders.put("${column}", "");
            placeholders.put("${selectedText}", "");
        }

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

    private JTextComponent getCurrentEditor() {
        Node[] arr = TopComponent.getRegistry().getCurrentNodes();
        if (null == arr) {
            return null;
        }
        for (Node arr1 : arr) {
            EditorCookie ec = arr1.getLookup().lookup(EditorCookie.class);
            if (ec != null) {
                JEditorPane[] panes = ec.getOpenedPanes();
                if (panes != null && panes.length > 0) {
                    return panes[0];
                }
            }
        }
        return null;
    }

}
