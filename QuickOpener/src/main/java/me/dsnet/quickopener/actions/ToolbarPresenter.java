/**
 *
 * @author SessonaD
 */
package me.dsnet.quickopener.actions;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.openide.awt.DropDownButtonFactory;
import org.openide.filesystems.FileUtil;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.util.actions.Presenter;

@Messages("CTL_CustomCommand=Launch custom command")
public final class ToolbarPresenter implements Presenter.Toolbar, PopupMenuListener {

    JPopupMenu popup = new JPopupMenu();

    public ToolbarPresenter() {
    }

    @Override
    public Component getToolbarPresenter() {

        final ImageIcon run16 = ImageUtilities.loadImageIcon("me/dsnet/quickopener/icons/run.png", false);
        final ImageIcon run32 = ImageUtilities.loadImageIcon("me/dsnet/quickopener/icons/run24.png", false);

        updatePopupMenu();
        popup.addPopupMenuListener(this);

        //button
        final JButton dropDownButton = DropDownButtonFactory.createDropDownButton(run16, popup);
        dropDownButton.addPropertyChangeListener("PreferredIconSize", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                boolean useSmallIcon = true;
                final Object prop = evt.getNewValue();
                if (prop instanceof Integer) {
                    if (((Integer) prop).intValue() == 24) {
                        useSmallIcon = false;
                    }
                }
                if (useSmallIcon) {
                    dropDownButton.setIcon(run16);
                } else {
                    dropDownButton.setIcon(run32);
                }
            }
        });
        return dropDownButton;
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        updatePopupMenu();
    }

    private JPopupMenu toPopup(List<? extends Action> actions) {
        JPopupMenu result = new JPopupMenu();
        if (!actions.isEmpty()) {
            Action[] toArray = actions.toArray(new Action[actions.size()]);
            result = Utilities.actionsToPopup(toArray, Utilities.actionsGlobalContext());
        }
        return result;
    }

    private void updatePopupMenu() {
        List<? extends Action> actions = Utilities.actionsForPath(me.dsnet.quickopener.actions.layer.ActionRegistrationService.MENU);

        JPopupMenu newPopup = toPopup(actions);
        popup.removeAll();
        for (Component aa : newPopup.getComponents()) {
            popup.add(aa);
        }
    }

}
