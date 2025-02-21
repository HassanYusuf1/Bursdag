package com.example.s374221_mappe2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;

import com.example.s374221_mappe2.enteties.Venner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MinSendService extends Service {

    private static final String CHANNEL_ID = "MinKanal"; // Kanal-ID

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        lagNotfikasjonKanal(); // Kaller metoden for å opprette kanalen når tjenesten starter
        Log.d("MinSendService", "Service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Sjekk om tjenesten ble utløst av alarmen
        boolean utlostAvAlarm = intent.getBooleanExtra("utlost_av_alarm", false);

        if (utlostAvAlarm) {
            // Kall funksjonen for å sende SMS
            sendSmsTilPersonerMedBursdag();
        } else {
            Log.d("MinSendService", "Tjenesten startet, men ikke av alarm.");
        }

        // Hvis tjenesten skal kjøre i bakgrunnen, kan vi returnere START_STICKY
        return START_NOT_STICKY;
    }
    private void lagNotfikasjonKanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Bursdagshilsener";
            String description = "Kanalen for bursdagshilsener";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void sendSmsTilPersonerMedBursdag() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSMSEnabled = sharedPreferences.getBoolean("sms_service_enabled", false);
        Log.d("MinSendService", "SMS service har blitt slått på: " + isSMSEnabled);

        if (isSMSEnabled) {
            String currentDate = new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date());
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Log.d("MinSendService", "Current date (MM-dd): " + currentDate);

            db.vennerDao().getFriendsWithBirthday(currentDate)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            vennerList -> {
                                String defaultMessage = sharedPreferences.getString("sms_default_message", "Gratulerer med dagen!");
                                for (Venner venn : vennerList) {
                                    sendSms(venn.getTelefonnummer(), defaultMessage);
                                }
                                visBursdagNotifikasjon(vennerList); // Show notification after sending SMS
                            },
                            throwable -> {
                                Log.e("MinSendService", "Error fetching friends with birthday", throwable);
                            }
                    );
        } else {
            Toast.makeText(this, "SMS service er ikke slått på", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSms(String mobil, String melding) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mobil, null, melding, null, null);
        } catch (Exception e) {
            Toast.makeText(this, "Kunne ikke sende SMS: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void visBursdagNotifikasjon(List<Venner> vennerList) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_IMMUTABLE);

        // Bygg meldingen som skal vises i notifikasjonen
        StringBuilder message = new StringBuilder("Bursdagshilsener sendt til:\n");
        for (Venner venn : vennerList) {
            message.append(venn.getNavn()).append(" (").append(venn.getTelefonnummer()).append(")\n");
        }

        //hentet fra:https://developer.android.com/develop/ui/views/notifications/build-notification
        // Bruker BigTextStyle for å vise hele meldingen
        Notification notifikasjon = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Bursdagshilsener sendt") // Tittel på notifikasjonen
                .setSmallIcon(R.mipmap.ic_launcher) // Ikon for notifikasjonen
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Prioritet for notifikasjonen
                .setContentIntent(pintent) // Intent som åpner MainActivity når varslet trykkes
                .setAutoCancel(true) // Varslet fjernes automatisk når det trykkes på
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message.toString())) // Setter hele meldingen i en BigTextStyle
                .build();

        // Vis varslingen med en unik ID
        notificationManager.notify(88, notifikasjon);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MinSendService", "Service destroyed");
    }
}
