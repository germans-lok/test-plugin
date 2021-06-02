package com.github.germanslok.testplugin

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.util.Disposer
import com.intellij.ui.jcef.JBCefBrowser
import javax.swing.JComponent

class ApplicationConfigurable : Configurable {
    private val jcef = JBCefBrowser()

    override fun createComponent(): JComponent {
        jcef.loadURL("https://google.com")
        return jcef.component
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {
        // nothing
    }

    override fun getDisplayName(): String {
        return "Application Configurable"
    }

    override fun disposeUIResources() {
        Disposer.dispose(jcef)
    }
}
