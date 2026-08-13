# Sportski Turnir — IT355 Prvi projektni zadatak

Spring Boot MVC aplikacija za evidenciju sportskog (fudbalskog) turnira: timovi,
igrači, tereni, sudije i utakmice. Podaci se čuvaju u application scope-u
(in-memory), bez baze podataka.

Puna dokumentacija projekta (opis aplikacije, model podataka, arhitektura, uputstvo
za korišćenje) nalazi se u [`DOKUMENTACIJA.md`](DOKUMENTACIJA.md).

## Tehnologije

Java 17 · Spring Boot 3.2.0 · Spring MVC · Thymeleaf · CSS3 · Maven

## Pokretanje

```
mvnw.cmd spring-boot:run
```

Aplikacija je zatim dostupna na [http://localhost:8080](http://localhost:8080) i
automatski se puni demonstracionim podacima pri startu.

## Funkcionalnost

Potpun CRUD (dodavanje, pregled, izmena, brisanje) nad svih pet entiteta:
Tim, Igrač, Teren, Sudija, Utakmica.
