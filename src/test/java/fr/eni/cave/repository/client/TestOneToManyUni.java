package fr.eni.cave.repository.client;

import fr.eni.cave.bo.client.LignePanier;
import fr.eni.cave.bo.client.Panier;
import fr.eni.cave.dal.client.LignePanierRepository;
import fr.eni.cave.dal.client.PanierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class TestOneToManyUni {

    @Autowired
    LignePanierRepository lignePanierRepository;
    @Autowired
    PanierRepository panierRepository;

    Panier panier = new Panier();
    LignePanier lp = new LignePanier();


    @BeforeEach
    public void setUp() {

        panier = new Panier();

        lp = LignePanier
                .builder()
                .qte_commande(3)
                .prix(3 * 11.45f)
                .build();

        panier.getLignes().add(lp);
        panier.setPrixTotal(lp.getPrix());

    }

    @Test
    @Transactional
    public void test_save_nouvelleLigne_NouveauPanier() {

        Panier saved = panierRepository.save(panier);
//        System.out.println(panier.getPrixTotal());
        System.out.println(saved);
        Assertions.assertNotNull(saved.getId());
    }

    @Transactional
    @Test
    public void test_save_nouvelleLigne_Panier() {
        panierRepository.save(panier);

        LignePanier lp2 = LignePanier
                .builder()
                .qte_commande(3)
                .prix(3 * 11.45f)
                .build();
        panier.getLignes().add(lp2);
        panierRepository.save(panier);

        System.out.println(panier);
        Assertions.assertNotNull(panier.getId());
    }


    @Test
    public void test_delete() {
        panierRepository.save(panier);

        panierRepository.delete(panier);

        Assertions.assertFalse(lignePanierRepository.existsById(panier.getId()));


    }

    @Test
    public void test_orphanRemoval() {

        panierRepository.save(panier);
        int lignePanierId = panier.getId();
        panier.getLignes().remove(lp);
        panierRepository.save(panier);
        Assertions.assertFalse(lignePanierRepository.existsById(lignePanierId));
    }

}



