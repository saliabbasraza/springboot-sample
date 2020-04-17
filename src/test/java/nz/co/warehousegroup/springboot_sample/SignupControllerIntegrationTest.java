package nz.co.warehousegroup.springboot_sample;

import nz.co.warehousegroup.springboot_sample.user.UserDto;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SignupControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void signup_returnsUserDetails() {
        UserDto userDto = new UserDto("s.ali.abbas.raza" + RandomString.make(5) + "@gmail.com", "123456", Arrays.asList("ADMIN"));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        HttpEntity<UserDto> request = new HttpEntity<>(userDto, headers);

        ResponseEntity<UserDto> resp = restTemplate.postForEntity("/signup", request, UserDto.class);
        UserDto respBody = resp.getBody();
        Assertions.assertThat(respBody.getUsername()).isEqualTo(userDto.getUsername());
        Assertions.assertThat(respBody.getPassword()).isNull();
        Assertions.assertThat(respBody.getRoles()).containsAll(userDto.getRoles());
    }
}