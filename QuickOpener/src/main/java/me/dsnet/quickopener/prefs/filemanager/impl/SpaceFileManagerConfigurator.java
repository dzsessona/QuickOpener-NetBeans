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
package me.dsnet.quickopener.prefs.filemanager.impl;

import com.sessonad.oscommands.detector.OSDetector;
import me.dsnet.quickopener.prefs.filemanager.IFileManagerConfigurator;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;

import javax.swing.*;
import java.io.File;

/**
 *
 * @author javatlacati
 */
@NbBundle.Messages({
    "SPACEFM_label=SpaceFM",
    "SPACEFM_ApproveTextBtn=Choose",
    "SPACEFM_title=Choose the installation folder of '%s'",
     "SPACEFM_exefile=%s%/usr/bin/spacefm",
     "SPACEFM_exefilenotfound=Selected folder '%s' does not contain a caja installation.",
     "SPACEFM_dir=spacefm",
     "SPACEFM_command=%s%/usr/bin/spacefm",
})
public class SpaceFileManagerConfigurator implements IFileManagerConfigurator{

    @Override 
    public String configure() { 
        File dir;
        dir = new FileChooserBuilder(Bundle.SPACEFM_dir())
                .setTitle(String.format(Bundle.SPACEFM_title(), getLabel()))
                .setDirectoriesOnly(true)
                .setSelectionApprover(new FileChooserBuilder.SelectionApprover() {
 
                    @Override
                    public boolean approve(File[] files) {
                        if (null != files && files.length == 1) {
                            String absolutePath = FileUtil.normalizeFile(files[0]).getAbsolutePath();
                            String exeFile = String.format(Bundle.SPACEFM_exefile(), absolutePath, File.separator);
                            final boolean exists = new File(exeFile).exists();
                            if (!exists) {
                                JOptionPane.showMessageDialog(null, String.format(Bundle.SPACEFM_exefilenotfound(), absolutePath));
                            }
                            return exists;
                        }
                        return false;
                    }
                })
                .setApproveText(Bundle.SPACEFM_ApproveTextBtn()).showOpenDialog();

        if (dir != null) {
            return String.format(Bundle.SPACEFM_command(), FileUtil.normalizeFile(dir).getAbsolutePath(), File.separator);
        } else {
            return null;
        }
    }

    @Override
    public String getLabel() {
        return Bundle.SPACEFM_label();
    }

    @Override
    public boolean isAvailable() {
        return OSDetector.detectOS().isLinux();
    }
    
}
