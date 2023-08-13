package com.example.task.view.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.task.R
import com.example.task.view.activities.LoginActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun yourSetUPFragment() {
        activityTestRule.getActivity().getFragmentManager().beginTransaction()
    }

    @Before
    fun setUp() {
        launchFragmentInContainer<HomeFragment>()
    }

    @Test
    fun navOneRecyclerViewFragment() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1, click()
            )
        )
    }

}