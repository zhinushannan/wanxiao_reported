package club.kwcoder.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
class ReportApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {

        String url = "http://127.0.0.1:5000/appoint_clazz?clazz_name=20软件2&report_type=1&bot_id=123";
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class);

        if (forEntity.hasBody()) {
            Map<String, String> body = forEntity.getBody();
            String raw = body.get("raw");
            String show = body.get("show");

            System.out.println(raw);
            System.out.println(show);

        }

    }

}
