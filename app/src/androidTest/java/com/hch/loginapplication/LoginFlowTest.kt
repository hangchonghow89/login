package com.hch.loginapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@RunWith(AndroidJUnit4::class)
class LoginFlowTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun fullLoginFlow() {
        runBlocking {
            delay(888)
        }
        // Page 1: Login
        composeTestRule.onNodeWithTag("password_field").performTextInput("Test@2026")
        runBlocking {
            delay(888)
        }
        composeTestRule.onNodeWithTag("login_button").performClick()

        runBlocking {
            delay(888)
        }
        // Page 2: Check if "test text 1" exists
        composeTestRule.waitForIdle()
        val testTextNode = composeTestRule.onAllNodes(hasText("test text 1", substring = false))
        val hasTestText = testTextNode.fetchSemanticsNodes().isNotEmpty()

        runBlocking {
            delay(888)
        }
        if (hasTestText) {
            composeTestRule.onNodeWithTag("test_text_button").performClick()
        } else {
            composeTestRule.onNodeWithTag("not_test_text_button").performClick()
            return
        }
        runBlocking {
            delay(888)
        }

        // Page 3: Enter PIN
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("pin_field").performTextInput("8526")

        runBlocking {
            delay(888)
        }
        composeTestRule.onNodeWithTag("pin_display").assertIsDisplayed()
        runBlocking {
            delay(888)
        }
        composeTestRule.onNodeWithText("PIN entered: 8526").assertIsDisplayed()
    }
}
