package dev.jeka.ide.intellij.platform;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.PathMacros;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.ui.playback.PathMacro;
import dev.jeka.ide.intellij.platform.ProjectPopupJekaActionGroup;
import org.jetbrains.jps.model.serialization.JpsPathVariablesConfiguration;

import java.io.File;
import java.util.Set;


public class JekaProjectComponent implements ProjectComponent {

    private static final String JEKA_USER_HOME = "JEKA_USER_HOME";

    @Override
    public void projectOpened() {

        // Instantiate jeka group action
        DefaultActionGroup jekaGroup = new ProjectPopupJekaActionGroup();

        // Register jeka group action
        ActionManager actionManager = ActionManager.getInstance();
        actionManager.registerAction(ProjectPopupJekaActionGroup.class.getName(), jekaGroup);

        // Add Jeka group to popup menu
        DefaultActionGroup projectPopupGroup = (DefaultActionGroup) actionManager.getAction("ProjectViewPopupMenu");
        Constraints menuLocation = new Constraints(Anchor.BEFORE, "Maven.GlobalProjectMenu");
        //Constraints menuLocation = new Constraints(Anchor.BEFORE, "MakeModule");
        projectPopupGroup.addAction(jekaGroup, menuLocation);
        projectPopupGroup.addAction(Separator.getInstance(), menuLocation);

        // Add classpath variable
        PathMacros pathMacros = PathMacros.getInstance();
        Set<String> macros = pathMacros.getAllMacroNames();
        if (!macros.contains(JEKA_USER_HOME)) {
            String value = System.getProperty("user.home") + File.separator + ".jeka";
            pathMacros.setMacro(JEKA_USER_HOME, value);
        }
    }
}
