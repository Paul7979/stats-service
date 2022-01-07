package at.technikum.statsservice.service;

import at.technikum.statsservice.entities.SightViews;
import at.technikum.statsservice.model.Sight;
import at.technikum.statsservice.repositories.SightRepository;
import at.technikum.statsservice.repositories.SightViewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class SightViewService {

  private final SightViewsRepository sightViewsRepository;
  private final SightRepository sightRepository;

  public Map<Sight, Integer> getTopNSightsAndViewsToday(int limit) {
    var date = LocalDate.now();
    var topSightViews = sightViewsRepository.findByDateOrderByViewsDesc(date);
    return topSightViews.stream().limit(limit)
      .collect(LinkedHashMap::new, (map, sightViews) -> map.put(sightViews.getSight(), sightViews.getViews()), Map::putAll);
  }

  public Map<Sight, Integer> getAllSightsStatsForToday() {
    var date = LocalDate.now();
    var topSightViews = sightViewsRepository.findByDateOrderByViewsDesc(date);
    return topSightViews.stream()
      .collect(LinkedHashMap::new, (map, sightViews) -> map.put(sightViews.getSight(), sightViews.getViews()), Map::putAll);
  }


  @Transactional
  public void registerSightView(Sight sight, LocalDate date) {
    var storedSight = sightRepository.findByNameAndLocation(sight.getName(), sight.getLocation());
    if (storedSight == null) {
      storedSight = sightRepository.save(sight);
    }
    var sightView = sightViewsRepository.findBySightAndDate(storedSight, date);
    if (sightView == null) {
      sightViewsRepository.save(SightViews.builder().sight(storedSight).date(date).views(1).build());
      //log.info("Created sight views for {}", storedSight);
      return;
    }
    sightViewsRepository.incrementViews(storedSight, date);
    //log.info("Incremented sight views for {}", storedSight);
  }

}
