package com.example.bookwisesweproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AdminUserSwitchTest {

    @Rule
    public ActivityScenarioRule<Admin_User_Switch> activityScenarioRule =
            new ActivityScenarioRule<>(Admin_User_Switch.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testAdminButtonLaunchesAdminLogin() {
        // Click the admin button
        onView(withId(R.id.adminButton)).perform(click());

        // Check if the Admin_Login activity is launched
        intended(hasComponent(Admin_Login.class.getName()));
    }

    @Test
    public void testUserButtonLaunchesUserLogin() {
        // Click the user button
        onView(withId(R.id.userButton)).perform(click());

        // Check if the User_Login activity is launched
        intended(hasComponent(User_Login.class.getName()));
    }

//    @Test
//    public void testUIElementsDisplayed() {
//        // Check if the image view and buttons are displayed
//        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
//        onView(withId(R.id.adminButton)).check(matches(isDisplayed()));
//        onView(withId(R.id.userButton)).check(matches(isDisplayed()));
//    }
}
