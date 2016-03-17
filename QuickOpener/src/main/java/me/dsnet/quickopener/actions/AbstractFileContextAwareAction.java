package me.dsnet.quickopener.actions;

import java.io.File;
import me.dsnet.quickopener.PathFinder;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.NodeAction;

public abstract class AbstractFileContextAwareAction extends NodeAction {

    private File file;

    protected File getFile() {
        return file;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public abstract String getName();

    @Override
    protected boolean enable(Node[] activatedNodes) {
        file = null;
        if (null != activatedNodes && activatedNodes.length == 1) {

            file = PathFinder.getActiveFile(activatedNodes[0].getLookup().lookup(DataObject.class), false);
        }
        return null != file;
    }

    @Override
    protected abstract String iconResource();

    @Override
    protected abstract void performAction(Node[] activatedNodes);

}
