package xyz.siavash.instagramhelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;


import xyz.siavash.instagramhelper.mvp.presenter.RelatedUserPresenter;

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
//    @Test
//    public void checkRxRequest() throws Exception{
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        RelatedUserPresenter relatedUserPresenter=new RelatedUserPresenter();
//        relatedUserPresenter.loadUsers(false);
//    }
}