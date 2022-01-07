package at.technikum.statsservice.controller;

import at.technikum.statsservice.model.Sight;
import at.technikum.statsservice.service.SightViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "/stats")
@RequiredArgsConstructor
public class StatsController {

  private final SightViewService sightViewService;

  @GetMapping(path = "/topSights/{limit}")
  public Map<Sight, Integer> getTopSights(@PathVariable int limit) {
    return sightViewService.getTopNSightsAndViewsToday(limit);
  }

  @GetMapping(path = "/allSights")
  public Map<Sight, Integer> getAllSights() {
    return sightViewService.getAllSightsStatsForToday();
  }


}
