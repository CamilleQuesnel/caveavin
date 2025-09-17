package fr.eni.cave.dal.client;

import fr.eni.cave.bo.client.Proprio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprioRepository extends JpaRepository<Proprio,String> {
}
