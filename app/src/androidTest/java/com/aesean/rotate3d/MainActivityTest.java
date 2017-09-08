package com.aesean.rotate3d;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.fragment_container_0), isDisplayed()));
        frameLayout.perform(click());

        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.fragment_container_1), isDisplayed()));
        frameLayout2.perform(click());

        ViewInteraction frameLayout3 = onView(
                allOf(withId(R.id.fragment_container_2), isDisplayed()));
        frameLayout3.perform(click());

        ViewInteraction frameLayout4 = onView(
                allOf(withId(R.id.fragment_container_3), isDisplayed()));
        frameLayout4.perform(click());

        ViewInteraction frameLayout5 = onView(
                allOf(withId(R.id.fragment_container_1), isDisplayed()));
        frameLayout5.perform(click());

        ViewInteraction frameLayout6 = onView(
                allOf(withId(R.id.fragment_container_3), isDisplayed()));
        frameLayout6.perform(click());

        ViewInteraction frameLayout7 = onView(
                allOf(withId(R.id.fragment_container_0), isDisplayed()));
        frameLayout7.perform(click());

        ViewInteraction frameLayout8 = onView(
                allOf(withId(R.id.fragment_container_2), isDisplayed()));
        frameLayout8.perform(click());

    }

}
