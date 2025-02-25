# Bursdag

## Introduksjon
**Bursdag** (også kalt *Bursdagsliste*) er en Android-applikasjon utviklet for å hjelpe deg med å holde oversikt over venners bursdager. Appen oppretter en lokal database ved hjelp av Room og benytter moderne teknologier som RxJava for asynkrone operasjoner. Ved å lagre og administrere bursdagsdata, kan appen automatisk sende bursdagshilsener via SMS.

## Funksjoner
- **Databasehåndtering:** Opprettelse og administrasjon av en lokal database med Room.
- **Venneliste:** Viser en liste over venner i `VennerListFragment`, med data hentet via `VennerDao`.
- **Legge til ny venn:** Brukeren kan legge til nye venner (navn, fødselsdato og telefonnummer) via skjermen "Ny venn".
- **Redigere og slette:** Trykk på en venn i listen for å få opp alternativer for å endre eller slette oppføringen.
- **SMS-tjenester:** Automatisk sending av bursdagshilsener via SMS, håndtert av tjenestene `MinBroadcastService`, `MinPeriodiskService` og `MinSendService`.
- **Notifikasjoner:** Bekreftende notifikasjoner vises når en SMS sendes, med informasjon om mottakerne.
- **Innstillinger:** Mulighet til å konfigurere tidspunkt for SMS-sending, innholdet i meldingen og om SMS-tjenesten skal være aktivert.

## Teknologier og Verktøy
- **Android:** Applikasjonen er utviklet som en Android-app.
- **Java og Kotlin:** Hovedspråk brukt i prosjektet.
- **Room:** Brukes for lokal databasehåndtering.
- **RxJava:** For asynkrone operasjoner og trådhåndtering.
- **Android Studio & Gradle:** Utviklingsmiljø og byggesystem (med Kotlin DSL i build.gradle.kts).

## Installasjon og Kjøreinstruksjoner
Følg disse stegene for å sette opp og kjøre prosjektet lokalt:

1. **Klon repositoriet:**
   ```bash
   git clone https://github.com/HassanYusuf1/Bursdag.git
Åpne prosjektet i Android Studio:

Start Android Studio.
Velg "Open an existing project" og naviger til mappen der du klonet repositoriet.
La Android Studio synkronisere Gradle og laste ned nødvendige avhengigheter.
Bygg og kjør applikasjonen:

Velg en emulator eller koble til en fysisk Android-enhet.
Klikk på "Run"-knappen for å bygge og starte applikasjonen.

Bruk
Når applikasjonen kjøres:

Vennelisten: Appen åpner med en liste over registrerte venner.
Legge til venn: Trykk på menyvalget "Ny venn" for å legge til en ny oppføring med navn, fødselsdato og telefonnummer.
Redigere/Slette: Trykk på en venn i listen for å få opp alternativer for å redigere eller slette oppføringen.
SMS-varsling: Når SMS-tjenesten er aktivert via innstillingene, sendes bursdagshilsener automatisk, og en notifikasjon bekrefter at meldingen er sendt.
SMS-Tjenester
Appen håndterer SMS-sending gjennom følgende tjenester:

MinBroadcastService: Mottar et broadcast ved appstart og aktiverer den periodiske tjenesten.
MinPeriodiskService: Sjekker tidspunktet for når SMS skal sendes basert på brukerens innstillinger (lagret i SharedPreferences).
MinSendService: Kontrollerer databasen for å se om noen har bursdag, sender SMS-hilsener, og viser en notifikasjon med mottakerinformasjon.
Prosjektstruktur
VennerListFragment: Viser listen over venner hentet fra databasen.
nyVenn: Skjerm for å legge til nye venner med nødvendig informasjon.
VennerDao: Data Access Object for databaseoperasjoner med Room.
Innstillinger: Lar brukeren bestemme tidspunkt og innhold for SMS, samt aktivere/deaktivere SMS-tjenesten.
Meny: Egen mappe for menyfunksjonalitet som bidrar til en strukturert kodebase.

Kilder
Android Developers: Save data in a local database using Room
Android Developers: Performing asynchronous queries with Room
Android Developers: Menus overview
Android Developers: Create a notification
