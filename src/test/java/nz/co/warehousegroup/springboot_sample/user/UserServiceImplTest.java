package nz.co.warehousegroup.springboot_sample.user;

import nz.co.warehousegroup.springboot_sample.role.Role;
import nz.co.warehousegroup.springboot_sample.role.RoleRepository;
import nz.co.warehousegroup.springboot_sample.role.UserRole;
import nz.co.warehousegroup.springboot_sample.role.UserRolesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRolesRepository userRoleRepository;
    @Mock
    private UserMapper userMapper;

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository, userMapper);
    }

    @Test
    void getUsersByIds() {
    }

    @Test
    void get() {
    }

    @Test
    void signup_ShouldReturnCreatedUser() {
        String username = "s.ali.abbas.raza@gmail.com";
        String password = "123456";
        List<Role> roles = Arrays.asList(new Role(1l, "USER"));
        User user = new User(1L, username, password);
        user.setUserRoles(roles.stream().map(role -> new UserRole(1L, user, role)).collect(Collectors.toSet()));
        UserDto userDto = new UserDto(username, password, roles.stream().map(Role::getName).collect(Collectors.toList()));

        given(roleRepository.findAllByNameIn(Arrays.asList("USER"))).willReturn(roles);
        given(userMapper.convertToEntity(userDto)).willReturn(user);
        given(userRepository.save(user)).willReturn(user);
        given(userMapper.convertToDto(any(User.class), anySet())).willReturn(userDto);


        UserDto userDtoResp = userService.signup(userDto);

        Assertions.assertThat(userDtoResp.getUsername()).isEqualTo(userDto.getUsername());
        Assertions.assertThat(userDtoResp.getPassword()).isEqualTo(userDto.getPassword());
        Assertions.assertThat(userDtoResp.getRoles()).isEqualTo(userDto.getRoles());

    }
}