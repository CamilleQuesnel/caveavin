package fr.eni.cave.repository.client;


import fr.eni.cave.bo.client.Client;
import fr.eni.cave.bo.client.LignePanier;
import fr.eni.cave.bo.client.Proprio;
import fr.eni.cave.bo.client.Utilisateur;
import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.repository.vin.BouteilleRepository;
import fr.eni.cave.repository.vin.CouleurRepository;
import fr.eni.cave.repository.vin.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TestManyToOnePanierBouteille {
    @Autowired
    private BouteilleRepository bouteilleRepository;
    @Autowired
    private LignePanierRepository lignePanierRepository;


    Region paysDeLaLoire;
    Couleur blanc;
    List<Bouteille> bouteilles;
    @Autowired
    private CouleurRepository couleurRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @BeforeEach
    void initDB() {
        jeuDeDonneesBouteilles();
        jeuDeDonneesUtilisateur();
    }

    private void jeuDeDonneesBouteilles() {
        final Couleur rouge = Couleur
                .builder()
                .nom("Rouge")
                .build();

        blanc = Couleur
                .builder()
                .nom("Blanc")
                .build();

        final Couleur rose = Couleur
                .builder()
                .nom("Rosé")
                .build();


        couleurRepository.save(rouge);
        couleurRepository.save(rose);
        couleurRepository.save(blanc);

        final Region grandEst = Region
                .builder()
                .nom("Grand Est")
                .build();

        paysDeLaLoire = Region
                .builder()
                .nom("Pays de la Loire")
                .build();

        final Region nouvelleAquitaine = Region
                .builder()
                .nom("Nouvelle-Aquitaine")
                .build();


        regionRepository.save(grandEst);
        regionRepository.save(nouvelleAquitaine);
        regionRepository.save(nouvelleAquitaine);

        bouteilles = new ArrayList<>();
        bouteilles.add(Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2022")
                .prix(23.95f)
                .quantite(1298)
                .region(paysDeLaLoire)
                .couleur(blanc)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Rouge du DOMAINE ENI Ecole")
                .millesime("2018")
                .prix(11.45f)
                .quantite(987)
                .region(paysDeLaLoire)
                .couleur(rouge)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Service")
                .millesime("2022")
                .prix(34)
                .petillant(true)
                .quantite(111)
                .region(grandEst)
                .couleur(blanc)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Rouge du DOMAINE ENI Service")
                .millesime("2012")
                .prix(8.15f)
                .quantite(344)
                .region(paysDeLaLoire)
                .couleur(rouge)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Rosé du DOMAINE ENI")
                .millesime("2020")
                .prix(33)
                .quantite(1987)
                .region(nouvelleAquitaine)
                .couleur(rose)
                .build());

        bouteilles.forEach(e -> {
            bouteilleRepository.save(e);
            // Vérification de l'identifiant
//            assertThat(e.getId()).isGreaterThan(0);
        });


    }

    private void jeuDeDonneesUtilisateur() {
        final List<Utilisateur> utilisateurs = new ArrayList<>();
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
    public void test_save() {
        System.out.println(bouteilles);
        Bouteille b1 = bouteilles.get(0);
        Bouteille b2 = bouteilles.get(1);

        LignePanier lp1 = LignePanier.builder()
                .qte_commande(2)
                .bouteille(b1)
                .build();

        LignePanier lp2 = LignePanier.builder()
                .qte_commande(5)
                .bouteille(b2)
                .build();

        lignePanierRepository.save(lp1);
        lignePanierRepository.save(lp2);

        // Vérification
        List<LignePanier> lignes = lignePanierRepository.findAll();
        System.out.println("____________________________________________");
        lignes.forEach(lp ->
                System.out.println(lp.getQte_commande() + " bouteille(s) " + lp.getBouteille().getNom() + " dont le prix est " + lp.getBouteille().getPrix() + " € ")
        );

        System.out.println("____________________________________________");
        System.out.println(lignes);
        assertThat(lignes.get(0).getBouteille().getNom()).isEqualTo("Blanc du DOMAINE ENI Ecole");
        assertThat(lignes.get(1).getBouteille().getNom()).isEqualTo("Rouge du DOMAINE ENI Ecole");

    }

    @Transactional
    @Test
    public void test_delete() {

        Bouteille b3 = bouteilles.get(2);
        Bouteille bouteilleADelete = bouteilleRepository.saveAndFlush(b3);
        LignePanier lp3 = LignePanier.builder()
                .qte_commande(7)
                .bouteille(bouteilleADelete)
                .build();
        lignePanierRepository.saveAndFlush(lp3);

        System.out.println(bouteilleADelete);

        lignePanierRepository.delete(lp3);
        Assertions.assertEquals(b3.getId(), bouteilleADelete.getId()); // la même bouteille
        Assertions.assertTrue(bouteilleRepository.existsById(bouteilleADelete.getId())); // la bouteille existe encore
        Assertions.assertFalse(lignePanierRepository.existsById(lp3.getId())); // la ligne a bien été supprimée

    }
}
