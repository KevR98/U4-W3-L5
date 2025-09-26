package kevinramil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-L5pu");

    public static void main(String[] args) {

        // Creo l'entity manager che gestirà tutte le operazioni con il database
        EntityManager em = emf.createEntityManager();

        // LO USO PER CONTROLLARE SE IL DATABASE È CORRETTAMENTE CONNESSO
        System.out.println("Ciao, sei connesso correttamente al database!");
    }
}
