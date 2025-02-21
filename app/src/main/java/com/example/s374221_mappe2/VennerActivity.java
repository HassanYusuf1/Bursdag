package com.example.s374221_mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


//
public class VennerActivity extends AppCompatActivity {


    //Initialiserer aktiviteten og setter opp layouten.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

    }

    //On resume for å laste ned vennelistefragmentet
    @Override
    protected void onResume() {
        super.onResume();
        loadVennerListFragment();
    }

    // Laster inn VennerListFragment i hovedcontaineren.
    public void loadVennerListFragment() {
        VennerListFragment fragment = new VennerListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    //sjekker om iden er ny_venn og vis ny_venn-menyvalget er valgt,
    // oppretter den en ny Intent for å starte NyVenn-aktiviteten.
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ny_venn) {
            Intent nyAvtale = new Intent(VennerActivity.this, NyVenn.class);
            VennerActivity.this.startActivity(nyAvtale);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //vi bruker en default menu: https://developer.android.com/develop/ui/views/components/menus#options-menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.venner_menu, menu);
        return true;
    }
}