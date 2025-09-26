package kevinramil.Entities;

import jakarta.persistence.*;

// ENTITÀ
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalogo {
    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "anno_di_produzione")
    private int annoDiProduzione;

    @Column(name = "numero_di_pagine")
    private int numeroDiPagine;

    // LISTA COSTRUTTORI
    public Catalogo(String isbn, String titolo, int annoDiProduzione, int numeroDiPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.numeroDiPagine = numeroDiPagine;
        this.annoDiProduzione = annoDiProduzione;
    }

    // COSTRUTTORE VUOTO
    public Catalogo() {
    }

    // LISTA METODI

    // SETTER/GETTER

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiProduzione() {
        return annoDiProduzione;
    }

    public void setAnnoDiProduzione(int annoDiProduzione) {
        this.annoDiProduzione = annoDiProduzione;
    }

    public int getNumeroDiPagine() {
        return numeroDiPagine;
    }

    public void setNumeroDiPagine(int numeroDiPagine) {
        this.numeroDiPagine = numeroDiPagine;
    }
}
