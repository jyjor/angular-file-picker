package com.github.jyjor.angularfilepicker.models

enum class Grouping(private val text: String) {
    TAB_GROUP("Within the same editor group"),
    EVERYWHERE("Everywhere");

    override fun toString(): String {
        return text
    }
}
