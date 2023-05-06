package ma.estl02.sportnews.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id ;
    private String title;
    private String content;
    private String section;
    private String imageUrl;
    @Temporal(TemporalType.DATE)
    private Date publishDate;
}
