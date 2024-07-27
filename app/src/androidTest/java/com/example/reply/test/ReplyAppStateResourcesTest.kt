package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateResourcesTest {

     @get:Rule
     val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @TestCompactWidth
    @Test
    fun compactDevice_selectedEmailEmailRetained_afterConfiChange() {
    val stateRestorationTester  = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                LocalEmailsDataProvider.allEmails[2].body
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        composeTestRule.onNodeWithContentDescriptionForStringId(
            com.example.reply.R.string.navigation_back
        ).assertExists()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()

        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.onNodeWithContentDescriptionForStringId(
            com.example.reply.R.string.navigation_back
        ).assertExists()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()


    }
    @TestExpandedWidth
    @Test
    fun expandedScreen_selectedEmamilEmailRetained_afterConfigChange () {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).performClick()
        // since we are testing for teh expaneed screen so to maek sure this is happening in detail screen we will
        //  check if all this happening in the deialledscreen so >;
        composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body))
            )
        )
        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body))
            )
            )
    }
}