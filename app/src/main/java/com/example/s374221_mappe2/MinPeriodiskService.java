package com.example.s374221_mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import java.util.Calendar;

public class MinPeriodiskService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "I MinPeriodisk", Toast.LENGTH_SHORT).show();
        Log.d("MinPeriodisk", "I min periodisk");
        SharedPreferences preferanser = PreferenceManager.getDefaultSharedPreferences(this);
        String timeValue = preferanser.getString("sms_send_time", "08:00");

        // Henter ut tidspunktet fra preferences
        String[] timerogminutt = timeValue.split(":");
        int timer = Integer.parseInt(timerogminutt[0]);
        int minutt = Integer.parseInt(timerogminutt[1]);

        // Henter tidspunktet nå
        Calendar cal = Calendar.getInstance();

        // Setter tidspunktet for alarmen
        cal.set(Calendar.HOUR_OF_DAY, timer);
        cal.set(Calendar.MINUTE, minutt);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Sjekker om alarmen er i fortiden
        if (cal.getTimeInMillis() <= System.currentTimeMillis()) {
            cal.add(Calendar.DAY_OF_YEAR, 1); // Set it to the next day
        }

        Log.d("MinPeriodisk", "Alarm satt til: " + cal.getTime().toString());

        // Lager alarmen
        Intent i = new Intent(this, MinSendService.class);
        //legger til ekstra verdi når man oppretter intent for MinSendService som sier om intenten er utløs
        //av en alerm
        i.putExtra("utlost_av_alarm", true); // Legg til ekstra verdi
        PendingIntent pintent = PendingIntent.getService(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pintent);
        return super.onStartCommand(intent, flags, startId);
    }

}