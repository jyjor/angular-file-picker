package com.github.jyjor.angularfilepicker.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent


/**
 * Provides controller functionality for application settings.
 */
class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "Angular File Switcher"
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return mySettingsComponent?.preferredFocusedComponent
    }

    override fun createComponent(): JComponent? {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent?.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance

        var modified: Boolean = !mySettingsComponent?.classFileExtensionsText.equals(settings?.classFileExtensions)
        modified = modified or (mySettingsComponent?.classNextActionCombo !== settings?.classNextAction)

        modified = modified or !mySettingsComponent?.templateFileExtensionsText.equals(settings?.templateFileExtensions)
        modified = modified or (mySettingsComponent?.templateNextActionCombo !== settings?.templateNextAction)

        modified = modified or !mySettingsComponent?.styleFileExtensionsText.equals(settings?.styleFileExtensions)
        modified = modified or (mySettingsComponent?.styleNextActionCombo !== settings?.styleNextAction)

        modified = modified or !mySettingsComponent?.testFileExtensionsText.equals(settings?.testFileExtensions)
        modified = modified or (mySettingsComponent?.testNextActionCombo !== settings?.testNextAction)

        modified = modified or (mySettingsComponent?.grouping !== settings?.grouping)
        modified = modified or (mySettingsComponent?.closeBehavior !== settings?.closeBehavior)
        return modified
    }

    override fun apply() {
        val settings = AppSettingsState.instance

        val classFileExtensionsText = mySettingsComponent?.classFileExtensionsText
        if (classFileExtensionsText != null) {
            settings?.classFileExtensions = classFileExtensionsText
        }
        val classFileNextActionCombo = mySettingsComponent?.classNextActionCombo
        if (classFileNextActionCombo != null) {
            settings?.classNextAction = classFileNextActionCombo
        }

        val templateFileExtensionsText = mySettingsComponent?.templateFileExtensionsText
        if (templateFileExtensionsText != null) {
            settings?.templateFileExtensions = templateFileExtensionsText
        }
        val templateFileNextActionCombo = mySettingsComponent?.templateNextActionCombo
        if (templateFileNextActionCombo != null) {
            settings?.templateNextAction = templateFileNextActionCombo
        }

        val styleFileExtensionsText = mySettingsComponent?.styleFileExtensionsText
        if (styleFileExtensionsText != null) {
            settings?.styleFileExtensions = styleFileExtensionsText
        }
        val styleFileNextActionCombo = mySettingsComponent?.styleNextActionCombo
        if (styleFileNextActionCombo != null) {
            settings?.styleNextAction = styleFileNextActionCombo
        }

        val testFileExtensionsText = mySettingsComponent?.testFileExtensionsText
        if (testFileExtensionsText != null) {
            settings?.testFileExtensions = testFileExtensionsText
        }
        val testFileNextActionCombo = mySettingsComponent?.testNextActionCombo
        if (testFileNextActionCombo != null) {
            settings?.testNextAction = testFileNextActionCombo
        }

        val grouping = mySettingsComponent?.grouping
        if (grouping != null) {
            settings?.grouping = grouping
        }

        val closeBehavior = mySettingsComponent?.closeBehavior
        if (closeBehavior != null) {
            settings?.closeBehavior = closeBehavior
        }
    }

    override fun reset() {
        val settings = AppSettingsState.instance

        if (settings?.classFileExtensions != null) {
            mySettingsComponent?.classFileExtensionsText = settings.classFileExtensions
        }
        if (settings?.classNextAction != null) {
            mySettingsComponent?.classNextActionCombo = settings.classNextAction
        }

        if (settings?.templateFileExtensions != null) {
            mySettingsComponent?.templateFileExtensionsText = settings.templateFileExtensions
        }
        if (settings?.templateNextAction != null) {
            mySettingsComponent?.templateNextActionCombo = settings.templateNextAction
        }

        if (settings?.styleFileExtensions != null) {
            mySettingsComponent?.styleFileExtensionsText = settings.styleFileExtensions
        }
        if (settings?.styleNextAction != null) {
            mySettingsComponent?.styleNextActionCombo = settings.styleNextAction
        }

        if (settings?.testFileExtensions != null) {
            mySettingsComponent?.testFileExtensionsText = settings.testFileExtensions
        }
        if (settings?.testNextAction != null) {
            mySettingsComponent?.testNextActionCombo = settings.testNextAction
        }

        if (settings?.grouping != null) {
            mySettingsComponent?.grouping = settings.grouping
        }

        if (settings?.closeBehavior != null) {
            mySettingsComponent?.closeBehavior = settings.closeBehavior
        }
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}
