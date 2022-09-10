package com.github.jyjor.angularfilepicker.actions

import com.github.jyjor.angularfilepicker.models.Action
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class OpenStyleFileAction : AnAction("Open Style File") {
    override fun actionPerformed(e: AnActionEvent) {
        ActionPerformer.performAction(e.dataContext, Action.STYLE)
    }
}