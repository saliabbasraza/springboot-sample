package nz.co.warehousegroup.springboot_sample;

import nz.co.warehousegroup.springboot_sample.user.User;
import nz.co.warehousegroup.springboot_sample.user.UserRepository;
import nz.co.warehousegroup.springboot_sample.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
@AutoConfigureCache
public class CachingTest {
    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCaching() throws Exception {
        User user = new User(1l, "s.ali.abbas.raza@gmail.com", "123456");
        given(userRepository.findByUsername(user.getUsername())).willReturn(java.util.Optional.of(user));

        userService.findByUsername(user.getUsername());
        userService.findByUsername(user.getUsername());

        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }
}
