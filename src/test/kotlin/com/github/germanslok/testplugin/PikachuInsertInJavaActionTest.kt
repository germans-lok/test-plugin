package com.github.germanslok.testplugin

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class PikachuInsertInJavaActionTest : BasePlatformTestCase() {

    private val action = PikachuInsertInJavaAction()

    fun `test insert Pikachu in java file`() {
        // Given:
        myFixture.configureByFile("ClassBeforePikachu.java")

        // When:
        myFixture.testAction(action)
        //some changes

        // Then:
        myFixture.checkResultByFile("ClassAfterPikachu.java")
    }

/*    fun `test not allow to insert Pikachu outside methods`() {
        // Given:
        myFixture.configureByFile("CaretOutSideMethod.java")

        // When:
        val actionPresentation = myFixture.testAction(action)

        // Then:
        assertFalse(actionPresentation.isEnabledAndVisible)
    }*/

    override fun getTestDataPath(): String {
        return "src/test/testData"
    }
}
