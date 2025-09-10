package fr.eni.cave.repository;

import fr.eni.cave.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Client findByPseudo(String pseudo);
}
