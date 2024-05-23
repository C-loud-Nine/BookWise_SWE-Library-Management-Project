package com.example.bookwisesweproject;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class Add_BookTest {

    @Rule
    public ActivityScenarioRule<Add_Book> activityRule = new ActivityScenarioRule<>(Add_Book.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testAddNewBook() {
        // Simulate user input
        onView(withId(R.id.genre)).perform(replaceText("Science Fiction"));
        onView(withId(R.id.author)).perform(replaceText("Isaac Asimov"));
        onView(withId(R.id.book_name)).perform(replaceText("Foundation"));
        onView(withId(R.id.isbn)).perform(replaceText("1234567890"));

        // Click the button to add the book
        onView(withId(R.id.btnaddbook)).perform(click());

        // Verify input fields still contain the input after button click
        onView(withId(R.id.genre)).check(matches(withText("Science Fiction")));
        onView(withId(R.id.author)).check(matches(withText("Isaac Asimov")));
        onView(withId(R.id.book_name)).check(matches(withText("Foundation")));
        onView(withId(R.id.isbn)).check(matches(withText("1234567890")));
    }

    @Test
    public void testAddExistingBookCopy() {
        // Simulate user input for an existing book
        onView(withId(R.id.genre)).perform(replaceText("Science Fiction"));
        onView(withId(R.id.author)).perform(replaceText("Isaac Asimov"));
        onView(withId(R.id.book_name)).perform(replaceText("Foundation"));
        onView(withId(R.id.isbn)).perform(replaceText("1234567890"));

        // Click the button to add a new copy
        onView(withId(R.id.btnaddbook)).perform(click());

        // Verify input fields still contain the input after button click
        onView(withId(R.id.genre)).check(matches(withText("Science Fiction")));
        onView(withId(R.id.author)).check(matches(withText("Isaac Asimov")));
        onView(withId(R.id.book_name)).check(matches(withText("Foundation")));
        onView(withId(R.id.isbn)).check(matches(withText("1234567890")));
    }
}
