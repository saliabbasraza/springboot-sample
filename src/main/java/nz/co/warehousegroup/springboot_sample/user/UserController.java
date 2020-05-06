package nz.co.warehousegroup.springboot_sample.user;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "User API")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getUsersByIds();
    }

//    @GetMapping("/{id}")
//    public UserDto getUser(@PathVariable(name = "id", required = true) Long id) {
//        return userService.get(id);
//    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable(name = "username", required = true) String username) {
        return userService.findByUsername(username);
    }
}