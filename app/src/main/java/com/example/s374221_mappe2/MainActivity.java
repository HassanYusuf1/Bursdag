package com.example.s374221_mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.s374221_mappe2.daos.VennerDao;

public class MainActivity extends AppCompatActivity {

    // Initialiserer aktiviteten, setter opp layouten og starter MinSendService.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fetch the database instance
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        VennerDao vennerDao = db.vennerDao();

        startTheService();
    }

    private void startTheService() {
        Intent serviceIntent = new Intent(this, MinSendService.class);
        startService(serviceIntent);
    }

    // Laster inn VennerListFragment når aktiviteten gjenopprettes.
    @Override
    protected void onResume() {
        super.onResume();
        loadVennerListFragment();
    }

    public void loadVennerListFragment() {
        VennerListFragment fragment = new VennerListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    //for å vise menyvalg.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_menu, menu);
        return true;
    }


    // Håndterer klikk på menyvalg for å legge til ny venn eller åpne innstillingsaktiviteten.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ny_venn) {
            Intent venner = new Intent(MainActivity.this, NyVenn.class);
            MainActivity.this.startActivity(venner);
            return true;
        } else if (item.getItemId() == R.id.preferanser) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
