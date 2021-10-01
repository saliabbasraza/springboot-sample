package nz.co.warehousegroup.springboot_sample.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "User API")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Find all users", security = @SecurityRequirement(name = "bearerAuth"))
    public List<UserDto> getAllUsers() {
        return userService.getUsersByIds();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find by ID", security = @SecurityRequirement(name = "bearerAuth"))
    public UserDto getUser(@PathVariable(name = "id") Long id) {
        return userService.get(id);
    }
}