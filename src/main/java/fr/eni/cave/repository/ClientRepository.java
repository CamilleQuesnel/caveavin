package fr.eni.cave.repository;

import fr.eni.cave.bo.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Client findByPseudo(String pseudo);

    Client findClientByPseudo(String pseudo);
}
