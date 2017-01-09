package com.codemate.booklibrary.ui;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.codemate.booklibrary.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.codemate.booklibrary.RecyclerViewAssertions.adapterItemCountEquals;
import static com.codemate.booklibrary.RecyclerViewAssertions.adapterItemCountLowerThan;

/**
 * Some really coarse UI tests.
 *
 * Check the MainPresenterTest for more specific tests that ensure correct books are
 * returned.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void allBooksDisplayedInUI() {
        onView(ViewMatchers.withId(R.id.bookRecycler))
                .check(adapterItemCountEquals(45));
    }

    @Test
    public void onSearchTermEntered_NonMatchingItemsNotShown() {
        onView(isAssignableFrom(EditText.class))
                .perform(ViewActions.typeText("2"));
        onView(withId(R.id.bookRecycler))
                .check(adapterItemCountLowerThan(45));
    }
}
