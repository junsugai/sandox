package com.campus.disneycompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.campus.disneycompose.ui.details.PosterDetails
import com.campus.disneycompose.ui.main.MainActivity
import com.campus.disneycompose.ui.theme.DisneyComposeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityPosterDetailsTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var activity: MainActivity

    @Before
    fun init() {
        composeTestRule.activityRule.scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun posterDetailsFrozenIILoadingTest() {
        composeTestRule.setContent {
            DisneyComposeTheme {

                activity.viewModel.getPoster(0)

                PosterDetails(
                    viewModel = activity.viewModel,
                    pressOnBack = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Frozen II", ignoreCase = true)
            .assertIsDisplayed()
    }

    @Test
    fun posterDetailsZootopiaLoadingTest() {
        composeTestRule.setContent {
            DisneyComposeTheme {
                PosterDetails(
                    viewModel = activity.viewModel,
                    pressOnBack = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Zootopia", ignoreCase = true)
            .assertIsDisplayed()
    }
}
