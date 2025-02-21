package com.example.s374221_mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
public class MinBroadcastReceiver extends BroadcastReceiver {
    public MinBroadcastReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "I BroadcastReceiver",Toast.LENGTH_SHORT).show();
        Log.d("MinBroadcast","I min Broadcast");

        Intent i = new Intent(context, MinPeriodiskService.class);
        context.startService(i);
    }
}