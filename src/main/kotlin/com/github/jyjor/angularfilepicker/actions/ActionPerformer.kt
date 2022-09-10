package com.github.jyjor.angularfilepicker.actions

import com.github.jyjor.angularfilepicker.extensions.ActionExtensions
import com.github.jyjor.angularfilepicker.extensions.ActionExtensions.Companion.extensions
import com.github.jyjor.angularfilepicker.extensions.ActionExtensions.Companion.nextAction
import com.github.jyjor.angularfilepicker.extensions.FilePathExtensions
import com.github.jyjor.angularfilepicker.models.Action
import com.github.jyjor.angularfilepicker.models.CloseBehavior
import com.github.jyjor.angularfilepicker.models.Grouping
import com.github.jyjor.angularfilepicker.settings.AppSettingsState
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class ActionPerformer {
    companion object {
        fun performAction(dataContext: DataContext, action: Action) {
            val currentFile = CommonDataKeys.VIRTUAL_FILE.getData(dataContext) ?: return
            val path = currentFile.path
            val extension = FilePathExtensions.getExtension(path) ?: return
            val pathWithoutExtension = path.dropLast(extension.length)


            var nextExtension = getNextExtension(pathWithoutExtension, action)
            if (nextExtension == null || nextExtension == extension) {
                nextExtension = getNextExtension(pathWithoutExtension, action.nextAction)
                if (nextExtension == null || nextExtension == extension) {
                    return
                }
            }

            val nextFile = FilePathExtensions.getFileOrNull(pathWithoutExtension + nextExtension)
                ?: return

            val project = CommonDataKeys.PROJECT.getData(dataContext) ?: return

            val grouping = AppSettingsState.instance?.grouping ?: return
            if (grouping == Grouping.EVERYWHERE) {
                switchFileEverywhere(project, nextFile)
            } else {
                switchFileInEditorGroup(dataContext, project, nextFile)
            }

            ActionExtensions.previousExtension = extension
        }

        private fun getNextExtension(pathWithoutExtension: String, action: Action): String? {
            return action.extensions.firstOrNull {
                FilePathExtensions.getFileOrNull(
                    pathWithoutExtension + it
                ) != null
            }
        }

        private fun switchFileEverywhere(project: Project, file: VirtualFile) {
            OpenFileDescriptor(project, file).navigate(true)

            if (AppSettingsState.instance?.closeBehavior == CloseBehavior.ONLY_ON_ACTION) {
                val fileEditorManager = FileEditorManager.getInstance(project)
                val path = file.canonicalPath ?: return
                val otherFiles = FilePathExtensions.getOtherFiles(path)
                otherFiles.filter(fileEditorManager::isFileOpen)
                    .forEach(fileEditorManager::closeFile)
            }
        }

        private fun switchFileInEditorGroup(
            dataContext: DataContext,
            project: Project,
            file: VirtualFile
        ) {
            val window = EditorWindow.DATA_KEY.getData(dataContext) ?: return
            (FileEditorManagerEx.getInstanceEx(project) as FileEditorManagerImpl)
                .openFileImpl2(window, file, true)

            if (AppSettingsState.instance?.closeBehavior == CloseBehavior.ONLY_ON_ACTION) {
                val path = file.canonicalPath ?: return
                FilePathExtensions.getOtherFiles(path).filter(window::isFileOpen)
                    .forEach(window::closeFile)
            }
        }
    }
}