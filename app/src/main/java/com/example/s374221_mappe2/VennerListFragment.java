package com.example.s374221_mappe2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import com.example.s374221_mappe2.daos.VennerDao;
import com.example.s374221_mappe2.enteties.Venner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

 //VennerListFragment er en fragmentklasse som viser en liste over venner.

public class VennerListFragment extends ListFragment {

    private List<Venner> venneListe1 = new ArrayList<>();
    private ArrayAdapter<Venner> adapter;


    //Henter alle venner fra databasen ved hjelp av VennerDao og setter dem i en ArrayAdapter
    // for å vise dem i en liste.
    @SuppressLint("CheckResult")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());

        db.vennerDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        venneListe -> {
                            venneListe1 = venneListe;
                            adapter = new ArrayAdapter<Venner>(getActivity(), R.layout.venner_list, venneListe1) {
                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    if (convertView == null) {
                                        LayoutInflater inflater = LayoutInflater.from(getContext());
                                        convertView = inflater.inflate(R.layout.venner_list, parent, false);
                                    }

                                    Venner venn = getItem(position);

                                    // Finn TextViewene i layouten
                                    TextView navnTextView = convertView.findViewById(R.id.navnTextView);
                                    TextView telefonnummerTextView = convertView.findViewById(R.id.telefonnummerTextView);
                                    TextView fodselsdatoTextView = convertView.findViewById(R.id.fodselsdatoTextView);

                                    // Sett dataene i TextViewene
                                    if (venn != null) {
                                        navnTextView.setText(venn.navn);
                                        telefonnummerTextView.setText("Telefonnummer: " + venn.telefonnummer);
                                        fodselsdatoTextView.setText("Fødselsdato: " + venn.foedselsdato); // Legg til fødselsdato
                                    }

                                    return convertView;
                                }
                            };
                            setListAdapter(adapter);
                        },
                        throwable -> {
                            // Feilhandtering
                        }
                );
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Når en venn blit klikket i venneliste, vises en
        //AlertDialog med muligheten til å endre eller slette vennen.
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Velg handling");
                builder.setMessage("Vil du slette eller endre denne vennen?");

                // Hvis "Endre" velges, startes NyVenn-aktiviteten for å redigere vennen.
                builder.setNegativeButton("Endre", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), NyVenn.class);
                        // Pass på informasjon om vennen som skal endres, hvis nødvendig
                        intent.putExtra("vennId", venneListe1.get(position).getId());
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                //Hvis "Slett" velges, slettes vennen fra databasen og oppdateres listen.
                builder.setPositiveButton("Slett", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("SletteVenn", "Position før sletting: " + position);
                        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());
                        VennerDao vennerDao = db.vennerDao();
                        Venner vennToDelete = venneListe1.get(position);
                        Log.d("SletteVenn", "Venn som slettes: " + vennToDelete.navn);

                        vennerDao.delete(vennToDelete)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> {
                                    venneListe1.remove(position); // Fjern fra liste
                                    adapter.notifyDataSetChanged(); // Oppdater adapter
                                }, throwable -> {
                                    Log.e("SletteVenn", "Feil under sletting av venn: " + throwable.getMessage(), throwable);
                                });
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Avbryt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
}
