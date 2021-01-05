package com.github.germanslok.testplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.codeStyle.CodeStyleManager

abstract class PikachuBaseInsertAction : AnAction() {

    abstract fun shouldShowActionBasedOnPsiElement(event: AnActionEvent, element: PsiElement): Boolean

    abstract fun prepareInsertStatement(ascii: List<String>): String

    open fun getElementToReformat(event: AnActionEvent, element: PsiElement): PsiElement? = null

    @Suppress("ReturnCount")
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(PlatformDataKeys.EDITOR) ?: return
        val caretOffset = editor.caretModel.offset
        val psiFile = e.getData(LangDataKeys.PSI_FILE) ?: return
        val project = editor.project ?: return

        val manager = PsiDocumentManager.getInstance(e.project!!)
        val document = manager.getDocument(psiFile) ?: return
        val element = psiFile.findElementAt(caretOffset) ?: return

        val service = project.getService(PikachuAsciiService::class.java)
        val insertStatement = prepareInsertStatement(service.getPikachuAsciiStrings())
        val elementToReformat = getElementToReformat(e, element)

        WriteCommandAction.runWriteCommandAction(editor.project) {
            document.insertString(caretOffset, insertStatement)
            manager.commitDocument(document)

            elementToReformat?.let {
                CodeStyleManager.getInstance(project).reformat(it)
            }
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = shouldShowAction(e)
    }

    @Suppress("ReturnCount")
    private fun shouldShowAction(e: AnActionEvent): Boolean {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return false
        val psiFile = e.getData(LangDataKeys.PSI_FILE) ?: return false
        val caretOffset = editor.caretModel.offset
        val element = psiFile.findElementAt(caretOffset) ?: return false
        return shouldShowActionBasedOnPsiElement(e, element)
    }
}
