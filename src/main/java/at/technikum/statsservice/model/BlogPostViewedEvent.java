package at.technikum.statsservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BlogPostViewedEvent {
  private BlogPost blogPost;
  private long timestamp;

  public static BlogPostViewedEvent of(BlogPost blogPost) {
    return new BlogPostViewedEvent(blogPost, System.currentTimeMillis());
  }

}
