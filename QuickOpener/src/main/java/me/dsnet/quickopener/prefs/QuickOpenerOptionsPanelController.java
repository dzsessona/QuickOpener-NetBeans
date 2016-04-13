/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener.prefs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.SubRegistration(location = "Advanced",
        id = "QuickOpener",
        displayName = "#AdvancedOption_DisplayName_QuickOpener",
        keywords = "#AdvancedOption_Keywords_QuickOpener",
        keywordsCategory = "Advanced/QuickOpener")
@org.openide.util.NbBundle.Messages({"AdvancedOption_DisplayName_QuickOpener=QuickOpener", "AdvancedOption_Keywords_QuickOpener=quickopener"})
public final class QuickOpenerOptionsPanelController extends OptionsPanelController {

    private QuickOpenerPanel panel;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private AtomicBoolean changed=new AtomicBoolean(false);

    public void update() {
        getPanel().load();
        changed.set(false);
    }

    public void applyChanges() {
        getPanel().store();
        changed.set(false);
    }

    public void cancel() {
        // need not do anything special, if no changes have been persisted yet
    }

    public boolean isValid() {
        return getPanel().valid();
    }

    public boolean isChanged() {
        return changed.get();
    }

    public HelpCtx getHelpCtx() {
        return null; // new HelpCtx("...ID") if you have a help set
    }

    public JComponent getComponent(Lookup masterLookup) {
        return getPanel();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    private QuickOpenerPanel getPanel() {
        if (panel == null) {
            panel = new QuickOpenerPanel(this);
        }
        return panel;
    }

    void changed() {
        if (!changed.get()) {
            changed.set(true);
            pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
//        pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
    }
}
