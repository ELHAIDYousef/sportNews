package ma.estl02.sportnews.controller;

import ma.estl02.sportnews.entity.Team;
import ma.estl02.sportnews.repository.TeamRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TeamController {


    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;

    }

    private String projectPath = System.getProperty("user.dir");


    @GetMapping("/teams")
    public List<Team> getTeams(@RequestParam(name = "team", defaultValue = "") String team) {
        return teamRepository.findByNameContains(team);
    }

    @PostMapping("/teams/add-team")
    public ResponseEntity<Team> saveTeam(@RequestParam("file") MultipartFile file, @RequestParam("name") String name
        ) {

        // Create a new team object
        Team team = new Team();
        team.setName(name);

        // Get the original filename and extension
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        // Save the file to the images folder in resources directory
        String uploadDir = projectPath +"/src/main/resources/news website/imgs/logo/";
        String filePath = uploadDir + originalFilename;
        try {
            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the logo file path for the team object
        team.setLogoUrl( originalFilename);

        // Save the team object to the database
        teamRepository.save(team);

        return ResponseEntity.ok().body(team);
    }

    @PutMapping("/teams/edit-team")
    public ResponseEntity<Team> editTeam(@RequestParam("file") MultipartFile file, @RequestParam("name") String name
    ) {

        // Create a new team object
        Team team = teamRepository.findByName(name);

        // Get the original filename and extension
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);

        // Save the file to the images folder in resources directory
        String uploadDir = projectPath +"/src/main/resources/news website/imgs/logo/";
        String filePath = uploadDir + originalFilename;
        try {
            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the logo file path for the team object
        team.setLogoUrl( originalFilename);

        // Save the team object to the database
        teamRepository.save(team);

        return ResponseEntity.ok().body(team);
    }
    @DeleteMapping("/teams/delete-team/{name}")
    public String deleteTeam(@PathVariable String name) {
        try {
            teamRepository.deleteById(name);
            return "true";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping(path = "/teams/{name}/logo", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE} )
    public byte[] getPhoto(@PathVariable  String name) throws Exception{
        Team team = teamRepository.findByName(name);
        return Files.readAllBytes(Paths.get(team.getLogoUrl()));
    }

}
