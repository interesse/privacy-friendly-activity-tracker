package org.secuso.privacyfriendlyactivitytracker.utils;

import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Build;

/**
 *
 * @author Tobias Neidig
 * @version 20160610
 */
public class AndroidVersionHelper {
    /**
     * Decides whether the current soft- and hardware setup allows to use hardware step detection
     * @param pm An instance of the android PackageManager
     * @return true if hardware step detection can be used otherwise false
     */
    public static boolean supportsStepDetector(PackageManager pm) {
        return getStepDetectorType(pm) != 0;
    }
    /**
     * Decides whether the current soft- and hardware setup allows to use hardware step detection
     * @param pm An instance of the android PackageManager
     * @return which type of hardware step detection can be used or null if none is available
     */
    public static int getStepDetectorType(PackageManager pm) {
        // (Hardware) step detection was introduced in KitKat (4.4 / API 19)
        // https://developer.android.com/about/versions/android-4.4.html
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // In addition to the system version
            // the hardware step detection is not supported on every device
            // let's check the device's ability.
            if (pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)) {
                return Sensor.TYPE_STEP_COUNTER;
            } else {
                if (pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)) {
                    return Sensor.TYPE_STEP_DETECTOR;
                }
            }
        }
        return 0;
    }
}
