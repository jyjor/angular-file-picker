// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.jyjor.angularfilepicker.settings

import com.github.jyjor.angularfilepicker.models.Action
import com.github.jyjor.angularfilepicker.models.CloseBehavior
import com.github.jyjor.angularfilepicker.models.Grouping
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.IdeBorderFactory
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val myClassFileExtensionsText = JBTextField()
    private val myClassNextActionCombo =
        ComboBox(Action.values().filter { it != Action.CLASS }.toTypedArray(), 240)

    private val myTemplateFileExtensionsText = JBTextField()
    private val myTemplateNextActionCombo =
        ComboBox(Action.values().filter { it != Action.TEMPLATE }.toTypedArray(), 240)

    private val myStyleFileExtensionsText = JBTextField()
    private val myStyleNextActionCombo =
        ComboBox(Action.values().filter { it != Action.STYLE }.toTypedArray(), 240)

    private val myTestFileExtensionsText = JBTextField()
    private val myTestNextActionCombo =
        ComboBox(Action.values().filter { it != Action.TEST }.toTypedArray(), 240)

    private val myGroupingCombo: ComboBox<Grouping> =
        ComboBox<Grouping>(Grouping.values(), 240)
    private val myCloseBehaviorCombo: ComboBox<CloseBehavior> =
        ComboBox<CloseBehavior>(CloseBehavior.values(), 240)

    init {
        val fileExtensionTypePanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Class: ", myClassFileExtensionsText, 5, false)
            .addLabeledComponent("Template: ", myTemplateFileExtensionsText, 5, false)
            .addLabeledComponent("Style: ", myStyleFileExtensionsText, 5, false)
            .addLabeledComponent("Test: ", myTestFileExtensionsText, 5, false)
            .panel
        fileExtensionTypePanel.border = IdeBorderFactory.createTitledBorder("File Extension Types")

        val nextActionPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("If on class file: ", myClassNextActionCombo, 5, false)
            .addLabeledComponent("If on template file: ", myTemplateNextActionCombo, 5, false)
            .addLabeledComponent("If on style file: ", myStyleNextActionCombo, 5, false)
            .addLabeledComponent("If on test file: ", myTestNextActionCombo, 5, false)
            .panel
        nextActionPanel.border = IdeBorderFactory.createTitledBorder("Second Action")

        val otherSettingsPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Open and close same component files: ", myGroupingCombo, 5, false)
            .addLabeledComponent("Close other component files: ", myCloseBehaviorCombo, 5, false)
            .panel
        otherSettingsPanel.border = IdeBorderFactory.createTitledBorder("Other Settings")

        panel = FormBuilder.createFormBuilder()
            .addComponent(fileExtensionTypePanel)
            .addComponent(nextActionPanel)
            .addComponent(otherSettingsPanel)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = myClassFileExtensionsText

    var classFileExtensionsText: String
        get() = myClassFileExtensionsText.text
        set(newText) {
            myClassFileExtensionsText.text = newText
        }
    var classNextActionCombo: Action
        get() = myClassNextActionCombo.item
        set(newAction) {
            myClassNextActionCombo.item = newAction
        }

    var templateFileExtensionsText: String
        get() = myTemplateFileExtensionsText.text
        set(newText) {
            myTemplateFileExtensionsText.text = newText
        }
    var templateNextActionCombo: Action
        get() = myTemplateNextActionCombo.item
        set(newAction) {
            myTemplateNextActionCombo.item = newAction
        }

    var styleFileExtensionsText: String
        get() = myStyleFileExtensionsText.text
        set(newText) {
            myStyleFileExtensionsText.text = newText
        }
    var styleNextActionCombo: Action
        get() = myStyleNextActionCombo.item
        set(newAction) {
            myStyleNextActionCombo.item = newAction
        }

    var testFileExtensionsText: String
        get() = myTestFileExtensionsText.text
        set(newText) {
            myTestFileExtensionsText.text = newText
        }
    var testNextActionCombo: Action
        get() = myTestNextActionCombo.item
        set(newAction) {
            myTestNextActionCombo.item = newAction
        }

    var grouping: Grouping
        get() = myGroupingCombo.item
        set(newSwitcherGrouping) {
            myGroupingCombo.item = newSwitcherGrouping
        }
    var closeBehavior: CloseBehavior
        get() = myCloseBehaviorCombo.item
        set(newCloseBehavior) {
            myCloseBehaviorCombo.item = newCloseBehavior
        }
}