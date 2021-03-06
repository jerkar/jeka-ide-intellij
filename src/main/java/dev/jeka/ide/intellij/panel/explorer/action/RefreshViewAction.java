/*
 * Copyright 2018-2019 original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.jeka.ide.intellij.panel.explorer.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKey;

/**
 * @author Jerome Angibaud
 */
public class RefreshViewAction extends AnAction {

    public static final RefreshViewAction INSTANCE = new RefreshViewAction();

    private RefreshViewAction() {
        super("Force Refresh View", "Force Refresh Module View", AllIcons.Actions.ChangeView);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        RootAndJekaFolder rootAndJekaFolder = event.getData(RootAndJekaFolder.DATA_KEY);
        rootAndJekaFolder.getRootManager().refreshModule(rootAndJekaFolder.getJekaFolder());
    }

}
