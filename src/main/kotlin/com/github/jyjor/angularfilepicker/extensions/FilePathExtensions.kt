package com.github.jyjor.angularfilepicker.extensions

import com.github.jyjor.angularfilepicker.extensions.FileExtensionExtensions.Companion.extensions
import com.github.jyjor.angularfilepicker.settings.AppSettingsState
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile

class FilePathExtensions {
    companion object {
        fun getExtension(path: String): String? =
            AppSettingsState.instance?.extensions?.filter(path::endsWith)
                ?.maxByOrNull { it.length }

        fun getOtherFiles(path: String): List<VirtualFile> {
            val extension = getExtension(path) ?: return emptyList()
            val pathWithoutExtension = path.dropLast(extension.length)
            val extensions = AppSettingsState.instance?.extensions ?: return emptyList()
            return extensions
                .filter { it != extension }
                .map { pathWithoutExtension + it }
                .mapNotNull(::getFileOrNull)
        }

        fun getFileOrNull(path: String): VirtualFile? {
            val file = LocalFileSystem.getInstance().findFileByPath(path) ?: return null
            return if (file.exists()) file else null
        }
    }
}