package at.technikum.statsservice.repositories;

import at.technikum.statsservice.entities.AuthorClicks;
import at.technikum.statsservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface AuthorClicksRepository extends JpaRepository<AuthorClicks, UUID> {

  AuthorClicks findByAuthorAndDate(Author author, LocalDate date);

  @Modifying
  @Query("update AuthorClicks ac SET ac.views = ac.views + 1 where ac.author = ?1 and ac.date = ?2")
  void incrementViews(Author author, LocalDate date);

}
