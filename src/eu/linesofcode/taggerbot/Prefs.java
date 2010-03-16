package eu.linesofcode.taggerbot;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * This class provides access to the application-wide settings.
 * 
 * @author Xperimental
 */
public class Prefs {

    private static Prefs instance;

    public synchronized static Prefs get() {
        return instance;
    }

    public synchronized static void init(Context ctx) {
        instance = new Prefs(ctx);
    }

    private String keyUser;
    private String keyPassword;
    private String keyKeepAlive;
    private SharedPreferences prefs;

    private Prefs(Context ctx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        keyUser = ctx.getString(R.string.prefs_login_user_key);
        keyPassword = ctx.getString(R.string.prefs_login_password_key);
        keyKeepAlive = ctx.getString(R.string.prefs_updater_keepalive_key);
    }

    /**
     * 
     */
    public String getUser() {
        return prefs.getString(keyUser, null);
    }

    /**
     * 
     */
    public void setUser(String value) {
        setValue(keyUser, value);
    }

    /**
     * @param key
     * @param value
     */
    private void setValue(String key, String value) {
        Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    private void setValue(String key, boolean value) {
        Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 
     */
    public String getPassword() {
        return prefs.getString(keyPassword, null);
    }

    /**
     * 
     */
    public void setPassword(String value) {
        setValue(keyPassword, value);
    }

    public boolean isKeepAlive() {
        return prefs.getBoolean(keyKeepAlive, false);
    }

    public void setKeepAlive(boolean value) {
        setValue(keyKeepAlive, value);
    }

}
