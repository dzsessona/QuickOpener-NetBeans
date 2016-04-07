package me.dsnet.quickopener.actions.layer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.Action;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * Based on from http://wiki.netbeans.org/DevFaqActionsAddAtRuntime and
 * https://github.com/Tateology/java-corpus/blob/master/netbeans/maven/src/org/netbeans/modules/maven/actions/RunCustomMavenAction.java#L160
 */
public class ActionRegistrationService {

    public static final String ACTIONS = "Actions/";
    private static final String MENU = "Menu/Tools/External Tools";
    private static final String SHORTCUTS = "Shortcuts";
    private static final String TOOLBARS = "Toolbars/";

    /**
     * Registers an action with the platform along with optional shortcuts and
     * menu items.
     * <pre>
     * &lt;file name="action1.instance"&gt;
     *     &lt;attr methodvalue="me.dsnet.quickopener.actions.layer.LayerXMLConfiguredCustomRunnerAction.create" name="instanceCreate"/&gt;
     *     &lt;attr name="imagePath" stringvalue="me/dsnet/quickopener/icons/run.png"/&gt;
     *     &lt;attr name="displayName" stringvalue="Notepad"/&gt;
     *     &lt;attr name="custom-command" stringvalue="notepad ${file}"/&gt;
     * &lt;/file&gt;
     * </pre>
     *
     *
     * @param name Display name of the action.
     * @param category Category in the Keymap tool.
     * @param menuPath Menu location starting with "Menu", like "Menu/File"
     * @param action an action object to attach to the action entry.
     * @throws IOException
     */
    public static void registerActionAsMenuAndToolbar(String name, String category) {
        try {
            String originalFile = String.format("%s/%s.instance", ACTIONS + category, name);
            registerActionForMenu(originalFile, category, name);
            registerActionForToolbar(originalFile, category, name);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    public static void registerActionAsMenu(String name, String originalFile, int position) {
        try {
            FileObject obj = getOrCreateFileObject(getFolderAt(MENU), name, "shadow");
            obj.setAttribute("originalFile", originalFile);
            obj.setAttribute("position", position);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public static void unregisterAction(String name, String category) {
        try {
            String originalFile = String.format("%s/%s.instance", ACTIONS + category, name);
            
            deleteFileObject(getFolderAt(ACTIONS + category), name, "instance");
            deleteFileObject(getFolderAt(TOOLBARS + category), name, "shadow");
            deleteFileObject(getFolderAt(MENU + category), name, "shadow");

            // iterate over all shortcuts, remove the shortcut which links to originalFileName
            // FIXME does it really work?
            FileObject folderAt = getFolderAt(SHORTCUTS);
            List<FileObject> forRemoval = new ArrayList<FileObject>();
            
            for (FileObject fo : folderAt.getChildren()) {
                if (originalFile.equals(fo.getAttribute("originalFile"))) {
                    //found shortcut by its reference to the original action
                    forRemoval.add(fo);
                }
            }
            for (FileObject fo : forRemoval) {
                fo.delete();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    public static void unregisterActions(String category) {
        try {
            getFolderAt(ACTIONS + category).delete();
            getFolderAt(MENU).delete();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public static void registerAction(final String category, String id, String displayName, String command, String imagePath, String fqnFactoryMethodName) throws IOException {
        //NOTE cannot create 'name="instanceCreate" methodvalue="xxx"' in code, so using a template
        FileObject template = FileUtil.getConfigFile("QuickOpener/actionTemplate.instance");
        FileObject obj = template.copy(getFolderAt(ACTIONS + category), displayName, "instance");

        obj.setAttribute("imagePath", imagePath);
        obj.setAttribute("displayName", escapeXMLCharacters(displayName));
        obj.setAttribute("custom-command", escapeXMLCharacters(command));
    }

    public static void registerAction(String id, final String category, String displayName, String command) {
        try {
            ActionRegistrationService.registerAction(category, id, displayName, command, "me/dsnet/quickopener/icons/run.png", "me.dsnet.quickopener.actions.layer.LayerXMLConfiguredCustomRunnerAction.create");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static void deleteFileObject(FileObject folder, String name, final String ext) throws IOException {
        FileObject fo = folder.getFileObject(name, ext);
        if (fo != null) {
            fo.delete();
        }
    }

    /**
     * Replace xml-characters like &lt with &amp;lt;
     *
     * @param text
     * @return
     */
    private static String escapeXMLCharacters(String text) {
        //TODO replace xml-characters like < with &lt;
        return text;
    }

    /**
     * Replace xml-characters like &amp;lt; with &lt
     *
     * @param text
     * @return
     */
    public static String unescapeXMLCharacters(String text) {
        //TODO replace xml-characters like &lt; with <
        return text;
    }

    private static FileObject getFolderAt(String inputPath) throws IOException {
        final String[] split = inputPath.split("/");
        if (null == split || split.length == 0) {
            return null;
        }
        List<String> parts = Arrays.asList(split);
        FileObject existing = FileUtil.getConfigFile(inputPath);
        if (existing != null) {
            return existing;
        }

        FileObject base = FileUtil.getConfigFile(parts.get(0));
        if (base == null) {
            return null;
        }

        for (int i = 1; i < parts.size(); i++) {
            String path = join("/", parts.subList(0, i + 1));
            FileObject next = FileUtil.getConfigFile(path);
            if (next == null) {
                next = base.createFolder(parts.get(i));
            }
            base = next;
        }

        return FileUtil.getConfigFile(inputPath);
    }

    private static FileObject getOrCreateFileObject(FileObject folder, String name, final String ext) throws IOException {
        FileObject fo = folder.getFileObject(name, ext);
        if (fo == null) {
            fo = folder.createData(name, ext);
        }
        FileObject configFile = FileUtil.getConfigFile(String.format("%s/%s.%s", folder.getPath(), name, ext));
        return configFile;
//        return fo;
    }

    private static String join(String separator, List<String> list) {
        StringBuilder sb = new StringBuilder();
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            String text = list.get(i);
            sb.append(text);
            if (i != (size - 1)) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    private static void registerActionForMenu(String originalFile, String category, String name) throws IOException {
        FileObject obj = getOrCreateFileObject(getFolderAt(MENU + category), name, "shadow");
        obj.setAttribute("originalFile", originalFile);
    }

    private static void registerActionForToolbar(String originalFile, String category, String name) throws IOException {

        FileObject obj = getOrCreateFileObject(getFolderAt(TOOLBARS + category), name, "shadow");
        obj.setAttribute("originalFile", originalFile);

    }

}
