package com.github.jyjor.angularfilepicker.extensions

import com.github.jyjor.angularfilepicker.settings.AppSettingsState

class FileExtensionExtensions {
    companion object {
        private fun getExtensionsAsList(extensions: String?): List<String> =
            extensions?.trim()?.split(Regex(" +")) ?: emptyList()

        val AppSettingsState.classExtensions: List<String>
            get() = getExtensionsAsList(classFileExtensions)

        val AppSettingsState.templateExtensions: List<String>
            get() = getExtensionsAsList(templateFileExtensions)

        val AppSettingsState.styleExtensions: List<String>
            get() = getExtensionsAsList(styleFileExtensions)

        val AppSettingsState.testExtensions: List<String>
            get() = getExtensionsAsList(testFileExtensions)

        val AppSettingsState.extensions: List<String>
            get() = classExtensions + templateExtensions + styleExtensions + testExtensions
    }
}