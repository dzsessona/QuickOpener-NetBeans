package me.dsnet.quickopener.actions.layer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.Action;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * Taken from http://wiki.netbeans.org/DevFaqActionsAddAtRuntime
 */
public class ActionRegistrationService {

    /**
     * Registers an action with the platform along with optional shortcuts and
     * menu items.
     *
     * @param name Display name of the action.
     * @param category Category in the Keymap tool.
     * @param shortcut Default shortcut, use an empty string or null for none.
     * @param menuPath Menu location starting with "Menu", like "Menu/File"
     * @param action an action object to attach to the action entry.
     * @throws IOException
     */
    public static void registerAction(String name, String category, String shortcut, String menuPath, Action action) throws IOException {
        /////////////////////// 
        // Add/Update Action // 
        /////////////////////// 
        String originalFile = "Actions/" + category + "/" + name + ".instance";
        FileObject in = getFolderAt("Actions/" + category);
        FileObject obj = in.getFileObject(name, "instance");
        if (obj == null) {
            obj = in.createData(name, "instance");
        }
        action.putValue(Action.NAME, name);
        obj.setAttribute("instanceCreate", action);
        obj.setAttribute("instanceClass", action.getClass().getName());

        ///////////////////// 
        // Add/Update Menu // 
        ///////////////////// 
        in = getFolderAt(menuPath);
        obj = in.getFileObject(name, "shadow");
        // Create if missing. 
        if (obj == null) {
            obj = in.createData(name, "shadow");
            obj.setAttribute("originalFile", originalFile);
        }

        ///////////////////////// 
        // Add/Update Shortcut // 
        ///////////////////////// 
        in = getFolderAt("Shortcuts");
        obj = in.getFileObject(shortcut, "shadow");
        if (obj == null) {
            obj = in.createData(shortcut, "shadow");
            obj.setAttribute("originalFile", originalFile);
        }
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
}
