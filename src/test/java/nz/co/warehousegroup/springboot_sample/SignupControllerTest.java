package nz.co.warehousegroup.springboot_sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.warehousegroup.springboot_sample.user.UserDto;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SignupControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void givenUserEmailPasswordRole_whenSignup_thenReturnUserId() throws Exception {
        UserDto userDto = new UserDto("s.ali.abbas.raza" + RandomString.make(5) + "@gmail.com", "123456", Arrays.asList("ADMIN"));
        this.mvc.perform(
                post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(userDto.getUsername()))
                .andExpect(jsonPath("password").isEmpty())
                .andExpect(jsonPath("roles").isArray())
                .andExpect(jsonPath("$.roles[0]", Matchers.is("ADMIN")));
    }
}
