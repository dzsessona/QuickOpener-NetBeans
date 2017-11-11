/*
 * Copyright (C) 2017 Diego Zambelli Sessona (diego.sessona@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
