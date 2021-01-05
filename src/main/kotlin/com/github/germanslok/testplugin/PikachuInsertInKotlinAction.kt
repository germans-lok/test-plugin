package com.github.germanslok.testplugin

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtFunction

class PikachuInsertInKotlinAction : PikachuBaseInsertAction() {

    override fun shouldShowActionBasedOnPsiElement(event: AnActionEvent, element: PsiElement): Boolean {
        val codeBlock = PsiTreeUtil.getParentOfType(element, KtFunction::class.java)
        return codeBlock != null
    }

    override fun prepareInsertStatement(ascii: List<String>): String {
        val builder = StringBuilder()
        builder.append("println(\"\"\"\n")
        ascii.forEach { builder.append("$it\n") }
        builder.append("\"\"\")")
        return builder.toString()
    }

    override fun getElementToReformat(event: AnActionEvent, element: PsiElement): PsiElement? {
        return PsiTreeUtil.getParentOfType(element, KtFunction::class.java)
    }
}
