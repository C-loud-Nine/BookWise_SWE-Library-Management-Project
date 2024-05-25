package com.example.bookwisesweproject;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class UserRegTest {

    @Rule
    public ActivityScenarioRule<User_Reg> activityScenarioRule = new ActivityScenarioRule<>(User_Reg.class);

    @Test
    public void testUIComponentsDisplayed() {
        // Check if the TextInputEditTexts are displayed
        Espresso.onView(ViewMatchers.withId(R.id.reg_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.reg_email)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.reg_phone)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.reg_pin)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the Buttons are displayed and have the correct text
        Espresso.onView(ViewMatchers.withId(R.id.btnreg)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnreg)).check(ViewAssertions.matches(ViewMatchers.withText("Enter")));

        Espresso.onView(ViewMatchers.withId(R.id.call_login)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.call_login)).check(ViewAssertions.matches(ViewMatchers.withText("Already have an account? Login here")));
    }

    @Test
    public void testLoginButtonClick() {
        // Perform click on Login Button and check if User_Login activity is launched
        Espresso.onView(ViewMatchers.withId(R.id.call_login)).perform(ViewActions.click());

        // Assuming User_Login activity has a view with id userLoginView, uncomment and modify the following line if needed
        // Espresso.onView(ViewMatchers.withId(R.id.userLoginView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testInvalidEmailValidation() {
        // Enter invalid email and valid values for other fields
        Espresso.onView(ViewMatchers.withId(R.id.reg_name)).perform(ViewActions.typeText("Test User"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.reg_email)).perform(ViewActions.typeText("invalidemail"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.reg_pin)).perform(ViewActions.typeText("123456"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.reg_phone)).perform(ViewActions.typeText("01234567890"), ViewActions.closeSoftKeyboard());

        // Click register button
        Espresso.onView(ViewMatchers.withId(R.id.btnreg)).perform(ViewActions.click());

        // Check if email error message is displayed
        Espresso.onView(ViewMatchers.withId(R.id.reg_email)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("Enter a valid email")));
    }
}
