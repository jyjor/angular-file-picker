package com.github.jyjor.angularfilepicker.extensions

import com.github.jyjor.angularfilepicker.models.Action
import com.github.jyjor.angularfilepicker.extensions.FileExtensionExtensions.Companion.classExtensions
import com.github.jyjor.angularfilepicker.extensions.FileExtensionExtensions.Companion.styleExtensions
import com.github.jyjor.angularfilepicker.extensions.FileExtensionExtensions.Companion.templateExtensions
import com.github.jyjor.angularfilepicker.extensions.FileExtensionExtensions.Companion.testExtensions
import com.github.jyjor.angularfilepicker.settings.AppSettingsState

class ActionExtensions {
    companion object {
        var previousExtension: String? = null

        val Action.extensions: List<String>
            get() {
                val state = AppSettingsState.instance ?: return emptyList()
                return when (this) {
                    Action.CLASS -> state.classExtensions
                    Action.STYLE -> state.styleExtensions
                    Action.TEMPLATE -> state.templateExtensions
                    Action.TEST -> state.testExtensions
                    Action.PREVIOUS_FILE -> previousExtension?.let(::listOf) ?: emptyList()
                    Action.NOTHING -> emptyList()
                }
            }

        val Action.nextAction: Action
            get() {
                val state = AppSettingsState.instance ?: return Action.NOTHING
                return when (this) {
                    Action.CLASS -> state.classNextAction
                    Action.TEMPLATE -> state.templateNextAction
                    Action.STYLE -> state.styleNextAction
                    Action.TEST -> state.testNextAction
                    else -> Action.NOTHING
                }
            }
    }
}