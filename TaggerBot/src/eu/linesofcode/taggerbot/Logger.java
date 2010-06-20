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

    /**
     * Logs a warning message.
     * 
     * @param string
     *            Message text.
     */
    public static void w(String message) {
        Log.w(APP_TAG, message);
    }

    /**
     * Logs an error.
     * 
     * @param string
     *            Error message text.
     */
    public static void e(String message) {
        Log.e(APP_TAG, message);
    }

    private Logger() {
    }
}
