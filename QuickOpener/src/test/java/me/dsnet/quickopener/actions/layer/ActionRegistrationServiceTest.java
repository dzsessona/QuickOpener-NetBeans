package me.dsnet.quickopener.actions.layer;

import org.junit.Test;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author markiewb
 */
public class ActionRegistrationServiceTest extends org.netbeans.junit.NbTestCase {

    public ActionRegistrationServiceTest(String name) {
        super(name);
    }

    @Test
    public void testRegisterAction() throws Exception {
        ActionRegistrationService.registerAction("id", "category", "displayName", "command");
        {
            FileObject configFile = FileUtil.getConfigFile("Actions/category/displayName.instance");
            assertNotNull(configFile);
            //check for attribute
            assertEquals("displayName", configFile.getAttribute("displayName"));
            assertEquals("command", configFile.getAttribute("custom-command"));
            assertNotNull(configFile.getAttribute("imagePath"));
        }

    }

    @Test
    public void testRegisterMenu() throws Exception {
        ActionRegistrationService.registerActionAsMenuAndToolbar("action1", "category");
        {
            FileObject configFile = FileUtil.getConfigFile("Menu/category/action1.shadow");
            assertNotNull(configFile);
        }
    }

    @Test
    public void testRegisterToolbar() throws Exception {
        ActionRegistrationService.registerActionAsMenuAndToolbar("action1", "category");
        {
            FileObject configFile = FileUtil.getConfigFile("Toolbars/category/action1.shadow");
            assertNotNull(configFile);
        }
    }

    @Test
    public void testUnregisterAction() throws Exception {
        ActionRegistrationService.registerAction("id", "category", "displayName", "command");
        ActionRegistrationService.registerActionAsMenuAndToolbar("displayName", "category");

        ActionRegistrationService.unregisterAction("displayName", "category");
        assertNull(FileUtil.getConfigFile("Actions/category/displayName.instance"));
        assertNull(FileUtil.getConfigFile("Menu/category/displayName.shadow"));
        assertNull(FileUtil.getConfigFile("Toolbars/category/displayName.shadow"));
    }

}
