package com.example.task.view.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.task.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    @JvmField
    @Rule
    val activity = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun userClick() {
        onView(withId(R.id.emailEdit)).perform(typeText("demo@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordEdit)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.logInBtn)).perform(click())
    }
}