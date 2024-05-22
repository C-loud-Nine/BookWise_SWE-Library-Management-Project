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
public class AdminuserUI {

    @Rule
    public ActivityScenarioRule<Admin_User_Switch> activityScenarioRule = new ActivityScenarioRule<>(Admin_User_Switch.class);

    @Test
    public void testUIComponentsDisplayed() {
        // Check if ImageView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if Admin Login Button is displayed and has the correct text
        Espresso.onView(ViewMatchers.withId(R.id.adminButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.adminButton)).check(ViewAssertions.matches(ViewMatchers.withText("Admin Login")));

        // Check if User Login Button is displayed and has the correct text
        Espresso.onView(ViewMatchers.withId(R.id.userButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.userButton)).check(ViewAssertions.matches(ViewMatchers.withText("User Login")));
    }

    @Test
    public void testAdminButtonClick() {
        // Perform click on Admin Login Button and check if Admin_Login activity is launched
        Espresso.onView(ViewMatchers.withId(R.id.adminButton)).perform(ViewActions.click());


    }

    @Test
    public void testUserButtonClick() {
        // Perform click on User Login Button and check if User_Login activity is launched
        Espresso.onView(ViewMatchers.withId(R.id.userButton)).perform(ViewActions.click());

    }
}
