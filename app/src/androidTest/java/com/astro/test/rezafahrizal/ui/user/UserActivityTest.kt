package com.astro.test.rezafahrizal.ui.user

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.astro.test.rezafahrizal.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UserActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<UserActivity>()

    @Test
    fun checkActivityVisibility() {
        Espresso.onView(withId(R.id.layout_user))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun editTextTest() {
        Espresso.onView(withId(R.id.et_source_search))
            .perform(ViewActions.typeText("eva"))
    }

    @Test
    fun buttonClick() {
        Espresso.onView(withId(R.id.rb_desc_search))
            .perform(ViewActions.click())

    }
}