package kevinramil.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import kevinramil.Entities.Rivista;
import kevinramil.Exception.NotFoundException;

import java.util.List;

public class RivistaDAO {
    private EntityManager em;

    public RivistaDAO(EntityManager em) {
        this.em = em;
    }

    // PER SALVARE
    public void save(Rivista rivista) {
        em.getTransaction().begin();
        em.persist(rivista);
        em.getTransaction().commit();
    }

    // PER CERCARE
    public Rivista findByIsbn(String isbn) {
        Rivista found = em.find(Rivista.class, isbn);
        if (found == null) throw new NotFoundException("Rivista con ISBN " + isbn + " non trovata.");
        return found;
    }

    public List<Rivista> findAll() {
        TypedQuery<Rivista> query = em.createQuery(
                "SELECT r FROM Rivista r", Rivista.class);
        return query.getResultList();
    }
}
