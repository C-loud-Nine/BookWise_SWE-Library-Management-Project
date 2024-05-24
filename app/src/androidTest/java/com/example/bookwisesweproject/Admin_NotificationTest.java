import com.example.bookwisesweproject.Admin_Notification;
import com.example.bookwisesweproject.R;

import androidx.test.espresso.action.ViewActions;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class Admin_NotificationTest {

    @Rule
    public ActivityScenarioRule<Admin_Notification> activityRule = new ActivityScenarioRule<>(Admin_Notification.class);

    @Test
    public void testUIElementsPresence() {
        // Check if all UI elements are displayed correctly
        onView(withId(R.id.notif)).check(matches(isDisplayed()));
        onView(withId(R.id.msgedt)).check(matches(isDisplayed()));
        onView(withId(R.id.msgbutton)).check(matches(isDisplayed()));
    }

    @Test
    public void testSendMessage() {
        // Type a message
        String testMessage = "Test Message";
        onView(withId(R.id.msgedt)).perform(typeText(testMessage), ViewActions.closeSoftKeyboard());

        // Click the send button
        onView(withId(R.id.msgbutton)).perform(click());

        // Delay for the message to be sent
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the toast message "Success" is displayed
        // Since verifying toast messages is not straightforward with Espresso,
        // you might need a custom matcher or handle it differently based on your testing requirements.
    }
}
