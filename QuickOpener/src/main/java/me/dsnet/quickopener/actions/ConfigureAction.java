package me.dsnet.quickopener.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "ExternalToolsHardCodedActions",
        id = "me.dsnet.quickopener.actions.ConfigureAction"
)
@ActionRegistration(
        displayName = "#CTL_ConfigureAction"
)
//@ActionReference(path = "Actions/ExternalToolsHardCodedActions", position = MAX_VALUE-1, separatorBefore = MAX_VALUE-2)
@Messages("CTL_ConfigureAction=Configure...")
public final class ConfigureAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        OptionsDisplayer.getDefault().open("Advanced" + "/QuickOpener", true);
    }
}
