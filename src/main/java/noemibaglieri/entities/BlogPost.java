package noemibaglieri.entities;


import jakarta.persistence.*;
import lombok.*;
import java.util.Random;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String category;

    private String title;

    private String cover;

    private String content;

    private int readingTime;

    public BlogPost(String category, String title, String content, int readingTime, String cover) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.cover = cover;
    }
}
