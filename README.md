# Bursdag

**Bursdag** (også kalt *Bursdagsliste*) er en Android-applikasjon designet for å hjelpe deg med å holde oversikt over venners bursdager. Ved hjelp av en lokal database (Room) og asynkrone operasjoner med RxJava, administrerer appen bursdagsdata og sender automatiserte SMS-bursdagshilsener.

---

## 📑 Innholdsfortegnelse

- [Introduksjon](#introduksjon)
- [Hovedfunksjoner](#hovedfunksjoner)
- [Teknologier og Verktøy](#teknologier-og-verktøy)
- [Installasjon og Oppsett](#installasjon-og-oppsett)
- [Brukerveiledning](#brukerveiledning)
- [SMS-Tjenester](#sms-tjenester)
- [Prosjektstruktur](#prosjektstruktur)
- [Kilder og Dokumentasjon](#kilder-og-dokumentasjon)
- [Fremtidige Forbedringer](#fremtidige-forbedringer)
- [Lisens](#lisens)

---

## Introduksjon

**Bursdag** er en Android-applikasjon som gir deg full kontroll over venners bursdager. Med et brukervennlig grensesnitt kan du enkelt legge til, redigere og slette bursdagsoppføringer. Appen sørger for at ingen bursdag går ubemerket hen ved å sende automatiserte SMS-hilsener til dine venner på deres spesielle dag.

---

## Hovedfunksjoner

- **Databasehåndtering:**  
  Oppretter og administrerer en lokal database med Room for effektiv lagring av bursdagsdata.
  
- **Venneliste:**  
  Viser en oversikt over alle registrerte venner i `VennerListFragment`. Data hentes via `VennerDao` for rask tilgang.
  
- **Legge til ny venn:**  
  Lar deg registrere nye venner med navn, fødselsdato og telefonnummer gjennom skjermen "Ny venn".
  
- **Redigere og slette oppføringer:**  
  Trykk på en venn i listen for å redigere detaljer eller slette oppføringen.
  
- **Automatisert SMS-sending:**  
  Appen sender automatisk bursdagshilsener via SMS ved hjelp av tjenestene `MinBroadcastService`, `MinPeriodiskService` og `MinSendService`.
  
- **Varslingssystem:**  
  Bekreftende notifikasjoner vises etter at en SMS er sendt, med informasjon om hvem som har mottatt meldingen.
  
- **Innstillinger:**  
  Konfigurer SMS-sendingstidspunkt, meldingsinnhold og aktivering/deaktivering av SMS-tjenesten.

---

## Teknologier og Verktøy

- **Android:**  
  Utviklet som en Android-applikasjon.
  
- **Java og Kotlin:**  
  Java gir robust og moderne utvikling.
  
- **Room:**  
  Lokal databasehåndtering for lagring av vennedata.
  
- **RxJava:**  
  Håndterer asynkrone operasjoner og trådhåndtering.
  
- **Android Studio & Gradle:**  
  Brukes som utviklingsmiljø og byggesystem (med Kotlin DSL i `build.gradle.kts`).

---

## Installasjon og Oppsett

### Forutsetninger

- Android Studio (nyeste versjon anbefales)
- Git-klient for å klone repositoriet

### Steg-for-steg

1. **Klon prosjektet:**
   ```bash
   git clone https://github.com/HassanYusuf1/Bursdag.git
