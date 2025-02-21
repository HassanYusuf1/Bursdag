package com.example.s374221_mappe2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s374221_mappe2.daos.VennerDao;
import com.example.s374221_mappe2.enteties.Venner;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NyVenn extends AppCompatActivity {

    private EditText navnEditText;
    private EditText telefonnummerEditText;
    private EditText fodselsdatoEditText;
    private TextView feilmeldingEditText;

    private TextView felmeldingfods;
    private VennerDao vennerDao;
    private int vennId; // Variabel for å lagre ID-en til vennen som skal endres

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nyvenn);

        // Initialiser databasen og DAO
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        vennerDao = db.vennerDao();

        // Hent referanser til UI-elementene
        navnEditText = findViewById(R.id.navnEditText);
        telefonnummerEditText = findViewById(R.id.telefonnummerEditText);
        fodselsdatoEditText = findViewById(R.id.foedselsdatoEditText);
        Button nyVennKnapp = findViewById(R.id.nyVennKnapp);
        feilmeldingEditText = findViewById(R.id.feilmeldingEditText);
        felmeldingfods = findViewById(R.id.feilmeldingfods);

        // Hent vennId fra Intent
        vennId = getIntent().getIntExtra("vennId", -1);

        // Hvis vennId ikke er -1, hent vennen fra databasen
        if (vennId != -1) {
            hentVenn(vennId);
        }

        // Set up click listener for the button
        nyVennKnapp.setOnClickListener(view -> lagreVenn());
    }

    private void hentVenn(int vennId) {
        vennerDao.getVennById(vennId) // Hent venn basert på ID
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(venn -> {
                    // Fyll ut feltene med vennen sin informasjon
                    navnEditText.setText(venn.getNavn());
                    telefonnummerEditText.setText(venn.getTelefonnummer());
                    fodselsdatoEditText.setText(venn.getFoedselsdato());
                }, throwable -> {
                    // Håndter feil
                    Toast.makeText(this, "Kunne ikke hente vennen", Toast.LENGTH_SHORT).show();
                });
    }

    private void lagreVenn() {
        String navn = navnEditText.getText().toString();
        String telefonnummer = telefonnummerEditText.getText().toString();
        String fodselsdato = fodselsdatoEditText.getText().toString();

        // Validering av input før lagring
        if (navn.isEmpty()) {
            feilmeldingEditText.setText("Ny venn må ha et navn!!");
            return;
        }
        if(fodselsdato.isEmpty()){
            felmeldingfods.setText("Legg til venn må ha fodselsdato!");
            return;
        }
        feilmeldingEditText.setText("");
        felmeldingfods.setText("");

        Venner nyVenn = new Venner();
        nyVenn.setNavn(navn);
        nyVenn.setTelefonnummer(telefonnummer);
        nyVenn.setFoedselsdato(fodselsdato);

        if (vennId != -1) {
            // Oppdater eksisterende venn
            nyVenn.setId(vennId); // Sett ID-en til den eksisterende vennen
            vennerDao.updateVenn(nyVenn)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        Toast.makeText(NyVenn.this, "Venn oppdatert!", Toast.LENGTH_SHORT).show();
                        finish();
                    }, throwable -> {
                        Toast.makeText(NyVenn.this, "Feil ved oppdatering av venn!", Toast.LENGTH_SHORT).show();
                    });
        } else {
            // Lagre ny venn
            vennerDao.insertVenn(nyVenn)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        Toast.makeText(NyVenn.this, "Venn lagret!", Toast.LENGTH_SHORT).show();
                        navnEditText.setText("");
                        telefonnummerEditText.setText("");
                        fodselsdatoEditText.setText("");
                    }, throwable -> {
                        Toast.makeText(NyVenn.this, "Feil ved lagring av venn!", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
