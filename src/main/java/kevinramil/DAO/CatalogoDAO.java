package kevinramil.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import kevinramil.Entities.Catalogo;
import kevinramil.Exception.NotFoundException;

import java.util.List;

public class CatalogoDAO {

    private final EntityManager em;

    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    // PER SALVARE
    public void save(Catalogo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();

        System.out.println("Elemento salvato correttamente!");
    }

    // PER CERCARE
    public Catalogo findByIsbn(String isbn) {
        Catalogo found = em.find(Catalogo.class, isbn);
        if (found == null) throw new NotFoundException("Elemento con ISBN " + isbn + " non trovato.");
        return found;
    }

    public List<Catalogo> findByAnno(int anno) {
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", Catalogo.class);
        return query.setParameter("anno", anno).getResultList();
    }

    public List<Catalogo> findByTitolo(String titolo) {
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE :titolo", Catalogo.class);
        return query.setParameter("titolo", "%" + titolo + "%").getResultList();
    }

    public List<Catalogo> findAll() {
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e", Catalogo.class);
        return query.getResultList();
    }
    
    // PER RIMUOVERE
    public void removeByIsbn(String isbn) {
        em.getTransaction().begin();
        Catalogo found = em.find(Catalogo.class, isbn);
        if (found == null) {
            em.getTransaction().rollback();
            throw new NotFoundException("Elemento con ISBN " + isbn + " non trovato.");
        }
        em.remove(found);
        em.getTransaction().commit();
    }


}
