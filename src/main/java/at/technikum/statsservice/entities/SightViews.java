package at.technikum.statsservice.entities;

import at.technikum.statsservice.model.Sight;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stats_t_sight_views", uniqueConstraints = @UniqueConstraint(name = "unique_sight_and_date", columnNames = {"date","fk_sight"}))
public class SightViews {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "fk_sight")
  private Sight sight;

  private int views;

}
