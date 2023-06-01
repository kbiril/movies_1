package be.vdab.movies.domain;

import be.vdab.movies.exceptions.SoldOutException;

import java.math.BigDecimal;

public class Film {
    private final long id;
    private final long genreId;
    private final String titel;
    private final int voorraad;
    private int gereserveerd;
    private final BigDecimal prijs;

    public Film(long id, long genreId, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public Film(long genreId, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this(0, genreId, titel, voorraad, gereserveerd, prijs);
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitel() {
        return titel;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
    public void reserveren () {
        if (gereserveerd < voorraad) {
            gereserveerd++;
        } else {
            throw new SoldOutException(titel);
        }
    }
}
