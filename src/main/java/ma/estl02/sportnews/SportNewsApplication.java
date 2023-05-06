package ma.estl02.sportnews;

import ma.estl02.sportnews.entity.Article;
import ma.estl02.sportnews.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SportNewsApplication implements ApplicationRunner {

    public SportNewsApplication(ArticleRepository articleRepository, TeamRepository teamRepository, MatchhRepository matchhRepository) {
        this.articleRepository = articleRepository;
        this.teamRepository = teamRepository;
        this.matchhRepository = matchhRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SportNewsApplication.class, args);
    }

    public final ArticleRepository articleRepository;
    public final TeamRepository teamRepository;
    public final MatchhRepository matchhRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        articleRepository.save(new Article(null,"jjj","kfgisdfghdfgjisd","football","jdfhgh",new Date()));
//        articleRepository.save(new Article(null,"khkhk","fngdjfgkjdglx","football","lljkj",new Date()));
//        articleRepository.save(new Article(null,"mnmnm","kgnsdfgjadrmg","football","mbnvhfh",new Date()));
//        articleRepository.save(new Article(null,"lklkl","idrgj895ut5utrj","football","kfkgjdf",new Date()));
//        articleRepository.save(new Article(null,",m,m,","dfgiosg95rgrojf","football","nfgklsjdio",new Date()));
//        articleRepository.save(new Article(null,"bvbvb","fgknsie5j9ira0t94ea","football","jdkfgnfhgh",new Date()));




    }
}
