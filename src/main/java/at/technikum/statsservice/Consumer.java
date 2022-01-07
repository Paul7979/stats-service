package at.technikum.statsservice;

import at.technikum.statsservice.model.BlogPostViewedEvent;
import at.technikum.statsservice.service.AuthorClicksService;
import at.technikum.statsservice.service.SightViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Slf4j
public class Consumer {


  private final SightViewService sightViewService;
  private final AuthorClicksService authorClicksService;

  @KafkaListener(
    topics = "blogposts.views",
    containerFactory = "blogPostViewedConsumerKafkaListenerContainerFactory")
  public void consumeBlogPostViewedEvent(BlogPostViewedEvent blogPostViewedEvent) {
    log.info("Received {}", blogPostViewedEvent);
    var date = Instant.ofEpochMilli(blogPostViewedEvent.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDate();
    sightViewService.registerSightView(blogPostViewedEvent.getBlogPost().getSight(), date);
    authorClicksService.registerAuthorClick(blogPostViewedEvent.getBlogPost().getAuthor(), date);
  }
}
