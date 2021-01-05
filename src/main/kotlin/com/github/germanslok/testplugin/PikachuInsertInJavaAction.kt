package com.github.germanslok.testplugin

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil

class PikachuInsertInJavaAction : PikachuBaseInsertAction() {

    override fun shouldShowActionBasedOnPsiElement(event: AnActionEvent, element: PsiElement): Boolean {
        val codeBlock = PsiTreeUtil.getParentOfType(element, PsiMethod::class.java)
        return codeBlock != null
    }

    override fun prepareInsertStatement(ascii: List<String>): String {
        val builder = StringBuilder()
        ascii.forEach {
            builder.append("""System.out.println("$it");""")
        }
        return builder.toString()
    }

    override fun getElementToReformat(event: AnActionEvent, element: PsiElement): PsiElement? {
        return PsiTreeUtil.getParentOfType(element, PsiMethod::class.java)
    }
}
