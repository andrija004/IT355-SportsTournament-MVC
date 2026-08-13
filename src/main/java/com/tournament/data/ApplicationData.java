package com.tournament.data;

import com.tournament.model.*;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ApplicationData {

    private final Map<Long, Tim> timovi = new LinkedHashMap<>();
    private final Map<Long, Igrac> igraci = new LinkedHashMap<>();
    private final Map<Long, Teren> tereni = new LinkedHashMap<>();
    private final Map<Long, Sudija> sudije = new LinkedHashMap<>();
    private final Map<Long, Utakmica> utakmice = new LinkedHashMap<>();

    private final AtomicLong timCounter = new AtomicLong(1);
    private final AtomicLong igracCounter = new AtomicLong(1);
    private final AtomicLong terenCounter = new AtomicLong(1);
    private final AtomicLong sudijaCounter = new AtomicLong(1);
    private final AtomicLong utakmicaCounter = new AtomicLong(1);

    @PostConstruct
    public void initData() {
        // Timovi
        Tim t1 = new Tim(timCounter.getAndIncrement(), "FK Zvezda", "Beograd", 1945, "Marko Jovic");
        Tim t2 = new Tim(timCounter.getAndIncrement(), "FK Partizan", "Beograd", 1946, "Nikola Rakic");
        Tim t3 = new Tim(timCounter.getAndIncrement(), "FK Vojvodina", "Novi Sad", 1914, "Petar Simic");
        Tim t4 = new Tim(timCounter.getAndIncrement(), "FK Radnicki", "Nis", 1923, "Stefan Popovic");
        timovi.put(t1.getId(), t1);
        timovi.put(t2.getId(), t2);
        timovi.put(t3.getId(), t3);
        timovi.put(t4.getId(), t4);

        // Igraci
        Igrac i1 = new Igrac(igracCounter.getAndIncrement(), "Milan", "Petrovic", "Napred", 9, 1L);
        Igrac i2 = new Igrac(igracCounter.getAndIncrement(), "Luka", "Markovic", "Vezni", 8, 1L);
        Igrac i3 = new Igrac(igracCounter.getAndIncrement(), "Nemanja", "Stojanovic", "Odbrana", 4, 2L);
        Igrac i4 = new Igrac(igracCounter.getAndIncrement(), "Stefan", "Jovanovic", "Golman", 1, 2L);
        Igrac i5 = new Igrac(igracCounter.getAndIncrement(), "Djordje", "Nikolic", "Napred", 11, 3L);
        igraci.put(i1.getId(), i1);
        igraci.put(i2.getId(), i2);
        igraci.put(i3.getId(), i3);
        igraci.put(i4.getId(), i4);
        igraci.put(i5.getId(), i5);

        // Tereni
        Teren te1 = new Teren(terenCounter.getAndIncrement(), "Marakana", "Ljutice Bogdana 1a, Beograd", 55000, "Trava");
        Teren te2 = new Teren(terenCounter.getAndIncrement(), "Humska", "Humska 1, Beograd", 32500, "Trava");
        Teren te3 = new Teren(terenCounter.getAndIncrement(), "Karadjordje", "Maksima Gorkog 34, Novi Sad", 15754, "Trava");
        tereni.put(te1.getId(), te1);
        tereni.put(te2.getId(), te2);
        tereni.put(te3.getId(), te3);

        // Sudije
        Sudija s1 = new Sudija(sudijaCounter.getAndIncrement(), "Aleksandar", "Stankovic", "RS-001", 15);
        Sudija s2 = new Sudija(sudijaCounter.getAndIncrement(), "Vladimir", "Todorovic", "RS-002", 10);
        Sudija s3 = new Sudija(sudijaCounter.getAndIncrement(), "Milos", "Lazarevic", "RS-003", 7);
        sudije.put(s1.getId(), s1);
        sudije.put(s2.getId(), s2);
        sudije.put(s3.getId(), s3);

        // Utakmice
        Utakmica u1 = new Utakmica(utakmicaCounter.getAndIncrement(),
                LocalDateTime.of(2025, 9, 15, 18, 0), 1L, 2L, 1L, 1L, "2:1");
        Utakmica u2 = new Utakmica(utakmicaCounter.getAndIncrement(),
                LocalDateTime.of(2025, 9, 22, 20, 0), 3L, 4L, 3L, 2L, "0:0");
        Utakmica u3 = new Utakmica(utakmicaCounter.getAndIncrement(),
                LocalDateTime.of(2025, 10, 5, 16, 0), 2L, 3L, 2L, 3L, null);
        utakmice.put(u1.getId(), u1);
        utakmice.put(u2.getId(), u2);
        utakmice.put(u3.getId(), u3);
    }

    public Map<Long, Tim> getTimovi() { return timovi; }
    public Map<Long, Igrac> getIgraci() { return igraci; }
    public Map<Long, Teren> getTereni() { return tereni; }
    public Map<Long, Sudija> getSudije() { return sudije; }
    public Map<Long, Utakmica> getUtakmice() { return utakmice; }

    public long nextTimId() { return timCounter.getAndIncrement(); }
    public long nextIgracId() { return igracCounter.getAndIncrement(); }
    public long nextTerenId() { return terenCounter.getAndIncrement(); }
    public long nextSudijaId() { return sudijaCounter.getAndIncrement(); }
    public long nextUtakmicaId() { return utakmicaCounter.getAndIncrement(); }
}
