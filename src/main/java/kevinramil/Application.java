package kevinramil;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import kevinramil.Entities.Archivio;
import kevinramil.Entities.Libro;
import kevinramil.Entities.Utente;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-L5pu");

    public static void main(String[] args) {
        // Creo l'entity manager che gestirà tutte le operazioni con il database
        EntityManager em = emf.createEntityManager();

        Archivio archivio = new Archivio(em);

        // Faker che mi genera
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            String isbn = faker.code().isbn10();
            String titolo = faker.book().title();
            int anno = faker.number().numberBetween(1950, 2025);
            int pagine = faker.number().numberBetween(100, 1000);
            String autore = faker.book().author();
            String genere = faker.book().genre();

            Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);
            archivio.aggiungiElemento(libro);
            System.out.println("Aggiunto libro: " + libro);
        }

        // 4. Aggiungi 5 utenti fake
        for (int i = 0; i < 5; i++) {
            String nome = faker.name().firstName();
            String cognome = faker.name().lastName();
            // Data di nascita tra 18 e 65 anni fa
            Date fakeDate = faker.date().birthday(18, 65);
            LocalDate dataNascita = fakeDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int numeroTessera = faker.number().numberBetween(1000, 9999);

            Utente utente = new Utente(nome, cognome, dataNascita, numeroTessera);
            archivio.aggiungiUtente(utente);
            System.out.println("Aggiunto utente: " + utente);
        }


        // 2. Uso dei metodi di Archivio
        archivio.aggiungiElemento(new Libro("111", "Il Signore degli Anelli", 1954, 1200, "Tolkien", "Fantasy"));

        Utente utente = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 10), 0);
        archivio.aggiungiUtente(utente);

        archivio.prestaElemento(utente.getNumeroDiTessera(), "111", LocalDate.now());

        // ...altre operazioni con archivio...

        em.close();
        emf.close();

        // LO USO PER CONTROLLARE SE IL DATABASE È CORRETTAMENTE CONNESSO
        System.out.println("Ciao, sei connesso correttamente al database!");
    }
}
