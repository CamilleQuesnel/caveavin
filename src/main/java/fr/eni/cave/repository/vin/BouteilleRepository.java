package fr.eni.cave.repository.vin;

import fr.eni.cave.vin.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {
}
