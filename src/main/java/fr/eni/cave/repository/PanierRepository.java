package fr.eni.cave.repository;

import fr.eni.cave.bo.client.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Integer> {

}
