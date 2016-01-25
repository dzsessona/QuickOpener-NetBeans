/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener.prefs.shell.chooser.impl;

import java.io.File;
import javax.swing.JOptionPane;
import me.dsnet.quickopener.prefs.shell.chooser.IShellConfigurator;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/**
 *
 * @author markiewb
 */
@NbBundle.Messages({
    "GITBASH_dir=git-bash",
    "GITBASH_ApproveTextBtn=Choose",
    "GITBASH_title=Choose the installation folder of '%s'",
    "GITBASH_exefile=%s%sbin\\sh.exe",
    "GITBASE_exefilenotfound=Selected folder '%s' does not contain a msysgit installation.",
    "GITBASE_command=%s%sbin\\sh.exe --login -i",
    "GITBASE_label=Git BASH for Windows/msysgit"
})
public class GitBashConfigurator implements IShellConfigurator {

    @Override 
    public String configure() { 
        File dir;
        dir = new FileChooserBuilder(Bundle.GITBASH_dir())
                .setTitle(String.format(Bundle.GITBASH_title(), getLabel()))
                .setDirectoriesOnly(true)
                .setSelectionApprover(new FileChooserBuilder.SelectionApprover() {
 
                    @Override
                    public boolean approve(File[] files) {
                        if (null != files && files.length == 1) {
                            String absolutePath = FileUtil.normalizeFile(files[0]).getAbsolutePath();
                            String exeFile = String.format(Bundle.GITBASH_exefile(), absolutePath, File.separator);
                            final boolean exists = new File(exeFile).exists();
                            if (!exists) {
                                JOptionPane.showMessageDialog(null, String.format(Bundle.GITBASE_exefilenotfound(), absolutePath));
                            }
                            return exists;
                        }
                        return false;
                    }
                })
                .setApproveText(Bundle.GITBASH_ApproveTextBtn()).showOpenDialog();

        if (dir != null) {
            return String.format(Bundle.GITBASE_command(), FileUtil.normalizeFile(dir).getAbsolutePath(), File.separator);
        } else {
            return null;
        }
    }

    @Override
    public String getLabel() {
        return Bundle.GITBASE_label();
    }

    @Override
    public boolean isAvailable() {
        return Utilities.isWindows();
    }
}
