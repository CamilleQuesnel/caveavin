package fr.eni.cave.repository.client;

import fr.eni.cave.bo.client.Adresse;
import fr.eni.cave.bo.client.Client;
import fr.eni.cave.bo.client.Proprio;
import fr.eni.cave.bo.client.Utilisateur;
import fr.eni.cave.dal.client.ClientRepository;
import fr.eni.cave.dal.client.ProprioRepository;
import fr.eni.cave.dal.client.UtilisateurRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class TestHeritage {

@Autowired
private ClientRepository clientRepository;
@Autowired
private UtilisateurRepository utilisateurRepository;
@Autowired
private ProprioRepository proprioRepository;


    @Test
    public void findAllUtilisateursTest(){
        Utilisateur utilisateur1 = (Utilisateur
                .builder()
                .pseudo("harrisonford@email.fr")
                .password("IndianaJones3")
                .nom("Ford")
                .prenom("Harrison")
                .build());

        Utilisateur utilisateur2 = (Proprio
                .builder()
                .pseudo("georgelucas@email.fr")
                .password("Réalisateur&Producteur")
                .nom("Lucas")
                .prenom("George")
                .siret("12345678901234")
                .build());

        //Adresse pour le client 3 qui est obligé d'avoir une adresse
        Adresse adresse = Adresse.builder().rue("rue du pétage de cable").codepostal("123145").ville("SUPERVILLE").build();

        Utilisateur utilisateur3 = (Client
                .builder()
                .pseudo("natalieportman@email.fr")
                .password("MarsAttacks!")
                .nom("Portman")
                .prenom("Natalie")
                .adresse(adresse)
                .build());

        utilisateurRepository.save(utilisateur1);
        utilisateurRepository.save(utilisateur2);
        utilisateurRepository.save(utilisateur3);

        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

        Assertions.assertEquals(3, utilisateurs.size());

        System.out.println(utilisateurs);
    }

    @Test
    public void findAllClientTest(){
        Utilisateur utilisateur1 = (Utilisateur
                .builder()
                .pseudo("harrisonford@email.fr")
                .password("IndianaJones3")
                .nom("Ford")
                .prenom("Harrison")
                .build());

        Utilisateur utilisateur2 = (Proprio
                .builder()
                .pseudo("georgelucas@email.fr")
                .password("Réalisateur&Producteur")
                .nom("Lucas")
                .prenom("George")
                .siret("12345678901234")
                .build());

        Adresse adresse = Adresse.builder().rue("rue du pétage de cable").codepostal("123145").ville("SUPERVILLE").build();
        Utilisateur utilisateur3 = (Client
                .builder()
                .pseudo("natalieportman@email.fr")
                .password("MarsAttacks!")
                .nom("Portman")
                .prenom("Natalie")
                .adresse(adresse)
                .build());

        utilisateurRepository.save(utilisateur1);
        utilisateurRepository.save(utilisateur2);
        utilisateurRepository.save(utilisateur3);

        List<Client> clients = clientRepository.findAll();
        Assertions.assertEquals(1, clients.size());


        System.out.println(clients);
    }

    @Test
    public void findAllProprioTest(){
        Utilisateur utilisateur1 = (Utilisateur
                .builder()
                .pseudo("harrisonford@email.fr")
                .password("IndianaJones3")
                .nom("Ford")
                .prenom("Harrison")
                .build());

        Utilisateur utilisateur2 = (Proprio
                .builder()
                .pseudo("georgelucas@email.fr")
                .password("Réalisateur&Producteur")
                .nom("Lucas")
                .prenom("George")
                .siret("12345678901234")
                .build());

        Adresse adresse = Adresse.builder().rue("rue du pétage de cable").codepostal("123145").ville("SUPERVILLE").build();
        Utilisateur utilisateur3 = (Client
                .builder()
                .pseudo("natalieportman@email.fr")
                .password("MarsAttacks!")
                .nom("Portman")
                .prenom("Natalie")
                .adresse(adresse)
                .build());

        utilisateurRepository.save(utilisateur1);
        utilisateurRepository.save(utilisateur2);
        utilisateurRepository.save(utilisateur3);

        List<Proprio> proprios = proprioRepository.findAll();
        Assertions.assertEquals(1, proprios.size());


        System.out.println(proprios);
    }


}
