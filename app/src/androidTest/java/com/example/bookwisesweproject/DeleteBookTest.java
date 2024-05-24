import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;

import com.example.bookwisesweproject.Delete_Book;
import com.example.bookwisesweproject.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.click;

import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class DeleteBookTest {

    @Rule
    public ActivityScenarioRule<Delete_Book> activityRule = new ActivityScenarioRule<>(Delete_Book.class);

    @Test
    public void testUIElementsPresence() {
        // Check if the TextView is displayed
        onView(withId(R.id.inventory)).check(matches(isDisplayed()));

        // Check if the ListView is displayed
        onView(withId(R.id.deletebookListView)).check(matches(isDisplayed()));
    }

//    @Test
//    public void testListViewInteraction() {
//        // Wait for data to load
////        try {
////            Thread.sleep(2000); // Adjust the delay as needed for your app
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//        // Perform click on a ListView item (ensure there is at least one item in the list)
//        onData(anything())
//                .inAdapterView(withId(R.id.deletebookListView))
//                .atPosition(0)
//                .perform(click());
//
//        // Wait for the alert dialog to be displayed
//        try {
//            Thread.sleep(1000); // Adjust the delay as needed for your app
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Check if the alert dialog is displayed
//        onView(withId(android.R.id.message)).check(matches(isDisplayed()));
//    }
}
