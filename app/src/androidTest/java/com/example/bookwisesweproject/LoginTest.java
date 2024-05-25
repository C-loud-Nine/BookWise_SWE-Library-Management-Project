package com.example.bookwisesweproject;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class LoginTest {

    @Rule
    public ActivityScenarioRule<User_Login> activityScenarioRule = new ActivityScenarioRule<>(User_Login.class);

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.INTERNET);

    @Test
    public void testUIComponentsDisplayed() {
        // Check if the TextViews are displayed
        Espresso.onView(ViewMatchers.withId(R.id.logo_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.slogan_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the TextInputEditTexts are displayed
        Espresso.onView(ViewMatchers.withId(R.id.email)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.pin)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the Buttons are displayed
        Espresso.onView(ViewMatchers.withId(R.id.btnlogin)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.callreg)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the forgot password TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.forget_password)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
