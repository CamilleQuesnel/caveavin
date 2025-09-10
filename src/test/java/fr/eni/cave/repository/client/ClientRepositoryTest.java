package fr.eni.cave.repository.client;

import fr.eni.cave.bo.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;


    @Test
    public void addClientTest() {
        Client testclient = Client.builder().pseudo("pseudotest").nom("nomtest").password("azerty").prenom("prenomtest").build();
        clientRepository.save(testclient);

        Client savedTest = clientRepository.findClientByPseudo("pseudotest");

        Assertions.assertNotNull(savedTest);
        Assertions.assertEquals(savedTest.getPseudo(), testclient.getPseudo());
        System.out.println(savedTest);
    }

    @Test
    public void deleteClientTest() {
        //arrange
        Client testDeleteClient = Client.builder()
                .pseudo("pseudodelete")
                .nom("nomdeleteclient")
                .prenom("prenomdeleteclient")
                .password("azerty")
                .build();

        //Act
        clientRepository.save(testDeleteClient);
    Client deleteTest = clientRepository.findClientByPseudo("pseudodelete");


            clientRepository.delete(testDeleteClient);
        Client deleteTestdoitpasexister = clientRepository.findClientByPseudo("pseudodelete");

        //Assert
        Assertions.assertNull(deleteTestdoitpasexister);

    }
}
