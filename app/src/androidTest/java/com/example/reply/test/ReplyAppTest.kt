package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.ReplyHomeScreen
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {
    @get : Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @TestCompactWidth
    @Test
    fun replyAppScreen_VerifyForBottomNavigation () {
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }
            composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.navigation_bottom)
                .assertIsDisplayed()
    }
    @TestMediumWidth
    @Test
    fun verifyNavigationRail () {
        composeTestRule.setContent {
            ReplyApp(
                windowSize  = WindowWidthSizeClass.Medium
            )
        }
            composeTestRule.onNodeWithTagForStringId(
                com.example.reply.R.string.navigation_rail
            ).assertExists()
    }
    @TestExpandedWidth
    @Test
    fun verifyExpandedScreen () {
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }
        composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.navigation_drawer)
            .assertExists()
    }


}