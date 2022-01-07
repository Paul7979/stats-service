package at.technikum.statsservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BlogPost {

  private Long id;

  private String headline;

  private String fullText;

  private LocalDate publicationDate;

  private Sight sight;

  private Author author;


}
