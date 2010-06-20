package eu.linesofcode.taggerbot.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import eu.linesofcode.taggerbot.R;

/**
 * @author Xperimental
 */
public class Settings extends PreferenceActivity {

    /*
     * (non-Javadoc)
     * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

}
