package com.example.fitnessdays

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.fitnessdays.model.Exercise
import com.example.fitnessdays.ui.theme.FitnessDaysTheme
import org.junit.Rule
import org.junit.Test

class ExerciseUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    val exercise = Exercise(1,R.string.day1_title, R.string.day1_description, R.drawable.day1)

    @Test
    fun expandExerciseItemDescription() {
        composeTestRule.setContent {
            FitnessDaysTheme {
                ExerciseItem(exercise)
            }
        }

        val description = "Jump with legs spread and hands touching overhead. Great cardio."

        composeTestRule.onNodeWithText(description).assertDoesNotExist()
        composeTestRule.onNodeWithText("Day 1").performClick()
        composeTestRule.onNodeWithText(description).assertExists(
            "No node with this text was found."
        )
    }

    @Test
    fun expandExerciseItemIcon() {
        composeTestRule.setContent {
            FitnessDaysTheme {
                ExerciseItem(exercise)
            }
        }

        composeTestRule.onNodeWithTag("expandMore", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Day 1").performClick()
        composeTestRule.onNodeWithTag("expandLess", true).assertIsDisplayed()
    }
}