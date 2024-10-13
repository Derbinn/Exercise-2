package com.yourpackage; 

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PurchaseFlowTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Test for the purchase flow
    @Test
    public void testPurchaseFlow() {
        // Step 1: Login
        Espresso.onView(withId(R.id.username_field))
                .perform(ViewActions.typeText("testuser"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.password_field))
                .perform(ViewActions.typeText("password"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.login_button))
                .perform(ViewActions.click());

        // Step 2: Select first item from the showcase screen
        Espresso.onView(withId(R.id.item_1))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.add_to_cart_button))
                .perform(ViewActions.click());

        // Step 3: Go back and select second item
        Espresso.pressBack();
        Espresso.onView(withId(R.id.item_2))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.add_to_cart_button))
                .perform(ViewActions.click());

        // Step 4: Go to cart screen
        Espresso.onView(withId(R.id.cart_button))
                .perform(ViewActions.click());

        // Bonus Step: Assert that the cart contains 2 items
        Espresso.onView(withId(R.id.cart_item_count))
                .check(matches(withText("2")));

        // Step 5: Proceed to address screen
        Espresso.onView(withId(R.id.proceed_to_checkout_button))
                .perform(ViewActions.click());

        // Step 6: Fill out address
        Espresso.onView(withId(R.id.address_field))
                .perform(ViewActions.typeText("123 Test Street"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.continue_button))
                .perform(ViewActions.click());

        // Step 7: Confirm purchase
        Espresso.onView(withId(R.id.confirm_purchase_button))
                .perform(ViewActions.click());

        // Verify purchase success
        Espresso.onView(withText("Purchase Successful"))
                .check(matches(ViewMatchers.isDisplayed()));
    }
}
 
