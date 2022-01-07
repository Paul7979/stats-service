package at.technikum.statsservice.entities;

import at.technikum.statsservice.model.Author;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "stats_t_author_clicks", uniqueConstraints = @UniqueConstraint(name = "unique_author_and_date", columnNames = {"date","fk_author"}))
public class AuthorClicks {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "fk_author")
  private Author author;

  private int views;

}
