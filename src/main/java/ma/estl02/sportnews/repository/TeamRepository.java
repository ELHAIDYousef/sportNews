package ma.estl02.sportnews.repository;

import ma.estl02.sportnews.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
@CrossOrigin(origins = "*")
public interface TeamRepository extends JpaRepository<Team,String> {

    Team findByName(String name);


    List<Team> findByNameContains(String keyword);
}
