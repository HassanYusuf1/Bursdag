# Bursdagsliste

**Bursdagsliste** er en Android-applikasjon som hjelper deg med å holde oversikt over venners bursdager. Ved hjelp av en lokal database (Room) og asynkrone operasjoner med RxJava, administrerer appen bursdagsdata og sender automatiserte SMS-bursdagshilsener.

## Innholdsfortegnelse
- [Introduksjon](#introduksjon)
- [Hovedfunksjoner](#hovedfunksjoner)
- [Teknologier og verktøy](#teknologier-og-verktøy)
- [Installasjon og oppsett](#installasjon-og-oppsett)
- [Brukerveiledning](#brukerveiledning)
- [SMS-tjenester](#sms-tjenester)
- [Prosjektstruktur](#prosjektstruktur)
- [Fremtidige forbedringer](#fremtidige-forbedringer)
- [Lisens](#lisens)

## Introduksjon

**Bursdagsliste** er en Android-applikasjon som gir deg full kontroll over venners bursdager. Med et brukervennlig grensesnitt kan du enkelt legge til, redigere og slette bursdagsoppføringer. Appen sørger for at ingen bursdag går ubemerket hen ved å sende automatiserte SMS-hilsener til dine venner på deres spesielle dag.

<div align="center">
  <table>
    <tr>
      <td align="center">Hovedskjerm</td>
      <td align="center">Legg til venn</td>
      <td align="center">Innstillinger</td>
    </tr>
  </table>
</div>

## Hovedfunksjoner

- **Databasehåndtering:**  
  Oppretter og administrerer en lokal Room-database for effektiv lagring av bursdagsdata.
  
- **Venneliste:**  
  Viser en oversikt over alle registrerte venner i `VennerListFragment`. Data hentes via `VennerDao` for rask tilgang.
  
- **Legge til ny venn:**  
  Lar deg registrere nye venner med navn, fødselsdato og telefonnummer gjennom skjermen "Ny venn".
  
- **Redigere og slette oppføringer:**  
  Trykk på en venn i listen for å redigere detaljer eller slette oppføringen.
  
- **Automatisert SMS-sending:**  
  Appen sender automatisk bursdagshilsener via SMS ved hjelp av tjenestene `MinBroadcastReceiver`, `MinPeriodiskService` og `MinSendService`.
  
- **Varslingssystem:**  
  Bekreftende notifikasjoner vises etter at en SMS er sendt, med informasjon om hvem som har mottatt meldingen.
  
- **Innstillinger:**  
  Konfigurer SMS-sendingstidspunkt, meldingsinnhold og aktivering/deaktivering av SMS-tjenesten.

## Teknologier og verktøy

<div align="center">
  <table>
    <tr>
      <td align="center">Android</td>
      <td align="center">Java</td>
      <td align="center">Room</td>
      <td align="center">RxJava</td>
      <td align="center">Gradle</td>
    </tr>
  </table>
</div>

- **Android:**  
  Utviklet som en Android-applikasjon med støtte for Android 9.0 (API nivå 28) og nyere.
  
- **Java:**  
  Hovedspråket for appens forretningslogikk og brukergrensesnitt.
  
- **Room:**  
  Room Persistence Library brukes for database-håndtering, med støtte for SQLite-databaser.
  
- **RxJava/RxAndroid:**  
  Reaktiv programmering for å håndtere asynkrone operasjoner og database-interaksjoner.
  
- **Android Studio & Gradle:**  
  Brukes som utviklingsmiljø og byggesystem (med Kotlin DSL i `build.gradle.kts`).

## Installasjon og oppsett

### Forutsetninger
- Android Studio (nyeste versjon anbefales)
- Android SDK (minimum API-nivå: 28, Android 9.0)
- Git for å klone repository

### Steg-for-steg

1. **Klon prosjektet:**
   ```bash
   git clone https://github.com/brukernavn/Bursdagsliste.git
   ```

2. **Åpne prosjektet i Android Studio:**
   - Start Android Studio
   - Velg "Open an existing Android Studio project"
   - Naviger til mappen hvor du klonet prosjektet og velg den

3. **Synkroniser Gradle-filer:**
   - Android Studio vil automatisk synkronisere Gradle-filene
   - Hvis ikke, klikk på "Sync Project with Gradle Files"-knappen i verktøylinjen

4. **Legg til nødvendige tillatelser:**
   - Appen trenger SMS-tillatelser for å fungere
   - Sørg for at enheten/emulatoren har gitt disse tillatelsene (SEND_SMS)

5. **Bygg og kjør prosjektet:**
   - Koble til en Android-enhet eller start en emulator
   - Klikk på "Run" (▶) i Android Studio

## Brukerveiledning

### Første oppstart
Ved første oppstart vil appen be om nødvendige tillatelser for SMS-sending. Godkjenn disse for full funksjonalitet.

### Legge til en ny venn
1. Trykk på "Legg til venn" i menyen øverst til høyre
2. Fyll ut navn, fødselsdato (dd-mm-yyyy) og telefonnummer
3. Trykk på "Ny venn" for å lagre informasjonen

### Redigere en eksisterende oppføring
1. Trykk på ønsket venn i vennelisten
2. Velg "Endre" i dialogen som vises
3. Endre informasjonen i redigeringsskjermen
4. Trykk på "Ny venn" for å lagre endringene

### Slette en venn
1. Trykk på vennen du ønsker å slette
2. Velg "Slett" i dialogen som vises
3. Vennen blir umiddelbart fjernet fra databasen

### Konfigurere SMS-innstillinger
1. Gå til "Innstillinger" fra hovedmenyen
2. Aktiver "Aktiver SMS-tjeneste" for å slå på automatisk SMS-sending
3. Still inn ønsket tidspunkt for SMS-sending i "Tidspunkt for SMS-sending"
4. Tilpass standard SMS-melding i "Standard SMS-melding"

## SMS-tjenester

### Automatisk sending
Appen kjører en bakgrunnstjeneste som sjekker dagens bursdager og sender SMS-meldinger automatisk. Dette håndteres av følgende komponenter:

- **MinBroadcastReceiver:**  
  Aktiveres ved systemoppstart og starter den periodiske tjenesten.
  
- **MinPeriodiskService:**  
  Kjører periodiske sjekker for å identifisere dagens bursdager. Setter opp en daglig alarm for SMS-sending basert på brukerens tidspreferanser.
  
- **MinSendService:**  
  Håndterer den faktiske SMS-sendingen når alarmen utløses. Henter alle venner som har bursdag på gjeldende dato og sender dem en melding.

### Varslinger
Etter at en SMS er sendt, vil appen vise en notifikasjon med:
- Navn på mottaker(e)
- Telefonnummer til mottaker(e)
- Bekreftelse på at meldingen ble sendt

## Prosjektstruktur

```
com.example.s374221_mappe2/
├── daos/
│   └── VennerDao.java          # Data Access Object for venner-tabellen
├── enteties/
│   └── Venner.java             # Entitetsklasse for venn-objekter
├── AppDatabase.java            # Room-databasekonfigurasjon
├── MainActivity.java           # Hovedaktivitet
├── MinBroadcastReceiver.java   # Håndterer systemoppstart
├── MinPeriodiskService.java    # Periodisk tjeneste for å sjekke bursdager
├── MinSendService.java         # Tjeneste for SMS-sending
├── NyVenn.java                 # Aktivitet for å legge til/redigere venner
├── SettingsActivity.java       # Innstillingsaktivitet
├── SettingsFragment.java       # Fragment for innstillinger
├── VennerActivity.java         # Aktivitet for vennelisten
└── VennerListFragment.java     # Fragment for visning av venner
```

## Fremtidige forbedringer

- **Flerspråklig støtte:** Implementere støtte for flere språk.
- **Tema-alternativer:** Legge til lyst og mørkt tema.
- **Backup og synkronisering:** Mulighet for å sikkerhetskopiere data til skyen.
- **Påminnelser:** Legge til påminnelser før selve bursdagen.
- **Forbedret dato-validering:** Implementere bedre validering av datoformater.
- **Bildestøtte:** Legge til mulighet for å lagre profilbilder for venner.
- **Gruppeorganisering:** Mulighet for å organisere venner i grupper.



---

<div align="center">
  <p>Utviklet av Hassan Yusuf</p>
</div>
