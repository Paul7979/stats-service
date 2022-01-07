package at.technikum.statsservice.repositories;

import at.technikum.statsservice.entities.SightViews;
import at.technikum.statsservice.model.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SightViewsRepository extends JpaRepository<SightViews, UUID> {

  SightViews findBySightAndDate(Sight sight, LocalDate date);

  List<SightViews> findByDateOrderByViewsDesc(LocalDate date);

  @Modifying
  @Query("update SightViews sv SET sv.views = sv.views + 1 where sv.sight = ?1 and sv.date = ?2")
  void incrementViews(Sight sight, LocalDate date);

}
