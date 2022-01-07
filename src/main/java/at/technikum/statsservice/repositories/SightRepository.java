package at.technikum.statsservice.repositories;

import at.technikum.statsservice.model.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightRepository extends JpaRepository<Sight, Long> {
   Sight findByNameAndLocation(String name, String location);
}
