package ma.estl02.sportnews.controller;

import ma.estl02.sportnews.entity.Article;
import ma.estl02.sportnews.entity.Team;
import ma.estl02.sportnews.repository.ArticleRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    private String projectPath = System.getProperty("user.dir");


    @GetMapping("/news/{section}")
    public List<Article> getBySection(@PathVariable String section) {
        return articleRepository.findBySection(section);
    }

    @GetMapping("/news")
    public List<Article> getArticles(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return articleRepository.findByKeyword(keyword);
    }

    @PostMapping("/news/add-article")
    public ResponseEntity<Article> addArticle(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
                                              /*RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,*/
                                              @RequestParam("content") String content, @RequestParam("section") String section
    ) {

        // Create a new team object
        Article article = new Article();
        article.setContent(content);
        article.setSection(section);
        article.setTitle(title);
        article.setPublishDate(new Date());

        // Get the original filename and extension
        String originalFilename = file.getOriginalFilename();
        //String extension = FilenameUtils.getExtension(originalFilename);
        String uploadDir = projectPath+"/src/main/resources/news website/imgs/articles/";
        String filePath = uploadDir + originalFilename;
        try {
            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the logo file path for the team object
        article.setImageUrl(originalFilename);

        // Save the team object to the database
        articleRepository.save(article);

        return ResponseEntity.ok().body(article);
    }

    @PutMapping("/news/edit-article")
    public ResponseEntity<Article> editArticle(@RequestParam(name="file" ,required = false) MultipartFile file, @RequestParam("title") String title,
                                               @RequestParam("content") String content, @RequestParam("id") Long id
                                             , @RequestParam("section") String section
    ) {

        // Create a new team object
        Article article = articleRepository.findById(id).get();
        article.setContent(content);
        article.setTitle(title);
        article.setSection(section);
        if(file!=null) {
            // Get the original filename and extension
            String originalFilename = file.getOriginalFilename();
            // Save the file to the images folder in resources directory
            String uploadDir = projectPath+"/src/main/resources/news website/imgs/articles/";
            String filePath = uploadDir + originalFilename;
            try {
                Path path = Paths.get(filePath);
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set the logo file path for the team object
            article.setImageUrl(originalFilename);
        }
            // Save the team object to the database
            articleRepository.save(article);

        return ResponseEntity.ok().body(article);
    }

    @DeleteMapping("/news/delete-article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        articleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/news/get/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleRepository.findById(id).get();
    }

    @GetMapping(path = "/news/{id}/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getPhoto(@PathVariable Long id) throws Exception {
        Article article = articleRepository.findById(id).get();
        return Files.readAllBytes(Paths.get( article.getImageUrl()));
    }


}
