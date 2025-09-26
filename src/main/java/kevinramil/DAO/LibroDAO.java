package kevinramil.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import kevinramil.Entities.Libro;
import kevinramil.Exception.NotFoundException;

import java.util.List;

public class LibroDAO {
    private EntityManager em;

    public LibroDAO(EntityManager em) {
        this.em = em;
    }

    // PER SALVARE
    public void save(Libro book) {
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    // PER CERCARE
    public Libro findByIsbn(String isbn) {
        Libro found = em.find(Libro.class, isbn);
        if (found == null) throw new NotFoundException("Libro con ISBN " + isbn + " non trovato.");
        return found;
    }

    public List<Libro> findByAutore(String autore) {
        TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l WHERE LOWER(l.autore) = :autore", Libro.class);
        return query.setParameter("autore", autore.toLowerCase()).getResultList();
    }

    public List<Libro> findAll() {
        TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l", Libro.class);
        return query.getResultList();
    }
}
