package com.example.bookwisesweproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testUIComponentsDisplayed() {
        // Check if ImageView is displayed
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));

        // Check if the logo TextView is displayed with the correct text
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.textView)).check(matches(withText("BookHive")));

        // Check if the slogan TextView is displayed with the correct text
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
        onView(withId(R.id.textView2)).check(matches(withText("Welcome to BookHive")));
    }

    @Test
    public void testSplashScreenTransition() {
        // Wait for the splash screen to complete and navigate to the next activity
        try {
            Thread.sleep(3500); // Wait for more than the splash screen delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
