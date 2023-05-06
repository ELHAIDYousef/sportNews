package ma.estl02.sportnews;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SportNewsApplicationTests {

    @Test
    void contextLoads() {
    }

//    @LocalServerPort
//    private int port;
//
//    private TestRestTemplate restTemplate = new TestRestTemplate();
//
//    @Test
//    public void testGetPlayerById() {
//        String url = "http://localhost:" + port + "/players/1";
//        ResponseEntity<Player> responseEntity = restTemplate.getForEntity(url, Player.class);
//        Player player = responseEntity.getBody();
//        System.out.println(assertThat(player).isNotNull());
//        System.out.println(assertThat(player.getId()).isEqualTo(1L));
//    }

}
