package fr.eni.cave.repository;

import fr.eni.cave.bo.Client;
import fr.eni.cave.bo.client.Adresse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
public class AdresseRepositoryTest {

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    @Test
    public void addAdresse() {
        //Arrange

        Client client1 = Client.builder()
                .nom("Doe")
                .prenom("John")
                .pseudo("John Doe")
                .password("password")
                .build();



        Adresse adresse1 = Adresse.builder()
                .rue("cuicui")
                .ville("oiseau")
                .codePostal("54662")
                .client(client1)
                .build();

        client1.setAdresse(adresse1);
        clientRepository.save(client1);

        Adresse findAdresse = adresseRepository.findByClientNom("Doe");

        //Assert
        Assertions.assertNotNull(findAdresse);
        Assertions.assertEquals(adresse1.getRue(), findAdresse.getRue());

        System.out.println(findAdresse);
    }

    @Test
    @Transactional
    public void deleteClientWithAdresse() {
        // Arrange
        Client client1 = Client.builder()
                .nom("Doe")
                .prenom("John")
                .pseudo("johnny")
                .password("secret")
                .build();

        Adresse adresse1 = Adresse.builder()
                .rue("Rue des tests")
                .ville("Quimper")
                .codePostal("29000")
                .client(client1)
                .build();

        client1.setAdresse(adresse1);

        clientRepository.save(client1);

        String clientId = client1.getPseudo(); // PK = pseudo
        Integer adresseId = adresse1.getId();

        // Act
        clientRepository.deleteById(clientId);
//        adresseRepository.deleteById(adresseId);

        // Assert
//        Assertions.assertTrue(adresseRepository.findById(adresseId).isEmpty());
        Assertions.assertTrue(clientRepository.findById(clientId).isEmpty());

    }

    @Test
    @Transactional
    public void deleteAdresse() {
        // Arrange
        Client client1 = Client.builder()
                .nom("Doe")
                .prenom("John")
                .pseudo("johnny")
                .password("secret")
                .build();

        Adresse adresse1 = Adresse.builder()
                .rue("Rue des tests")
                .ville("Quimper")
                .codePostal("29000")
                .client(client1)
                .build();

        client1.setAdresse(adresse1);

        clientRepository.save(client1);

        String clientId = client1.getPseudo(); // PK = pseudo
        Integer adresseId = adresse1.getId();

        // Act
//        clientRepository.deleteById(clientId);
        adresseRepository.deleteById(adresseId);

        // Assert
        Assertions.assertTrue(adresseRepository.findById(adresseId).isEmpty());
//        Assertions.assertTrue(clientRepository.findById(clientId).isEmpty());

    }

}
