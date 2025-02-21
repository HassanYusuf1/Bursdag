package com.example.s374221_mappe2;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {
    private static final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        //Sjekk og be om SEND_SMS tillatelse
        sjekkOgBeomSMSTillatelse();
        sjekkSMSServiceAktivert();
    }

    private void sjekkOgBeomSMSTillatelse() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS},
                    SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }


    //Denne metoden sjekker for sjekker for SMS-tillatelse
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SEND_SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS-tillatelse er på. Du kan sende SMS.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS-tillatelse er ikke på. Du kan ikke sende SMS.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //denne metoden sjekker for SMS-tjenesten er aktivert i appens innstilinger
    private void sjekkSMSServiceAktivert() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSMSEnabled = sharedPreferences.getBoolean("sms_service_enabled", false);

        if (isSMSEnabled) {
            Toast.makeText(this, "SMS-tjenesten er aktivert. Du kan sende SMS.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SMS-tjenesten er ikke aktivert. Du kan ikke sende SMS.", Toast.LENGTH_SHORT).show();
        }
    }
}
