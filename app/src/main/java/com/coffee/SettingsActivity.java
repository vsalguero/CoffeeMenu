package com.coffee;

import com.coffee.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by Vladimir Salguero on 25/09/2015.
 */

public class SettingsActivity extends PreferenceActivity {
    static final String KEY_PREF_LIKE = "likeplus";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        addPreferencesFromResource(R.xml.preferences);
    }
}
