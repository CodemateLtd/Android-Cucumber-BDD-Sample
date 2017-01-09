package com.codemate.booklibrary;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by ironman on 02/09/16.
 */
public class RecyclerViewAssertions {
    public static ViewAssertion adapterItemCountEquals(int count) {
        return new ItemCountAssertion(ItemCountAssertion.MODE_EQUALS, count);
    }

    public static ViewAssertion adapterItemCountLowerThan(int count) {
        return new ItemCountAssertion(ItemCountAssertion.MODE_LESS_THAN, count);
    }

    private static class ItemCountAssertion implements ViewAssertion {
        private static final int MODE_EQUALS = 0;
        private static final int MODE_LESS_THAN = 1;

        private final int mode;
        private final int expectedChildCount;

        ItemCountAssertion(int mode, int exptectedChildCount) {
            this.mode = mode;
            this.expectedChildCount = exptectedChildCount;
        }

        @Override
        public void check (View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();

            if (mode == MODE_EQUALS) {
                assertThat(expectedChildCount, is(adapter.getItemCount()));
            } else if (mode == MODE_LESS_THAN) {
                assertTrue(expectedChildCount > adapter.getItemCount());
            }
        }
    }
}
