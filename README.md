# 📱 Bursdagsliste

<div align="center">
  <h3>Aldri glem en bursdag igjen!</h3>
  <p><i>En Android-app for å administrere bursdager og sende automatiske SMS-hilsener</i></p>
  
  <hr>
  
  <div>
    <a href="#introduksjon">Introduksjon</a> •
    <a href="#hovedfunksjoner">Hovedfunksjoner</a> •
    <a href="#teknologier-og-verktøy">Teknologier</a> •
    <a href="#installasjon-og-oppsett">Installasjon</a> •
    <a href="#brukerveiledning">Brukerveiledning</a> •
    <a href="#sms-tjenester">SMS-tjenester</a> •
    <a href="#prosjektstruktur">Struktur</a>
  </div>
</div>

<hr>

## 📖 Introduksjon

**Bursdagsliste** er en brukervennlig Android-applikasjon som hjelper deg med å holde styr på viktige bursdager. 

Med Bursdagsliste kan du:
- Registrere alle dine venners bursdagsdata på ett sted
- Få automatiske påminnelser på bursdager
- Sende personlige bursdagshilsener via SMS automatisk på riktig dag
- Enkelt administrere din kontaktliste

<div align="center">
  <table>
    <tr>
      <td align="center"><img src="screenshots/hovedskjerm.png" alt="Hovedskjerm" width="200"/></td>
      <td align="center"><img src="screenshots/legg_til_venn.png" alt="Legg til venn" width="200"/></td>
      <td align="center"><img src="screenshots/innstillinger.png" alt="Innstillinger" width="200"/></td>
    </tr>
    <tr>
      <td align="center"><b>Hovedskjerm</b></td>
      <td align="center"><b>Legg til venn</b></td>
      <td align="center"><b>Innstillinger</b></td>
    </tr>
  </table>
</div>

## ✨ Hovedfunksjoner

<div align="center">
  <table>
    <tr>
      <th width="300">Funksjon</th>
      <th width="500">Beskrivelse</th>
    </tr>
    <tr>
      <td><b>📋 Venneliste</b></td>
      <td>Oversiktlig visning av alle venner, med navn, telefonnummer og fødselsdato</td>
    </tr>
    <tr>
      <td><b>➕ Legge til venner</b></td>
      <td>Intuitivt grensesnitt for å legge til nye venner med all nødvendig informasjon</td>
    </tr>
    <tr>
      <td><b>✏️ Redigering</b></td>
      <td>Mulighet for å redigere og oppdatere eksisterende kontaktinformasjon</td>
    </tr>
    <tr>
      <td><b>🔔 Automatiske SMS</b></td>
      <td>Automatisk sending av bursdagshilsener via SMS på den store dagen</td>
    </tr>
    <tr>
      <td><b>⚙️ Tilpassbare innstillinger</b></td>
      <td>Tilpass meldingsinnhold og tidspunkt for når SMS skal sendes</td>
    </tr>
    <tr>
      <td><b>🗑️ Enkel sletting</b></td>
      <td>Fjern kontakter fra listen med et enkelt trykk</td>
    </tr>
  </table>
</div>

## 🛠️ Teknologier og verktøy

<div align="center">
  <table>
    <tr>
      <td align="center"><b>Android</b></td>
      <td align="center"><b>Java</b></td>
      <td align="center"><b>Room</b></td>
      <td align="center"><b>RxJava</b></td>
      <td align="center"><b>Gradle</b></td>
    </tr>
    <tr>
      <td align="center">Framework</td>
      <td align="center">Hovedspråk</td>
      <td align="center">Database</td>
      <td align="center">Asynkron</td>
      <td align="center">Byggesystem</td>
    </tr>
  </table>
</div>

### Tekniske detaljer

- **Android:**  
  Målrettet mot Android API 28+ (Android 9.0 Pie og nyere)
  
- **Java:**  
  Brukt for all app-logikk og brukergrensesnitt-implementasjon
  
- **Room Persistence Library:**  
  SQLite-abstraksjonsbibliotek for robust databasehåndtering
  
- **RxJava/RxAndroid:**  
  Reaktive utvidelser for effektiv håndtering av asynkrone operasjoner
  
- **AlarmManager:**  
  Brukt for å planlegge presise alarmer for SMS-sending
  
- **NotificationManager:**  
  Implementert for å vise brukernotifikasjoner etter vellykket SMS-sending

## 📲 Installasjon og oppsett

### Forutsetninger
- Android Studio (nyeste versjon anbefales)
- Android SDK (minimum API-nivå: 28, Android 9.0 Pie)
- Git for å klone repository

### Installasjonsprosess

<div align="center">
  <table>
    <tr>
      <th>Steg</th>
      <th>Kommando / Handling</th>
    </tr>
    <tr>
      <td>1. Klon repository</td>
      <td><code>git clone https://github.com/brukernavn/Bursdagsliste.git</code></td>
    </tr>
    <tr>
      <td>2. Åpne i Android Studio</td>
      <td>File → Open → [velg prosjektmappe]</td>
    </tr>
    <tr>
      <td>3. Synkroniser Gradle</td>
      <td>Klikk på 'Sync Now' når forespurt</td>
    </tr>
    <tr>
      <td>4. Bygg appen</td>
      <td>Build → Make Project (Ctrl+F9)</td>
    </tr>
    <tr>
      <td>5. Kjør på enhet/emulator</td>
      <td>Run → Run 'app' (Shift+F10)</td>
    </tr>
  </table>
</div>

### Nødvendige tillatelser

Appen krever følgende tillatelser for å fungere korrekt:

```xml
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />
```

## 👨‍💻 Brukerveiledning

### Navigasjon i appen

<div align="center">
  <table>
    <tr>
      <td align="center" width="250"><b>Hovedskjerm</b></td>
      <td>Viser en liste over alle registrerte venner</td>
    </tr>
    <tr>
      <td align="center"><b>Legg til venn</b></td>
      <td>Tilgjengelig via menyen øverst til høyre</td>
    </tr>
    <tr>
      <td align="center"><b>Innstillinger</b></td>
      <td>Tilgjengelig via menyen øverst til høyre</td>
    </tr>
  </table>
</div>

### Legge til en ny venn

1. Fra hovedskjermen, trykk på **Legg til venn** i menyen øverst til høyre
2. Fyll ut følgende informasjon:
   - **Navn**: Vennens fulle navn
   - **Fødselsdato**: I formatet DD-MM-YYYY (f.eks. 15-06-1990)
   - **Telefonnummer**: Vennens mobilnummer
3. Trykk på **Ny venn**-knappen
4. Du vil se en bekreftelsesmelding og vennen blir lagt til i hovedlisten

### Redigere eller slette en venn

1. Fra hovedskjermen, trykk på vennen du ønsker å endre
2. En dialogboks vises med tre alternativer:
   - **Endre**: Åpner NyVenn-skjermen med forhåndsutfylte felt
   - **Slett**: Fjerner vennen fra databasen umiddelbart
   - **Avbryt**: Lukker dialogen uten endringer
3. Hvis du velger **Endre**, gjør endringene og trykk på **Ny venn**-knappen

### Innstillinger for SMS-sending

1. Fra hovedskjermen, velg **Innstillinger** fra menyen øverst til høyre
2. På innstillingsskjermen kan du:
   - **Aktivere/deaktivere SMS-tjenesten**: Slå automatisk SMS-sending på eller av
   - **Velge tidspunkt for SMS-sending**: Angi når på dagen bursdagsmeldinger skal sendes (f.eks. 08:00)
   - **Tilpasse standard meldingstekst**: Endre standard bursdagshilsen

## 📱 SMS-tjenester

Appen bruker en sofistikert arkitektur for å håndtere SMS-sending på riktig dag og tidspunkt.

### Systemflyt for SMS-sending

<div align="center">
  <table>
    <tr>
      <th>Komponent</th>
      <th>Funksjon</th>
    </tr>
    <tr>
      <td><b>MinBroadcastReceiver</b></td>
      <td>Starter ved enhetsoppstart og initialiserer den periodiske tjenesten</td>
    </tr>
    <tr>
      <td><b>MinPeriodiskService</b></td>
      <td>Setter opp daglig alarm basert på brukerinnstilt tidspunkt</td>
    </tr>
    <tr>
      <td><b>MinSendService</b></td>
      <td>Utløses av alarmen, identifiserer bursdager og sender SMS</td>
    </tr>
  </table>
</div>

### SMS-prosess i detalj

1. **Oppstart**: `MinBroadcastReceiver` mottar en BOOT_COMPLETED-broadcast ved enhetsoppstart
2. **Planlegging**: `MinPeriodiskService` leser innstillinger og setter en nøyaktig alarm for angitt tidspunkt
3. **Utløsning**: `MinSendService` aktiveres når alarmen utløses
4. **Databasesjekk**: Tjenesten sjekker databasen for venner med bursdag på gjeldende dato
5. **Sending**: SMS sendes til alle med bursdag på dagen
6. **Notifikasjon**: En brukernotifikasjon vises som bekrefter vellykkede sendinger

## 📂 Prosjektstruktur

```
com.example.s374221_mappe2/
│
├── activities/                        # Aktivitetsklasser
│   ├── MainActivity.java              # Hovedaktivitet
│   ├── NyVenn.java                    # Legge til/redigere venn
│   ├── SettingsActivity.java          # Innstillinger
│   └── VennerActivity.java            # Venneliste
│
├── fragments/                         # Fragmenter
│   ├── SettingsFragment.java          # Innstillingsfragment
│   └── VennerListFragment.java        # Venneliste-fragment
│
├── database/                          # Databasekomponenter
│   ├── AppDatabase.java               # Room-database
│   ├── daos/
│   │   └── VennerDao.java             # Data Access Object
│   └── enteties/
│       └── Venner.java                # Venner-entitet
│
├── services/                          # Bakgrunnstjenester
│   ├── MinBroadcastReceiver.java      # Boot receiver
│   ├── MinPeriodiskService.java       # Alarm-tjeneste
│   └── MinSendService.java            # SMS-tjeneste
│
└── res/                               # Ressursfiler
    ├── layout/                        # Layoutfiler
    │   ├── activity_main.xml          # Hovedlayout
    │   ├── nyvenn.xml                 # Legg til venn-layout
    │   └── venner_list.xml            # Vennelisteformat
    ├── menu/                          # Menyfiler
    ├── values/                        # Verdier
    └── xml/                           # XML-konfigurasjon
        └── preferences.xml            # Innstillinger
```

## 🔮 Fremtidige forbedringer

<div align="center">
  <table>
    <tr>
      <th>Funksjon</th>
      <th>Beskrivelse</th>
    </tr>
    <tr>
      <td><b>🌐 Flerspråklig støtte</b></td>
      <td>Støtte for flere språk gjennom ressurslokalisering</td>
    </tr>
    <tr>
      <td><b>🌙 Mørkt tema</b></td>
      <td>Implementere et mørkt tema for bedre brukeropplevelse</td>
    </tr>
    <tr>
      <td><b>☁️ Cloud backup</b></td>
      <td>Sync og backup av data til skytjenester</td>
    </tr>
    <tr>
      <td><b>🖼️ Bildestøtte</b></td>
      <td>Mulighet for å legge til profilbilder for kontakter</td>
    </tr>
    <tr>
      <td><b>👥 Gruppering</b></td>
      <td>Kategorisere venner i grupper for enklere administrasjon</td>
    </tr>
    <tr>
      <td><b>📊 Statistikk</b></td>
      <td>Visning av bursdagsstatistikk og kommende bursdager</td>
    </tr>
  </table>
</div>



---

<div align="center">
  <p>
    <b>Bursdagsliste</b> er utviklet av Hassan
  </p>
  
</div>
