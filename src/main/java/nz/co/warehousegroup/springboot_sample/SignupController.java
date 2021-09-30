package nz.co.warehousegroup.springboot_sample;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nz.co.warehousegroup.springboot_sample.user.UserDto;
import nz.co.warehousegroup.springboot_sample.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Signup", description = "Signup API")
@RequestMapping("/signup")
@RestController
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Signup new user")
    UserDto signup(@RequestBody UserDto userDto) {
        return userService.signup(userDto);
    }

}
