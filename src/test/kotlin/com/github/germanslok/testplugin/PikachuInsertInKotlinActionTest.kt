package com.github.germanslok.testplugin

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class PikachuInsertInKotlinActionTest : BasePlatformTestCase() {

    private val action = PikachuInsertInKotlinAction()

    fun `test insert Pikachu in kotlin file`() {
        // Given:
        myFixture.configureByFile("ClassBeforePikachu.kt")

        // When:
        myFixture.testAction(action)

        // Then:
        myFixture.checkResultByFile("ClassAfterPikachu.kt")
    }

    fun `test not allow to insert Pikachu outside methods in kotlin`() {
        // Given:
        myFixture.configureByFile("CaretOutsideMethod.kt")

        // When:
        val actionPresentation = myFixture.testAction(action)

        // Then:
        assertFalse(actionPresentation.isEnabledAndVisible)
    }

    override fun getTestDataPath(): String {
        return "src/test/testData"
    }
}
