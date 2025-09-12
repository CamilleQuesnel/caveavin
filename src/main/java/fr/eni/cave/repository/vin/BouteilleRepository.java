package fr.eni.cave.repository.vin;

import fr.eni.cave.bo.vin.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {


    List<Bouteille> findByRegion(String region);


    List<Bouteille> findByCouleur(String couleur);

}
