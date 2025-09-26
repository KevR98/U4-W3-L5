package kevinramil.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

// ENTITÀ
@Entity
public class Rivista extends Catalogo {
    @Column(name = "periodicità")
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    // LISTA COSTRUTTORI
    public Rivista(String isbn, String titolo, int annoDiProduzione, int numeroDiPagine, Periodicita periodicita) {
        super(isbn, titolo, annoDiProduzione, numeroDiPagine);
        this.periodicita = periodicita;
    }

    // COSTRUTTORE VUOTO
    public Rivista() {
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    // LISTA METODI

    // SETTER/GETTER
    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
