package ma.estl02.sportnews.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.estl02.sportnews.entity.Matchh;
import ma.estl02.sportnews.repository.MatchhRepository;
import ma.estl02.sportnews.repository.TeamRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MatchhController {

    private final MatchhRepository matchhRepository;
    private final TeamRepository teamRepository;

    public MatchhController(MatchhRepository matchhRepository, TeamRepository teamRepository) {
        this.matchhRepository = matchhRepository;
        this.teamRepository = teamRepository;
    }



    @GetMapping("/matchs")
    public List<MatchForm> getBySection(@RequestParam(name = "section", defaultValue = "")  String section){
        List<Matchh> matchesList = matchhRepository.findBySection(section);
        List<MatchForm> formList = new ArrayList<>();
        for (Matchh m :matchesList ) {
            MatchForm match = new MatchForm();
            match.setId(m.getId());
            match.setMatchDate(m.getMatchDate());
            match.setSection(m.getSection());
            match.setAwayTeamName(m.getAwayTeam().getName());
            match.setHomeTeamName(m.getHomeTeam().getName());
            match.setAwayTeamScore(m.getAwayTeamScore());
            match.setHomeTeamScore(m.getHomeTeamScore());
            match.setTime(m.getTime());
            match.setFile1(m.getHomeTeam().getLogoUrl());
            match.setFile2(m.getAwayTeam().getLogoUrl());
            formList.add(match);
        }
        return formList;
    }



    @PostMapping("/matchs/add-match")
    @CrossOrigin(origins = "*")
    public Matchh addMatch(@ModelAttribute MatchForm form) {
        Matchh match = new Matchh();
        match.setMatchDate(form.getMatchDate());
        match.setAwayTeam(teamRepository.findByName(form.getAwayTeamName()));
        match.setHomeTeam(teamRepository.findByName(form.getHomeTeamName()));
        match.setAwayTeamScore(form.getAwayTeamScore());
        match.setHomeTeamScore(form.getHomeTeamScore());
        match.setSection(form.getSection());
        match.setTime(form.getTime());

        return matchhRepository.save(match);
    }

    @PutMapping("/matchs/edit-match")
    public Matchh editMatch(@RequestParam Long id, @RequestParam String awayTeamName, @RequestParam String homeTeamName,
                            @RequestParam String awayTeamScore, @RequestParam String homeTeamScore,
                            @RequestParam  String matchDate , @RequestParam String section , @RequestParam String time ) {
        Matchh match = matchhRepository.findById(id).get();
        match.setMatchDate(matchDate);
        match.setAwayTeam(teamRepository.findByName(awayTeamName));
        match.setHomeTeam(teamRepository.findByName(homeTeamName));
        match.setAwayTeamScore(awayTeamScore);
        match.setHomeTeamScore(homeTeamScore);
        match.setSection(section);
        match.setTime(time);

        return matchhRepository.save(match);
    }

    @DeleteMapping("/matchs/delete-match/{id}")
    public String deleteMatch(@PathVariable Long id) {

        try {
            matchhRepository.deleteById(id);
            return "true";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/matchs/{id}")
    public MatchForm getMatchById(@PathVariable Long id) {
        Matchh m =  matchhRepository.findById(id).orElse(null);
        MatchForm match = new MatchForm();
        match.setId(m.getId());
        match.setTime(m.getTime());
        match.setFile1(m.getHomeTeam().getLogoUrl());
        match.setFile2(m.getAwayTeam().getLogoUrl());
        match.setMatchDate(m.getMatchDate());
        match.setSection(m.getSection());
        match.setAwayTeamName(m.getAwayTeam().getName());
        match.setHomeTeamName(m.getHomeTeam().getName());
        match.setAwayTeamScore(m.getAwayTeamScore());
        match.setHomeTeamScore(m.getHomeTeamScore());
        return match;
    }


}
@Data @NoArgsConstructor@AllArgsConstructor
 class MatchForm {
    private Long id;
    private String homeTeamName;
    private String awayTeamName;
    private String homeTeamScore;
    private String awayTeamScore;
    private String section;
    private String matchDate;
    private String time;
    private String file2;
    private String file1;

}
