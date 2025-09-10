package fr.eni.cave;

import fr.eni.cave.bo.client.Client;
import fr.eni.cave.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Boot implements CommandLineRunner {

    private final ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        Client clientBob = Client.builder().pseudo("Bob l'éponge").prenom("bob").nom("l'éponge").password("azerty").build();
        clientRepository.save(clientBob);
    }
}
