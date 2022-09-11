package com.github.jyjor.angularfilepicker.listeners

import com.github.jyjor.angularfilepicker.extensions.FilePathExtensions
import com.github.jyjor.angularfilepicker.models.CloseBehavior
import com.github.jyjor.angularfilepicker.models.Grouping
import com.github.jyjor.angularfilepicker.settings.AppSettingsState
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.impl.PsiAwareFileEditorManagerImpl
import com.intellij.openapi.vfs.VirtualFile

internal class FileOpenedListener : FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        super.fileOpened(source, file)

        val state = AppSettingsState.instance ?: return

        if (state.closeBehavior != CloseBehavior.ALWAYS) {
            return
        }

        val path = file.canonicalPath ?: return
        val otherFiles = FilePathExtensions.getOtherFiles(path)

        if (state.grouping == Grouping.EVERYWHERE) {
            otherFiles.filter(source::isFileOpen).forEach(source::closeFile)
        } else {
            val window = (source as PsiAwareFileEditorManagerImpl).splitters.currentWindow ?: return
            otherFiles.filter(window::isFileOpen).forEach(window::closeFile)
        }
    }
}
