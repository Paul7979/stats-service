package at.technikum.statsservice.service;

import at.technikum.statsservice.entities.AuthorClicks;
import at.technikum.statsservice.model.Author;
import at.technikum.statsservice.repositories.AuthorClicksRepository;
import at.technikum.statsservice.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorClicksService {

  private final AuthorRepository authorRepository;
  private final AuthorClicksRepository authorClicksRepository;

  @Transactional
  public void registerAuthorClick(Author author, LocalDate date) {
    var storedAuthor = authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());
    if (storedAuthor == null) {
      storedAuthor = authorRepository.save(author);
    }
    var authorClicksByDate = authorClicksRepository.findByAuthorAndDate(storedAuthor, date);
    if (authorClicksByDate == null) {
      authorClicksRepository.save(AuthorClicks.builder().author(storedAuthor).date(date).views(1).build());
      //log.info("Created author clicks for {}", storedAuthor);
      return;
    }
    authorClicksRepository.incrementViews(storedAuthor, date);
    //log.info("Incremented author clicks for {}", storedAuthor);
  }


}
