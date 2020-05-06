package nz.co.warehousegroup.springboot_sample.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl userService;

    @Test
    public void whenGetByUsernameTwice_thenReturnUserFromCache() throws Exception {
        UserDto userDto = new UserDto("s.ali.abbas.raza@gmail.com", "123456", Arrays.asList("ADMIN"));
        given(userService.findByUsername(anyString())).willReturn(userDto);

        mvc.perform(get("/api/user/{username}", userDto.getUsername()))
                .andExpect(status().isOk());
        mvc.perform(get("/api/user/{username}", userDto.getUsername()))
                .andExpect(status().isOk());
        
        verify(userService, times(1)).findByUsername(userDto.getUsername());
    }
}
