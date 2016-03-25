package me.dsnet.quickopener.actions.layer;

import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import me.dsnet.quickopener.actions.RunCommand;
import static me.dsnet.quickopener.actions.layer.ActionRegistrationService.unescapeXMLCharacters;

/**
 * Action for running custom commands. The configuration is purely based on
 * custom attribute tags of the action registration in the layer.xml file.
 *
 * <pre>
 * &lt;file name="action1.instance"&gt;
 *     &lt;attr methodvalue="me.dsnet.quickopener.actions.layer.LayerXMLConfiguredCustomRunnerAction.create" name="instanceCreate"/&gt;
 *     &lt;attr name="imagePath" stringvalue="me/dsnet/quickopener/icons/run.png"/&gt;
 *     &lt;attr name="displayName" stringvalue="Notepad"/&gt;
 *     &lt;attr name="custom-command" stringvalue="notepad ${file}"/&gt;
 * &lt;/file&gt;
 * </pre>
 *
 * <p>
 * Based on
 * https://blogs.oracle.com/geertjan/entry/enabling_an_action_on_object,
 * https://blogs.oracle.com/geertjan/entry/superclass_sensitive_actions and
 * http://wiki.netbeans.org/DevFaqActionsAddAtRuntime
 * </p>
 *
 * @author markiewb
 */
public final class LayerXMLConfiguredCustomRunnerAction extends AbstractAction {

    private final RunCommand runCommand;

    /**
     *
     * @param map
     * @return
     */
    public static Action create(Map map) {
        final String command = unescapeXMLCharacters((String) map.get("custom-command"));
        final String displayName = unescapeXMLCharacters((String) map.get("displayName"));
        final String iconBase = (String) map.get("imagePath");
        return new LayerXMLConfiguredCustomRunnerAction(iconBase, displayName, command);
    }

    /**
     * Only to be instanciated by the layer.xml configured based on the
     * "instanceCreate"-value.
     *
     * @param iconBase
     * @param displayName
     * @param command
     */
    private LayerXMLConfiguredCustomRunnerAction(String iconBase, String displayName, final String command) {
        super(displayName);
        runCommand = new RunCommand(command);
        putValue("iconBase", iconBase);
        // enable action, only if all placeholders are replaced? -- not possible, because we have editor related properties, which are not propagated
        // setEnabled(runCommand.areAllPlaceHoldersReplaced());
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        runCommand.actionPerformed();
    }

}
