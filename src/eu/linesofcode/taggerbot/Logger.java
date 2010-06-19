package eu.linesofcode.taggerbot;

import android.util.Log;

/**
 * Utility class for logging in the application. Uses the standard android
 * logging capabilities and the same tag for this application.
 * 
 * @author Xperimental
 */
public class Logger {

    private static final String APP_TAG = "TaggerBot";

    /**
     * Log a debug message.
     * 
     * @param message
     *            Message to log.
     */
    public static void d(String message) {
        Log.d(APP_TAG, message);
    }

    private Logger() {
    }
}
