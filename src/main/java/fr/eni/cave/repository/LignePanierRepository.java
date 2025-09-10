package fr.eni.cave.repository;

import fr.eni.cave.bo.client.LignePanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LignePanierRepository extends JpaRepository<LignePanier, Integer> {
}
