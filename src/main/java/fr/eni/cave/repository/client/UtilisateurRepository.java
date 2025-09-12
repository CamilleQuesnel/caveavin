package fr.eni.cave.repository.client;

import fr.eni.cave.bo.client.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {


    List<Utilisateur> findByPseudo(String pseudo);


    List<Utilisateur> findByPseudoAndPassword(String pseudo, String password);
}
