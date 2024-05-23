package com.example.bookwisesweproject;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class AdminDashTest {

    @Rule
    public ActivityScenarioRule<Admin_Dash> activityRule = new ActivityScenarioRule<>(Admin_Dash.class);

    @Test
    public void testUIElementsPresence() {
        // Verify the presence of important UI elements
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.userlist)).check(matches(isDisplayed()));
        onView(withId(R.id.inventory)).check(matches(isDisplayed()));
        onView(withId(R.id.addbook)).check(matches(isDisplayed()));
        onView(withId(R.id.updatebook)).check(matches(isDisplayed()));
        onView(withId(R.id.wishlist)).check(matches(isDisplayed()));
        onView(withId(R.id.defaulter)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavigationToUserList() {
        // Simulate clicking the user list card and verify the navigation
        onView(withId(R.id.userlist)).perform(click());
        // Verify the new activity, if possible
    }

    @Test
    public void testNavigationToInventory() {
        // Simulate clicking the inventory card and verify the navigation
        onView(withId(R.id.inventory)).perform(click());
        // Verify the new activity, if possible
    }

    @Test
    public void testNavigationToAddBook() {
        // Simulate clicking the add book card and verify the navigation
        onView(withId(R.id.addbook)).perform(click());
        // Verify the new activity, if possible
    }

    @Test
    public void testNavigationToUpdateBook() {
        // Simulate clicking the update book card and verify the navigation
        onView(withId(R.id.updatebook)).perform(click());
        // Verify the new activity, if possible
    }

    @Test
    public void testNavigationToNotifications() {
        // Simulate clicking the wishlist card and verify the navigation
        onView(withId(R.id.wishlist)).perform(click());
        // Verify the new activity, if possible
    }

    @Test
    public void testLogout() {
        // Simulate clicking the defaulter card and verify the logout alert
        onView(withId(R.id.defaulter)).perform(click());
        // Verify the alert dialog
        onView(withText("Do you want to Log Out?")).check(matches(isDisplayed()));
        // Click the "No" button on the alert dialog
        onView(withText("No")).perform(click());
    }
}
