package com.example.sep4_android;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.rule.ActivityTestRule;

import com.example.sep4_android.view.MainActivity;

@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    // Menu fragment testing
    @Test
    public void isCo2FragmentVisible() throws Exception{
        onView(withId(R.id.current_c_02)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnTemperatureMenuItem() throws Exception{
        onView(withId(R.id.tempFragment)).perform(click());
        onView(withId(R.id.current_temperature)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnHumidityMenuItem() throws Exception{
        onView(withId(R.id.humidityFragment)).perform(click());
        onView(withId(R.id.current_humidity)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnCO2MenuItem() throws Exception{
        onView(withId(R.id.CO2Fragment)).perform(click());
        onView(withId(R.id.current_c_02)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnCWindowMenuItem() throws Exception{
        onView(withId(R.id.windowFragment)).perform(click());
        onView(withId(R.id.current_window)).check(matches(isDisplayed()));
    }


    @Test
    public void clickOnLogoutMenuItem() throws Exception{
        openActionBarOverflowOrOptionsMenu(
                ApplicationProvider.getApplicationContext());
        onView(withText("Log out")).perform(click());
        onView(withId(R.id.createAccountBtn)).check(matches(isDisplayed()));
    }

}
