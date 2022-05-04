package com.geekbrains.tests

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activitySearch_IsDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun activitySearch_HasNotText() {
        onView(withId(R.id.searchEditText)).check(matches(withText("")))
    }

    @Test
    fun activityToDetailsButton_IsDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isDisplayed()))
    }

    @Test
    fun activityToDetailsButton_HasText() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(withText("TO DETAILS")))
    }

    @Test
    fun activityTotalCount_IsNotDisplayed() {
        onView(withId(R.id.totalCountTextView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun activityRecyclerView_IsNotDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())

        onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))
    }

    private fun delay(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
