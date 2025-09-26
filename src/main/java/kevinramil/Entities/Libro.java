package kevinramil.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

// ENTITÀ
@Entity
public class Libro extends Catalogo {
    @Column(name = "autore")
    private String autore;

    @Column(name = "genere")
    private String genere;

    // LISTA COSTRUTTORI
    public Libro(String isbn, String titolo, int annoDiProduzione, int numeroDiPagine, String autore, String genere) {
        super(isbn, titolo, annoDiProduzione, numeroDiPagine);
        this.autore = autore;
        this.genere = genere;
    }

    // COSTRUTTORE VUOTO
    public Libro() {
    }

    // LISTA METODI

    // SETTER/GETTER
    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
