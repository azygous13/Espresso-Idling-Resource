package com.bananacoding.espressoidlingresource;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by teerapong changtakard on 7/25/2016 AD.
 */
public class MoreThanMatcher {

    public static Matcher<View> moreThan(final int expectedValue) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView textView) {
                float actualValue = Float.valueOf(textView.getText().toString());
                return actualValue >= expectedValue;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("more than: " + expectedValue);
            }
        };
    }
}
