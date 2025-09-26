package kevinramil.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import kevinramil.Entities.Prestito;
import kevinramil.Exception.NotFoundException;

import java.util.List;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    // PER SALVARE
    public void save(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    // PER CERCARE
    public Prestito findById(Long id) {
        Prestito p = em.find(Prestito.class, id);
        if (p == null) throw new NotFoundException("Prestito con id " + id + " non trovato.");
        return p;
    }

    public List<Prestito> findInPrestitoByNumeroTessera(int numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :nt AND p.dataRestituzioneEffettiva IS NULL",
                Prestito.class);
        return query.setParameter("nt", numeroTessera).getResultList();
    }

    public List<Prestito> prestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < CURRENT_DATE",
                Prestito.class);
        return query.getResultList();
    }

    public List<Prestito> findAll() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p", Prestito.class);
        return query.getResultList();
    }
}
