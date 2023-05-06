package ma.estl02.sportnews.repository;


import ma.estl02.sportnews.entity.Matchh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
@CrossOrigin(origins = "*")
public interface MatchhRepository extends JpaRepository<Matchh,Long> {

    @Query("SELECT m FROM Matchh m WHERE m.section LIKE %:section% ")
    List<Matchh> findBySection(@Param("section") String section);


}
