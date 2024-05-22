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
public class UserLoginUI {

    @Rule
    public ActivityScenarioRule<User_Login> activityScenarioRule = new ActivityScenarioRule<>(User_Login.class);

    @Test
    public void testUIComponentsDisplayed() {
        // Check if the TextViews are displayed
        Espresso.onView(ViewMatchers.withId(R.id.logo_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.slogan_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the TextInputEditTexts are displayed
        Espresso.onView(ViewMatchers.withId(R.id.email)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.pin)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the Buttons are displayed and have the correct text
        Espresso.onView(ViewMatchers.withId(R.id.btnlogin)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnlogin)).check(ViewAssertions.matches(ViewMatchers.withText("Enter")));

        Espresso.onView(ViewMatchers.withId(R.id.callreg)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.callreg)).check(ViewAssertions.matches(ViewMatchers.withText("New User ? Register Here")));

        // Check if the forgot password TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.forget_password)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.forget_password)).check(ViewAssertions.matches(ViewMatchers.withText("Forgot Password?")));
    }

    @Test
    public void testRegisterButtonClick() {
        // Perform click on Register Button
        Espresso.onView(ViewMatchers.withId(R.id.callreg)).perform(ViewActions.click());

        // Check if new activity is started (this requires you to have some identifiable view in User_Reg)
        // Assuming User_Reg activity has a view with id userRegView
        // Espresso.onView(ViewMatchers.withId(R.id.userRegView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testForgotPasswordClick() {
        // Perform click on Forgot Password TextView
        Espresso.onView(ViewMatchers.withId(R.id.forget_password)).perform(ViewActions.click());

        // Check if new activity is started (this requires you to have some identifiable view in ResetPasswordActivity)
        // Assuming ResetPasswordActivity has a view with id resetPasswordView
        // Espresso.onView(ViewMatchers.withId(R.id.resetPasswordView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
