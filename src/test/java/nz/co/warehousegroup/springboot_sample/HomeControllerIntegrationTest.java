package nz.co.warehousegroup.springboot_sample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void index_returnsGreetingWithHostname() throws UnknownHostException {
        ResponseEntity<String> resp = restTemplate.getForEntity("/", String.class);

        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(resp.getBody()).isEqualTo("Greetings from Spring Boot || Running on " + InetAddress.getLocalHost().getHostName());
    }

}