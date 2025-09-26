package kevinramil.DAO;

import jakarta.persistence.EntityManager;
import kevinramil.Entities.Utente;
import kevinramil.Exception.NotFoundException;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    // PER SALVARE
    public void save(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    // PER CERCARE
    public Utente findByNumeroTessera(int numeroTessera) {
        Utente found = em.find(Utente.class, numeroTessera);
        if (found == null) throw new NotFoundException("Utente con numero tessera " + numeroTessera + " non trovato.");
        return found;
    }
}
