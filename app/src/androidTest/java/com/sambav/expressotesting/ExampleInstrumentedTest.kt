package com.sambav.expressotesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

import android.view.View
import android.widget.EditText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

import androidx.test.espresso.Espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions

import androidx.test.espresso.action.ViewActions.clearText

import androidx.test.espresso.action.ViewActions.typeText

import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import androidx.test.espresso.matcher.RootMatchers.withDecorView

import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//https://github.com/azygous13/Espresso-Login
    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun myFirstTest(){
        onView(withId(R.id.inputField)).perform(typeText("HELLO"), closeSoftKeyboard())
        onView(withId(R.id.changeText)).perform(click())
        onView(allOf(withId(R.id.tv), withText("First")))
        try{
            //onView(withId(R.id.textView)).check(matches(withText("hari")))
            //onView(withId(R.id.textView)).check(matches(not(isDisplayed())))
            //onView(withId(R.id.textView)).check(matches(not(isEnabled())))
        }catch (e:Exception){
            //throw Exception("This Is My Exception")
            fail("The integers are not equal");
        }

    }

    @get:Rule
    val rule1 = ActivityScenarioRule(MainActivity3::class.java)
    @Test
    fun myFirstTest1(){
        onView(withId(R.id.tv))
    }

    @get:Rule
    val rule_login = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun testLogin(){
        onView(withId(R.id.tv_email)).perform(clearText());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.tv_email)).check(matches(withError("This field is required")));
    }

    @Test
    fun passwordIsEmpty() {
        onView(withId(R.id.tv_email)).perform(typeText("email@email.com"), closeSoftKeyboard())
        onView(withId(R.id.tv_password)).perform(clearText())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.tv_password)).check(matches(withError("This field is required")))
    }

    @Test
    fun emailIsInvalid() {
        onView(withId(R.id.tv_email)).perform(typeText("invalid"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.tv_email)).check(matches(withError("This email address is invalid")))
    }

    @Test
    fun passwordIsTooShort() {
        onView(withId(R.id.tv_email)).perform(typeText("email@email.com"), closeSoftKeyboard())
        onView(withId(R.id.tv_password)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.tv_password)).check(matches(withError("This password is too short")))
    }

    @Test
    fun loginFailed() {
        onView(withId(R.id.tv_email)).perform(typeText("incorrect@email.com"), closeSoftKeyboard())
        onView(withId(R.id.tv_password)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText("Your email or password was incorrect. Please try again."))

    }

    @Test
    fun loginSuccessfully_shouldShowWelcomeMessage() {
        onView(withId(R.id.tv_email)).perform(typeText("user@email.com"), closeSoftKeyboard())
        onView(withId(R.id.tv_password)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
       // onView(withId(R.id.tv_welcome)).check(matches(withText("Hi user@email.com!")))
    }

    @Test
    fun loginSuccessfully_shouldShowToast() {
        onView(withId(R.id.tv_email)).perform(typeText("user@email.com"), closeSoftKeyboard())
        onView(withId(R.id.tv_password)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText("Login successfully."))

    }

    companion object  {
        private fun withError(expected: String): TypeSafeMatcher<View?> {
            return object : TypeSafeMatcher<View?>() {
                override fun matchesSafely(item: View?): Boolean {
                    return if (item is EditText) {
                        item.error.toString() == expected
                    } else false
                }

                override fun describeTo(description: Description) {
                    description.appendText("Not found error message$expected, find it!")
                }
            }
        }
    }

}