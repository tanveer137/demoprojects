package com.example.demouiautomator;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class BasicUiTest {

    private static UiDevice sUiDevice;

    @BeforeClass
    public static void setup() {
        // Initialize an instance of the UiDevice
        sUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testOpenHome() {
        sUiDevice.pressHome();
    }

//    /**
//     * This test case verifies if the Android Settings can be opened
//     * by clicking on the icon in the home screen.
//     * [Implementation using UiObject]
//     */
//    @Test
//    public void testOpenSettings() {
//        // Press on Home button using the UiDevice
//        sUiDevice.pressHome();
//        // Initialize UiObject to find view item having text "Settings"
//        UiObject settings = sUiDevice.findObject(new UiSelector().text("Settings"));
//        // Wait for maximum 5 seconds for the object to appear
//        if (settings.waitForExists(5000)) {
//            try {
//                // Click on the UiObject identified with text "Settings"
//                if (!settings.click()) {
//                    Assert.fail("Could not click on settings icon.");
//                }
//            } catch (UiObjectNotFoundException e) {
//                Assert.fail(e.getMessage());
//            }
//        } else {
//            Assert.fail("Settings is not available in home screen.");
//        }
//        // Initialize UiObject to find view item having the resource ID of settings recycler view
//        UiObject settings_content = sUiDevice.findObject(new UiSelector()
//                .resourceId("com.android.settings:id/recycler_view"));
//        // Assert that the UiObject actually exists on the display
//        Assert.assertTrue(settings_content.waitForExists(5000));
//        try {
//            // Assert that the total number of child items (menu items) for the container
//            // (settings page) is more than 10
//           Assert.assertTrue(settings_content.getChildCount() > 10);
//        } catch (UiObjectNotFoundException e) {
//            Assert.fail(e.getMessage());
//        }
//    }

    /**
     * This test case verifies if the Android Settings can be opened
     * by clicking on the icon in the home screen.
     * [Implementation using UiObject2]
     */
    @Test
    public void testOpenSettings() {
        // Press on Home button using the UiDevice
        sUiDevice.pressHome();
        // Initialize UiObject2 to find view item having text "Settings"
        UiObject2 settings = sUiDevice.findObject(By.text("Settings"));
        // Check if the object exists, null would mean the UI element was not found
        if (settings != null) {
            // Click on the UiObject2 identified with text "Settings"
            settings.click();
        } else {
            Assert.fail("Settings icon not found!");
        }
        // Initialize UiObject2 to find view item having the resource ID of settings recycler view
        // Maximum 5 seconds wait time for the object to be initialized
        // This is because, after clicking on the settings icon, it might need some time to
        // load the settings page, therefore it would require some time to discover the UI
        // element by the UiAutomator
        UiObject2 settings_content = sUiDevice.wait(Until
                        .findObject(By.res("com.android.settings:id/recycler_view")),
                5000);
        // Assert that the UiObject2 actually exists on the display
        Assert.assertNotNull(settings_content);
        // Assert that the total number of child items (menu items) for the container
        // (settings page) is more than 10
        Assert.assertTrue(settings_content.getChildCount() > 10);
    }

}
