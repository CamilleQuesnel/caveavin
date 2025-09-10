package fr.eni.cave.repository.vin;


import fr.eni.cave.vin.Bouteille;
import fr.eni.cave.vin.Couleur;
import fr.eni.cave.vin.Region;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class ManyToOneTest {
    @Autowired
    BouteilleRepository bouteilleRepository;

    @Autowired
    CouleurRepository couleurRepository;

    @Autowired
    RegionRepository regionRepository;

    Couleur rouge;
    Couleur blanc;
    Couleur rose;

    Region grandEst;
    Region paysDeLaLoire;
    Region nouvelleAquitaine;

    @BeforeEach
    public void initDB() {
        rouge = Couleur
                .builder()
                .nom("Rouge")
                .build();

        blanc = Couleur
                .builder()
                .nom("Blanc")
                .build();

        rose = Couleur
                .builder()
                .nom("Rosé")
                .build();

        couleurRepository.save(rouge);
        couleurRepository.save(blanc);
        couleurRepository.save(rose);

        grandEst =
                Region
                        .builder()
                        .nom("Grand Est")
                        .build();

        paysDeLaLoire =
                Region
                        .builder()
                        .nom("Pays de la Loire")
                        .build();

        nouvelleAquitaine =
                Region
                        .builder()
                        .nom("Nouvelle Aquitaine")
                        .build();

        regionRepository.save(grandEst);
        regionRepository.save(paysDeLaLoire);
        regionRepository.save(nouvelleAquitaine);
    }

    @AfterEach
    public void after() {
        bouteilleRepository.deleteAll();
    }

//    private List<Bouteille> jeuDeDonnees() {
//        List<Bouteille> bouteilles = new ArrayList<>();
//        bouteilles.add(Bouteille
//                .builder()
//                .nom("Blanc du DOMAINE ENI Ecole")
//                .millesime("2022")
//                .prix(23.95f)
//                .quantite(1298)
//                .region(paysDeLaLoire)
//                .couleur(blanc)
//                .build());
//        bouteilles.add(Bouteille
//                .builder()
//                .nom("Rouge du DOMAINE ENI Ecole")
//                .millesime("2018")
//                .prix(11.45f)
//                .quantite(987)
//                .region(paysDeLaLoire)
//                .couleur(rouge)
//                .build());
//        bouteilles.add(Bouteille
//                .builder()
//                .nom("Blanc du DOMAINE ENI Service")
//                .millesime("2022")
//                .prix(34)
//                .petillant(true)
//                .quantite(111)
//                .region(grandEst)
//                .couleur(blanc)
//                .build());
//        bouteilles.add(Bouteille
//                .builder()
//                .nom("Rouge du DOMAINE ENI Service")
//                .millesime("2012")
//                .prix(8.15f)
//                .quantite(344)
//                .region(paysDeLaLoire)
//                .couleur(rouge)
//                .build());
//        bouteilles.add(Bouteille
//                .builder()
//                .nom("Rosé du DOMAINE ENI")
//                .millesime("2020")
//                .prix(33)
//                .quantite(1987)
//                .region(nouvelleAquitaine)
//                .couleur(rose)
//                .build());
//        return bouteilles;
//    }

    @Transactional
    @Test
    public void test_save() {
        Bouteille bouteille = Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2022")
                .prix(23.95f)
                .quantite(1298)
                .region(paysDeLaLoire)
                .couleur(blanc)
                .build();

        Bouteille savedBouteille = bouteilleRepository.saveAndFlush(bouteille);

        Assertions.assertNotNull(savedBouteille.getRegion());
        Assertions.assertNotNull(savedBouteille.getCouleur());
        System.out.println(savedBouteille);
    }

    @Transactional
    @Test
    public void test_save_bouteille_regions_couleurs(){
        Bouteille bouteille1 = Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2023")
                .prix(22.95f)
                .quantite(1298)
                .region(paysDeLaLoire)
                .couleur(blanc)
                .build();

        Bouteille bouteille2 = Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2020")
                .prix(53.95f)
                .quantite(1298)
                .region(paysDeLaLoire)
                .couleur(blanc)
                .build();

        Bouteille bouteille3 = Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2002")
                .prix(123.95f)
                .quantite(1298)
                .region(paysDeLaLoire)
                .couleur(blanc)
                .build();

        Bouteille savedBouteille1 = bouteilleRepository.saveAndFlush(bouteille1);
        Bouteille savedBouteille2 = bouteilleRepository.saveAndFlush(bouteille2);
        Bouteille savedBouteille3 = bouteilleRepository.saveAndFlush(bouteille3);
        System.out.println(savedBouteille1);
        System.out.println(savedBouteille2);
        System.out.println(savedBouteille3);
        Assertions.assertNotNull(savedBouteille1.getRegion());
        Assertions.assertNotNull(savedBouteille1.getCouleur());
        Assertions.assertNotNull(savedBouteille2.getRegion());
        Assertions.assertNotNull(savedBouteille2.getCouleur());
        Assertions.assertNotNull(savedBouteille3.getRegion());
        Assertions.assertNotNull(savedBouteille3.getCouleur());
    }

    @Test
    @Transactional
    public void test_delete() {
        Bouteille bouteille4 = Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("1999")
                .prix(23.95f)
                .quantite(1298)
                .region(paysDeLaLoire)
                .couleur(blanc)
                .build();

        Bouteille savedBouteille = bouteilleRepository.saveAndFlush(bouteille4);
        bouteilleRepository.delete(savedBouteille);
        System.out.println("Couleurs : " + couleurRepository.findAll());
        System.out.println("Regions : " + regionRepository.findAll());
        Assertions.assertFalse(bouteilleRepository.existsById(savedBouteille.getId()));
        Assertions.assertTrue(couleurRepository.existsById(savedBouteille.getId()));
        Assertions.assertTrue(regionRepository.existsById(savedBouteille.getId()));
    }
}


