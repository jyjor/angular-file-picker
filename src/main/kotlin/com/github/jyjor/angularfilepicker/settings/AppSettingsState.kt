package com.github.jyjor.angularfilepicker.settings

import com.github.jyjor.angularfilepicker.models.Action
import com.github.jyjor.angularfilepicker.models.CloseBehavior
import com.github.jyjor.angularfilepicker.models.Grouping
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "com.github.jyjor.angularfilepicker.settings.AppSettingsState",
    storages = [Storage("SdkSettingsPlugin.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState> {
    var classFileExtensions = DEFAULT_CLASS_FILE_EXTENSIONS
    var classNextAction = DEFAULT_CLASS_NEXT_ACTION

    var templateFileExtensions = DEFAULT_TEMPLATE_FILE_EXTENSIONS
    var templateNextAction = DEFAULT_TEMPLATE_NEXT_ACTION

    var styleFileExtensions = DEFAULT_STYLE_FILE_EXTENSIONS
    var styleNextAction = DEFAULT_STYLE_NEXT_ACTION

    var testFileExtensions = DEFAULT_TEST_FILE_EXTENSIONS
    var testNextAction = DEFAULT_TEST_NEXT_ACTION

    var grouping = DEFAULT_GROUPING
    var closeBehavior = DEFAULT_CLOSE_BEHAVIOR

    override fun getState(): AppSettingsState {
        return this
    }

    override fun loadState(state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: AppSettingsState?
            get() {
                return ApplicationManager.getApplication().getService(AppSettingsState::class.java)
            }

        private const val DEFAULT_CLASS_FILE_EXTENSIONS = ".ts"
        private val DEFAULT_CLASS_NEXT_ACTION = Action.PREVIOUS_FILE

        private const val DEFAULT_TEMPLATE_FILE_EXTENSIONS = ".html"
        private val DEFAULT_TEMPLATE_NEXT_ACTION = Action.PREVIOUS_FILE

        private const val DEFAULT_STYLE_FILE_EXTENSIONS = ".css .scss .sass .less"
        private val DEFAULT_STYLE_NEXT_ACTION = Action.PREVIOUS_FILE

        private const val DEFAULT_TEST_FILE_EXTENSIONS = ".spec.ts"
        private val DEFAULT_TEST_NEXT_ACTION = Action.PREVIOUS_FILE

        private val DEFAULT_GROUPING = Grouping.EVERYWHERE
        private val DEFAULT_CLOSE_BEHAVIOR = CloseBehavior.NEVER
    }
}
