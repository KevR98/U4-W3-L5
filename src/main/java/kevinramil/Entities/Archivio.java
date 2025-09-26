package kevinramil.Entities;

import jakarta.persistence.EntityManager;
import kevinramil.DAO.*;

import java.time.LocalDate;
import java.util.List;

public class Archivio {
    private final EntityManager em;
    private final CatalogoDAO elementoDao;
    private final LibroDAO libroDao;
    private final RivistaDAO rivistaDao;
    private final UtenteDAO utenteDao;
    private final PrestitoDAO prestitoDao;

    public Archivio(EntityManager em) {
        this.em = em;
        this.elementoDao = new CatalogoDAO(em);
        this.libroDao = new LibroDAO(em);
        this.rivistaDao = new RivistaDAO(em);
        this.utenteDao = new UtenteDAO(em);
        this.prestitoDao = new PrestitoDAO(em);
    }

    // Aggiungo elemento
    public void aggiungiElemento(Catalogo catalogo) {
        elementoDao.save(catalogo);
    }

    // Rimuovo elemento
    public void rimuoviElementoByIsbn(String isbn) {
        elementoDao.removeByIsbn(isbn);
    }

    // Ricerco per ISBN
    public Catalogo ricercaPerIsbn(String isbn) {
        return elementoDao.findByIsbn(isbn);
    }

    // Ricerco per anno di publicazione
    public List<Catalogo> ricercaPerAnno(int anno) {
        return elementoDao.findByAnno(anno);
    }

    // Ricerco per autore
    public List<Libro> ricercaLibriPerAutore(String autore) {
        return libroDao.findByAutore(autore);
    }

    // Ricerco per titolo
    public List<Catalogo> ricercaPerTitolo(String titolo) {
        return elementoDao.findByTitolo(titolo);
    }

    //
    public List<Catalogo> tuttiGliElementi() {
        return elementoDao.findAll();
    }

    // Utenti
    public void aggiungiUtente(Utente u) {
        utenteDao.save(u);
    }

    public Utente ricercaUtentePerTessera(int numeroTessera) {
        return utenteDao.findByNumeroTessera(numeroTessera);
    }

    // Ricerco gli elementi
    public void prestaElemento(int numeroTessera, String isbn, LocalDate dataInizio) {
        Utente utente = ricercaUtentePerTessera(numeroTessera);
        Catalogo elementoPrestato = ricercaPerIsbn(isbn);
        Prestito prestito = new Prestito(utente, elementoPrestato, dataInizio);
        prestitoDao.save(prestito);
    }

    //
    public List<Prestito> prestitiUtente(int numeroTessera) {
        return prestitoDao.findInPrestitoByNumeroTessera(numeroTessera);
    }

    // Ricerco per gli elementi scaduti
    public List<Prestito> prestitiScadutiNonRestituiti() {
        return prestitoDao.prestitiScadutiNonRestituiti();
    }
}
