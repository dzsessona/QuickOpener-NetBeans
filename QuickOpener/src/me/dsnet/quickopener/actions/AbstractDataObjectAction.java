package me.dsnet.quickopener.actions;

import javax.swing.AbstractAction;
import me.dsnet.quickopener.PathFinder;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.Utilities;

/**
 * Generic action which is context-aware to {@link DataObject}. It also supports
 * {@link DataObject} from Favorite nodes.
 *
 * @author markiewb
 */
public abstract class AbstractDataObjectAction extends AbstractAction {

    private final Lookup context;

    public AbstractDataObjectAction() {
        context = Utilities.actionsGlobalContext();
    }

    @Override
    public final boolean isEnabled() {
        return null != context.lookup(DataObject.class);
    }

    protected final DataObject getDataObject() {
        final DataObject dataObject = context.lookup(DataObject.class);

        return PathFinder.getRealDataObject(dataObject);
    }

}
