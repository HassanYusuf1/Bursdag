package com.example.s374221_mappe2;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

// SettingFragmentet håndterer appens innstillinger ved å laste inn preferanser fra en XML-fil
// og sender en broadcast for å varsle andre komponenter når innstillingene er opprettet.

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Intent intent = new Intent();
        intent.setAction("com.example.service.MITTSIGNAL");
        getActivity().sendBroadcast(intent);

    }

}

