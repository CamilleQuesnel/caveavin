package fr.eni.cave.repository;

import fr.eni.cave.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByPseudo(String pseudo);
}
