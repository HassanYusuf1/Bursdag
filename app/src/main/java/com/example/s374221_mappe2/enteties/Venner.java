package com.example.s374221_mappe2.enteties;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Venner {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "navn")
    public String navn;

    @ColumnInfo(name = "telefonnummer")
    public String telefonnummer;

    @ColumnInfo(name = "foedselsdato")
    public String foedselsdato;

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getFoedselsdato() {
        return foedselsdato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setFoedselsdato(String foedselsdato) {
        this.foedselsdato = foedselsdato;
    }

    public String getNavn() {
        return navn;
    }


}
