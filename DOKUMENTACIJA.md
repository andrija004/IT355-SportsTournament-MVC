# Dokumentacija projektnog zadatka — IT355 Web sistemi 2

**Naziv projekta:** Sportski Turnir — sistem za upravljanje sportskim turnirom
**Predmet:** IT355 Web sistemi 2
**Student:** Andrija Milenković
**Univerzitet:** Univerzitet Metropolitan Beograd
**GitHub repozitorijum:** https://github.com/andrija004/IT355-SportsTournament-MVC

---

## Sadržaj

1. [Uvod](#1-uvod)
2. [Funkcionalni zahtevi](#2-funkcionalni-zahtevi)
3. [Model podataka](#3-model-podataka)
4. [Arhitektura aplikacije](#4-arhitektura-aplikacije)
5. [Skladištenje podataka (application scope)](#5-skladištenje-podataka-application-scope)
6. [Pregled funkcionalnosti po entitetima (CRUD)](#6-pregled-funkcionalnosti-po-entitetima-crud)
7. [Izgled i stilizacija (CSS)](#7-izgled-i-stilizacija-css)
8. [Uputstvo za pokretanje aplikacije](#8-uputstvo-za-pokretanje-aplikacije)
9. [Uputstvo za korišćenje aplikacije](#9-uputstvo-za-korišćenje-aplikacije)
10. [Zaključak](#10-zaključak)

---

## 1. Uvod

### 1.1 Opis teme projekta

Aplikacija **Sportski Turnir** je Spring Boot MVC veb aplikacija namenjena evidenciji i
upravljanju podacima o sportskom (fudbalskom) turniru: timovima, igračima, terenima,
sudijama i utakmicama. Aplikacija omogućava administratoru turnira da unosi, pregleda,
ažurira i briše sve navedene entitete, kao i da zakazuje utakmice povezujući timove,
teren i sudiju u jedan događaj.

### 1.2 Cilj projekta

Cilj projekta je izrada Spring Boot MVC aplikacije koja u potpunosti primenjuje tehnike
obrađene na predmetu IT355:

- MVC arhitektura sa jasno odvojenim slojevima (Model, Kontroler, Pogled)
- Modelski i kontrolerski nivo urađeni u Javi
- Nivo pogleda urađen u Thymeleaf-u
- Čuvanje podataka u **application scope**-u (in-memory), bez korišćenja baze podataka
- Potpuna CRUD funkcionalnost (Create, Read, Update, Delete) nad svim entitetima
- Stilizovan korisnički interfejs primenom CSS-a

### 1.3 Pregled korišćenih tehnologija

| Tehnologija | Namena |
|---|---|
| Java 17 | Programski jezik |
| Spring Boot 3.2.0 | Osnovni framework aplikacije |
| Spring MVC | Web i kontroler sloj |
| Thymeleaf | Template engine za nivo pogleda |
| Maven | Build alat i upravljanje zavisnostima |
| CSS3 | Stilizacija korisničkog interfejsa |
| Application scope (singleton `@Component`) | Skladištenje podataka umesto baze podataka |

---

## 2. Funkcionalni zahtevi

Aplikacija omogućava korisniku (administratoru turnira) sledeće:

- Pregled liste svih timova, igrača, terena, sudija i utakmica
- Dodavanje novog tima, igrača, terena, sudije i utakmice preko forme
- Izmenu postojećih podataka za svaki od navedenih entiteta
- Brisanje postojećih zapisa
- Pregled detalja pojedinačnog zapisa (npr. detalji utakmice prikazuju povezani domaći
  tim, gostujući tim, teren i sudiju)
- Pregled zbirnih statistika (broj timova, igrača, terena, sudija i utakmica) na
  početnoj stranici, kao i pregled rasporeda zakazanih utakmica

### Use-case (osnovni akter: Administrator turnira)

```
Administrator turnira
 ├── Upravlja timovima (dodaj / izmeni / obriši / pregledaj)
 ├── Upravlja igračima (dodaj / izmeni / obriši / pregledaj) — igrač je vezan za tim
 ├── Upravlja terenima (dodaj / izmeni / obriši / pregledaj)
 ├── Upravlja sudijama (dodaj / izmeni / obriši / pregledaj)
 └── Zakazuje i upravlja utakmicama
        (bira domaći tim, gostujući tim, teren i sudiju; unosi rezultat)
```

---

## 3. Model podataka

Aplikacija sadrži **5 model klasa** (paket `com.tournament.model`), svaka predstavlja
jedan entitet sistema. Umesto relacione baze i stranih ključeva preko JPA anotacija,
veze između entiteta su predstavljene ID referencama (`Long`) koje se ručno razrešavaju
u servisnom/kontrolerskom sloju — u skladu sa zahtevom da se umesto baze podataka
koristi application scope.

### 3.1 `Tim`
| Atribut | Tip | Opis |
|---|---|---|
| id | Long | Jedinstveni identifikator |
| naziv | String | Naziv tima |
| grad | String | Grad iz kog je tim |
| godinaOsnivanja | int | Godina osnivanja tima |
| trener | String | Ime i prezime trenera |

### 3.2 `Igrac`
| Atribut | Tip | Opis |
|---|---|---|
| id | Long | Jedinstveni identifikator |
| ime, prezime | String | Ime i prezime igrača |
| pozicija | String | Pozicija na terenu |
| brojDresa | int | Broj dresa |
| timId | Long | Referenca na `Tim` kome igrač pripada (veza 1:N — Tim ima više igrača) |

### 3.3 `Teren`
| Atribut | Tip | Opis |
|---|---|---|
| id | Long | Jedinstveni identifikator |
| naziv | String | Naziv terena/stadiona |
| lokacija | String | Adresa/lokacija |
| kapacitet | int | Kapacitet gledalaca |
| tip | String | Tip podloge (npr. trava) |

### 3.4 `Sudija`
| Atribut | Tip | Opis |
|---|---|---|
| id | Long | Jedinstveni identifikator |
| ime, prezime | String | Ime i prezime sudije |
| licenca | String | Broj sudijske licence |
| godineIskustva | int | Godine sudijskog iskustva |

### 3.5 `Utakmica`
| Atribut | Tip | Opis |
|---|---|---|
| id | Long | Jedinstveni identifikator |
| datumVreme | LocalDateTime | Datum i vreme odigravanja |
| domacinId | Long | Referenca na `Tim` (domaći tim) |
| gostId | Long | Referenca na `Tim` (gostujući tim) |
| terenId | Long | Referenca na `Teren` na kom se igra |
| sudijaId | Long | Referenca na `Sudija` koja sudi |
| rezultat | String | Rezultat utakmice (`null` ako nije odigrana) |

**Veze između entiteta:** Tim 1—N Igrač; Utakmica N—1 Tim (domaćin), N—1 Tim (gost),
N—1 Teren, N—1 Sudija.

---

## 4. Arhitektura aplikacije

Aplikacija je organizovana po slojevitoj MVC arhitekturi, sa jasnim razdvajanjem
odgovornosti:

```
com.tournament
 ├── model/       → Model sloj: čiste POJO klase (Tim, Igrac, Teren, Sudija, Utakmica)
 ├── data/        → ApplicationData: centralno skladište podataka (application scope)
 ├── service/     → Servisni (biznis) sloj: poslovna logika nad podacima
 ├── controller/  → Kontroleri: prijem HTTP zahteva, priprema modela za pogled
 └── TurnirApplication.java → glavna klasa, pokreće Spring Boot aplikaciju

resources/
 ├── templates/   → Thymeleaf pogledi (nivo pogleda), organizovani po entitetima
 └── static/css/  → CSS stilizacija
```

- **Model sloj** (`model`) sadrži isključivo podatke entiteta — bez logike, bez
  zavisnosti od ostalih slojeva.
- **Servisni sloj** (`service`) sadrži poslovnu logiku: pronalaženje, čuvanje, brisanje
  i validaciju podataka. Svaki servis (`TimService`, `IgracService`, `TerenService`,
  `SudijaService`, `UtakmicaService`) operiše nad odgovarajućom mapom u
  `ApplicationData`.
- **Kontroler sloj** (`controller`) prima HTTP zahteve, poziva odgovarajuće servise i
  prosleđuje podatke Thymeleaf pogledima kroz `Model` objekat. Kontroleri ne sadrže
  poslovnu logiku niti direktno pristupaju skladištu podataka.
- **Pogled** (`templates`) je u potpunosti odvojen od poslovne logike — Thymeleaf
  šabloni samo prikazuju podatke koje im kontroler prosledi, uz zajedničke fragmente
  (`fragmenti.html` — zaglavlje, navigacija, footer) koji se ponovo koriste na svakoj
  stranici.

---

## 5. Skladištenje podataka (application scope)

Umesto baze podataka, aplikacija koristi **application scope** pristup, isti princip
kao u vežbama (lekcija 6): klasa `ApplicationData` je Spring `@Component` (singleton
bean), koji živi tokom celog životnog veka aplikacije i deli iste podatke svim
korisnicima/zahtevima — funkcionalno ekvivalentno `ServletContext` (application) scope
atributima.

`ApplicationData` sadrži po jednu `Map<Long, T>` za svaki entitet (timovi, igraci,
tereni, sudije, utakmice), kao i brojače (`AtomicLong`) za generisanje novih ID
vrednosti. Prilikom pokretanja aplikacije (`@PostConstruct`) mapa se puni početnim
demonstracionim podacima (4 tima, 5 igrača, 3 terena, 3 sudije, 3 utakmice), tako da je
aplikacija odmah upotrebljiva nakon pokretanja.

Svi servisi zavise isključivo od `ApplicationData` (Dependency Injection kroz
konstruktor) i preko njega čitaju/upisuju podatke — čime je poslovni sloj potpuno
odvojen od načina skladištenja podataka.

---

## 6. Pregled funkcionalnosti po entitetima (CRUD)

Svih pet entiteta (Tim, Igrač, Teren, Sudija, Utakmica) ima identičnu, potpunu CRUD
funkcionalnost, dostupnu kroz zaseban kontroler i skup Thymeleaf pogleda
(`lista.html`, `forma.html`, `detalji.html`) u sopstvenom podfolderu.

Primer ruta za entitet **Igrač** (`IgracController`, prefiks `/igraci`):

| HTTP metoda | Putanja | Akcija | Pogled |
|---|---|---|---|
| GET | `/igraci` | Prikaz liste svih igrača | `igrac/lista` |
| GET | `/igraci/{id}` | Prikaz detalja igrača (sa timom kom pripada) | `igrac/detalji` |
| GET | `/igraci/novi` | Prikaz forme za dodavanje novog igrača | `igrac/forma` |
| GET | `/igraci/izmeni/{id}` | Prikaz forme za izmenu postojećeg igrača | `igrac/forma` |
| POST | `/igraci/sacuvaj` | Čuvanje novog ili izmenjenog igrača | redirect `/igraci` |
| GET | `/igraci/obrisi/{id}` | Brisanje igrača | redirect `/igraci` |

Analogan skup ruta postoji i za `/timovi`, `/tereni`, `/sudije` i `/utakmice`
(`TimController`, `TerenController`, `SudijaController`, `UtakmicaController`), pri
čemu su forme povezane — npr. forma za igrača nudi izbor postojećeg tima, a forma za
utakmicu nudi izbor domaćeg i gostujućeg tima, terena i sudije iz padajućih lista.

Početna stranica (`HomeController`, ruta `/`) prikazuje zbirne statistike (broj
timova, igrača, terena, sudija, utakmica) i pregled svih zakazanih utakmica sa brzim
linkovima ka formama za dodavanje.

---

## 7. Izgled i stilizacija (CSS)

Korisnički interfejs je stilizovan kroz zajednički stylesheet
`static/css/style.css`, koji definiše izgled navigacije, kartica (`card`),
statističkih pločica na početnoj strani, tabela za prikaz listi, značaka (`badge`) za
status rezultata utakmice, dugmadi (`btn-primary`, `btn-outline`, `btn-info`,
`btn-danger`...) i formi za unos podataka. Svi pogledi dele isto zaglavlje i navigaciju
kroz Thymeleaf fragment `fragmenti.html`, čime je izgled aplikacije konzistentan na
svim stranicama.

---

## 8. Uputstvo za pokretanje aplikacije

### Preduslovi
- Instaliran JDK 17 (ili noviji, kompatibilan sa Spring Boot 3.2.0)
- Maven (aplikacija sadrži Maven Wrapper `mvnw.cmd`, pa lokalna Maven instalacija nije
  neophodna)

### Pokretanje iz komandne linije (Windows)

```
mvnw.cmd spring-boot:run
```

### Pokretanje iz IDE-a (npr. IntelliJ IDEA)

1. Otvoriti projekat kao Maven projekat (`pom.xml`)
2. Pokrenuti glavnu klasu `com.tournament.TurnirApplication`

Nakon pokretanja, aplikacija je dostupna na:

```
http://localhost:8080
```

Aplikacija pri startu automatski učitava demonstracione podatke, tako da je odmah
spremna za pregled i testiranje funkcionalnosti bez potrebe za ručnim unosom.

---

## 9. Uputstvo za korišćenje aplikacije

1. **Početna strana** (`/`) — prikazuje pregled statistike i raspored utakmica. Iz
   sekcije "Brzi Pristup" moguće je direktno otvoriti formu za dodavanje bilo kog
   entiteta.
2. **Navigacija** — u zaglavlju se nalaze linkovi ka listama: Timovi, Igrači, Tereni,
   Sudije, Utakmice.
3. **Pregled liste** — svaka lista prikazuje sve zapise datog entiteta u tabeli, sa
   dugmićima za Detalje, Izmenu i Brisanje, kao i dugmetom za dodavanje novog zapisa.
4. **Dodavanje zapisa** — klikom na "Dodaj" otvara se forma; nakon popunjavanja polja i
   klika na "Sačuvaj", zapis se dodaje u skladište i korisnik se vraća na listu.
5. **Izmena zapisa** — klikom na "Izmeni" u listi, forma se prikazuje popunjena
   postojećim vrednostima; nakon izmene i čuvanja, ažurira se postojeći zapis
   (prepoznat po ID-ju).
6. **Brisanje zapisa** — klikom na "Obriši" zapis se trajno uklanja iz skladišta
   podataka.
7. **Detalji zapisa** — klikom na "Detalji" (npr. kod utakmice) prikazuju se povezani
   podaci — nazivi timova, teren i sudija umesto samih ID vrednosti.

---

## 10. Zaključak

Projekat demonstrira primenu Spring Boot MVC arhitekture sa jasno razdvojenim
slojevima modela, servisa (biznis logike), kontrolera i pogleda, uz čuvanje podataka u
application scope-u umesto u relacionoj bazi. Implementirana je potpuna CRUD
funkcionalnost nad svih pet entiteta (Tim, Igrač, Teren, Sudija, Utakmica), uključujući
i međusobno povezivanje entiteta (npr. igrač–tim, utakmica–timovi/teren/sudija).
Korisnički interfejs je urađen u Thymeleaf-u i stilizovan CSS-om radi preglednosti i
lakšeg korišćenja.

Mogućnosti za dalje unapređenje uključuju: uvođenje prave baze podataka i Spring Data
JPA, validaciju unosa na nivou formi (Bean Validation), autentifikaciju i autorizaciju
korisnika (Spring Security), kao i REST API sloj za integraciju sa spoljnim klijentima.

**Link ka repozitorijumu:** https://github.com/andrija004/IT355-SportsTournament-MVC
