package fr.eni.cave.repository.client;

import fr.eni.cave.bo.client.*;
import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.dal.client.AdresseRepository;
import fr.eni.cave.dal.client.ClientRepository;
import fr.eni.cave.dal.client.PanierRepository;
import fr.eni.cave.dal.client.UtilisateurRepository;
import fr.eni.cave.dal.vin.BouteilleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TestManyToOnePanierClient {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    AdresseRepository adresseRepository;


    @BeforeEach
    void initDB() {
        initBouteilles();
        jeuDeDonnees();
        jeuDeDonneesUtilisateur();
    }
    private List<Panier> paniers;
    private List<Utilisateur> utilisateurs;

    public void initBouteilles() {
        final List<Couleur> couleurs = new ArrayList<>();
        couleurs.add(Couleur
                .builder()
                .nom("Blanc")
                .build());
        couleurs.add(Couleur
                .builder()
                .nom("Rouge")
                .build());

        couleurs.forEach(item -> {
            entityManager.persist(item);
            assertThat(item.getId()).isGreaterThan(0);
        });
        entityManager.flush();

        final List<Region> regions = new ArrayList<>();
        regions.add(Region
                .builder()
                .nom("Pays de la Loire")
                .build());

        regions.add(Region
                .builder()
                .nom("Grand Est")
                .build());

        regions.forEach(item -> {
            entityManager.persist(item);
            assertThat(item.getId()).isGreaterThan(0);
        });
        entityManager.flush();

        final List<Bouteille> bouteilles = new ArrayList<>();
        bouteilles.add(Bouteille
                .builder()
                .nom("DOMAINE ENI Ecole")
                .millesime("2022")
                .prix(11.45f)
                .quantite(1298)
                .region(regions.get(0))
                .couleur(couleurs.get(0))
                .build());

        bouteilles.add(Bouteille
                .builder()
                .nom("DOMAINE ENI Service")
                .millesime("2015")
                .prix(23.95f)
                .quantite(2998)
                .region(regions.get(1))
                .couleur(couleurs.get(1))
                .build());

        bouteilles.forEach(item -> {
            entityManager.persist(item);
            assertThat(item.getId()).isGreaterThan(0);
        });
        entityManager.flush();
    }

//TODO

    private void jeuDeDonnees() {
        final List<Bouteille> bouteilles = bouteilleRepository.findAll();
        assertThat(bouteilles).isNotNull();
        assertThat(bouteilles.size()).isEqualTo(2);

        paniers = new ArrayList<>(); // <--- on initialise l'attribut ici

        final Panier p1 = new Panier();
        int qte1 = 3;
        final Bouteille b1 = bouteilles.get(0);
        final LignePanier lp1 = LignePanier
                .builder()
                .bouteille(b1)
                .qte_commande(qte1)
                .prix(qte1 * b1.getPrix())
                .build();
        p1.getLignes().add(lp1);
        p1.setPrixTotal(lp1.getPrix());
        paniers.add(p1);

        final Panier p2 = new Panier();
        int qte2 = 10;
        final Bouteille b2 = bouteilles.get(1);
        final LignePanier lp2 = LignePanier
                .builder()
                .bouteille(b2)
                .qte_commande(qte2)
                .prix(qte2 * b2.getPrix())
                .build();
        p2.getLignes().add(lp2);
        p2.setPrixTotal(lp2.getPrix());
        paniers.add(p2);
    }

    private void jeuDeDonneesUtilisateur() {
        utilisateurs = new ArrayList<>(); // <--- on initialise l'attribut de classe

        utilisateurs.add(Utilisateur
                .builder()
                .pseudo("harrisonford@email.fr")
                .password("IndianaJones3")
                .nom("Ford")
                .prenom("Harrison")
                .build());

        utilisateurs.add(Proprio
                .builder()
                .pseudo("georgelucas@email.fr")
                .password("Réalisateur&Producteur")
                .nom("Lucas")
                .prenom("George")
                .build());

        utilisateurs.add(Client
                .builder()
                .pseudo("natalieportman@email.fr")
                .password("MarsAttacks!")
                .nom("Portman")
                .prenom("Natalie")
                .build());

        // Contexte de la DB
        utilisateurs.forEach(e -> {
            utilisateurRepository.save(e);
        });
    }

    @Test
    public void test_save_1panier(){//sauvegarde 1 panier et son client
        Panier p1 = paniers.get(0);
        System.out.println(p1);
        Utilisateur u1 = utilisateurs.get(2);
        System.out.println(u1);
        assertThat(u1).isInstanceOf(Client.class);
        Adresse adresse = Adresse
                .builder()
                .rue("balbal")
                .codepostal("29200")
                .ville("Brest")
                .build();

        if (u1 instanceof Client client) {
            client.setAdresse(adresse);
            utilisateurRepository.save(client);
        }
        System.out.println(u1);

        if (u1 instanceof Client client) {
        p1.setClient((Client) u1);

            panierRepository.saveAndFlush(p1);
        }
        System.out.println("-------------------------");
        System.out.println(p1);

        // Vérifications
        assertThat(p1.getClient()).isNotNull();
        assertThat(p1.getClient()).isEqualTo(u1);

        // Vérif depuis la base
        Panier saved = panierRepository.findById(p1.getId()).orElseThrow();
        assertThat(saved.getClient()).isNotNull();
        assertThat(saved.getClient().getPseudo()).isEqualTo("natalieportman@email.fr");
    }

    @Test
    public void test_save_paniers_unClient(){//+sieurs paniers d'1 client

        Panier p1 = paniers.get(0);
        Panier p2 = paniers.get(1);
        System.out.println(p1);
        Utilisateur u1 = utilisateurs.get(2);
        System.out.println(u1);

        p1.setClient((Client) u1);
        p2.setClient((Client) u1);
        panierRepository.saveAndFlush(p1);
        panierRepository.saveAndFlush(p2);


    }

    @Test
    public void delete(){}//delete le client mais pas les paniers
}
