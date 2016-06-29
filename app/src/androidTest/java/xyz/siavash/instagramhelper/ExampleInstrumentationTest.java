package xyz.siavash.instagramhelper;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import xyz.siavash.instagramhelper.mvp.presenter.RelatedUserPresenter;
import xyz.siavash.instagramhelper.mvp.ui.TabContainerActivity;
import xyz.siavash.instagramhelper.util.UserDataPreferences;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentationTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("xyz.siavash.instagramhelper", appContext.getPackageName());
    }
    @Rule
    public ActivityTestRule<TabContainerActivity> activityRule
            = new ActivityTestRule<>(
            TabContainerActivity.class,
            true,     // initialTouchMode
            true);
    @Test
    public void checkErrorVisibilityWithoutToken() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        UserDataPreferences.setToken("token");
        onView(withId(R.id.errorView))
        .check(matches(isDisplayed()));
    }


}