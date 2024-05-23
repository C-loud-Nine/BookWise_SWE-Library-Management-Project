package com.example.bookwisesweproject;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class Admin_LoginTest {

    @Rule
    public ActivityScenarioRule<Admin_Login> activityRule = new ActivityScenarioRule<>(Admin_Login.class);



    @Test
    public void testValidLogin() throws InterruptedException {
        // Simulate valid login
      // onView(withId(R.id.email_admin)).perform(typeText("admin@gmail.com"));
     //   onView(withId(R.id.password_admin)).perform(typeText("475544"));
       // onView(withId(R.id.btnloginadmin)).perform(typeText(testMessage), ViewActions.closeSoftKeyboard());
        String testMessage = "admin@gmail.com";
        String testMessage1 = "475544";
        onView(withId(R.id.email_admin)).perform(typeText(testMessage), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_admin)).perform(typeText(testMessage1), ViewActions.closeSoftKeyboard());
//        // Adding a delay for debugging
//        Espresso.onIdle();
    //  Thread.sleep(1000);
//
//        // Initialize intents to check for the next activity
//        Intents.init();

        // Perform login button click
        onView(withId(R.id.btnloginadmin)).perform(click());

//        // Verify that the intent to start Admin_Dash activity was launched
//        Intents.intended(IntentMatchers.hasComponent(Admin_Dash.class.getName()));
//
//        // Release intents
      //  Intents.release();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
