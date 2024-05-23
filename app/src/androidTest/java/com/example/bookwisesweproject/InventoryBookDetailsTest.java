package com.example.bookwisesweproject;

import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class InventoryBookDetailsTest {

    @Rule
    public ActivityTestRule<InventoryBookDetails> activityRule =
            new ActivityTestRule<>(InventoryBookDetails.class, true, false);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testBookDetailsDisplayed() {
        // Create an intent with extras to pass to the activity
        Intent intent = new Intent();
        intent.putExtra("isbn_name", "123456789");
        intent.putExtra("book_name", "Test Book");

        // Launch the activity with the intent
        activityRule.launchActivity(intent);

        // Check if the TextViews are displayed and contain the correct text
        onView(withId(R.id.textView8)).check(matches(isDisplayed()));
        onView(withId(R.id.textView8)).check(matches(withText("Test Book")));

        onView(withId(R.id.textView9)).check(matches(isDisplayed()));
        onView(withId(R.id.textView9)).check(matches(withText("123456789")));
    }
}
