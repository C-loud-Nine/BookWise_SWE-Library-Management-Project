package com.example.bookwisesweproject;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class MainUI {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testUIComponentsDisplayed() {
        // Check if ImageView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the logo TextView is displayed with the correct text
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.withText("BookHive")));

        // Check if the slogan TextView is displayed with the correct text
        Espresso.onView(ViewMatchers.withId(R.id.textView2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.textView2)).check(ViewAssertions.matches(ViewMatchers.withText("Welcome to BookHive")));
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
