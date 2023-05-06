package ma.estl02.sportnews.repository;

import ma.estl02.sportnews.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "*")
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.title LIKE %:keyword% OR a.content LIKE %:keyword% OR a.section LIKE %:keyword% ORDER BY a.publishDate asc ")
    List<Article> findByKeyword(@Param("keyword") String keyword);

    List<Article> findBySection(String section);
}
