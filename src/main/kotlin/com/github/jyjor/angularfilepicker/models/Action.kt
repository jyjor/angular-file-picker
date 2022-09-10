package com.github.jyjor.angularfilepicker.models

enum class Action(private val text: String) {
    NOTHING("Do nothing"),
    PREVIOUS_FILE("Open previous file"),
    CLASS("Open class file"),
    TEMPLATE("Open template file"),
    STYLE("Open style file"),
    TEST("Open test file");

    override fun toString(): String {
        return text
    }
}