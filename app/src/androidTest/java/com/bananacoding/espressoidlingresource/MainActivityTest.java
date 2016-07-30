package com.bananacoding.espressoidlingresource;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.bananacoding.espressoidlingresource.MoreThanMatcher.moreThan;

/**
 * Created by teerapong chantakard on 7/24/2016 AD.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void resetTimeout() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS);
    }

    @Test
    public void waitFor5Seconds() {
        waitFor(5);
    }

    @Test
    public void waitFor65Seconds() {
        waitFor(65);
    }

    private void waitFor(int waitingTime) {

        onView(withId(R.id.toggle_button))
                .check(matches(withText(R.string.start)))
                .perform(click());

        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * 2, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * 2, TimeUnit.SECONDS);

        IdlingResource idlingResource = new ElapsedTimeIdlingResource(DateUtils.SECOND_IN_MILLIS * waitingTime);
        Espresso.registerIdlingResources(idlingResource);

        onView(withId(R.id.toggle_button))
                .check(matches(withText(R.string.stop)))
                .perform(click());

        onView(withId(R.id.elapsed_Time))
                .check(matches(moreThan(waitingTime)));

        Espresso.unregisterIdlingResources(idlingResource);
    }
}