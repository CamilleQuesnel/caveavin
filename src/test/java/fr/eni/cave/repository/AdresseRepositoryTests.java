package fr.eni.cave.repository;

import fr.eni.cave.bo.Client;
import fr.eni.cave.bo.client.Adresse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

    @SpringBootTest
    @ActiveProfiles("test")
public class AdresseRepositoryTests {


        @Autowired
        private ClientRepository clientRepository;

        @Autowired
        private AdresseRepository adresseRepository;

        @Test
        @Transactional
        public void ajouterAdresseTest(){
            Adresse adresse = Adresse
                    .builder()
                    .rue("balbal")
                    .codepostal("29200")
                    .ville("Brest")
                    .build();

            Client client = Client
                    .builder()
                    .pseudo("lablaba")
                    .nom("dsfsdf")
                    .prenom("jlklkj")
                    .password("465sd4f")
                    .adresse(adresse)// passage par référence
                    .build();

            clientRepository.save(client);

            Client savedClient = clientRepository.findByPseudo(client.getPseudo());
            int adresseId = savedClient.getAdresse().getId();
            Assertions.assertTrue(adresseRepository.findById(adresseId).isPresent());

        }

        @Test
        public void suppresstionAdresseTest(){
            Adresse adresse = Adresse
                    .builder()
                    .rue("balbal")
                    .codepostal("29200")
                    .ville("Brest")
                    .build();

            Client client = Client
                    .builder()
                    .pseudo("lablaba")
                    .nom("dsfsdf")
                    .prenom("jlklkj")
                    .password("465sd4f")
                    .adresse(adresse)
                    .build();

            clientRepository.save(client);

            Client savedClient = clientRepository.findByPseudo(client.getPseudo());
            int  adresseId = savedClient.getAdresse().getId();
            clientRepository.delete(savedClient);

            Assertions.assertTrue(adresseRepository.findById(adresseId).isEmpty());
        }

        @Test
        public void orphanRemovalTest(){
            Adresse adresse = Adresse
                    .builder()
                    .rue("balbal")
                    .codepostal("29200")
                    .ville("Brest")
                    .build();

            Client client = Client
                    .builder()
                    .pseudo("lablaba")
                    .nom("dsfsdf")
                    .prenom("jlklkj")
                    .password("465sd4f")
                    .adresse(adresse)
                    .build();

            clientRepository.save(client);
            Client savedClient = clientRepository.findByPseudo(client.getPseudo());
            int idAdresse = savedClient.getAdresse().getId();
            savedClient.setAdresse(null);
            clientRepository.save(savedClient);

            Assertions.assertTrue(adresseRepository.findById(idAdresse).isEmpty());

        }

    }
