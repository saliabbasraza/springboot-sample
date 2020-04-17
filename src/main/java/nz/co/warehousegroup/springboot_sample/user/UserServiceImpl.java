package nz.co.warehousegroup.springboot_sample.user;

import nz.co.warehousegroup.springboot_sample.role.Role;
import nz.co.warehousegroup.springboot_sample.role.RoleRepository;
import nz.co.warehousegroup.springboot_sample.role.UserRole;
import nz.co.warehousegroup.springboot_sample.role.UserRolesRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRolesRepository userRoleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRolesRepository userRoleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getUsersByIds() {
        Collection<User> users = (Collection<User>) userRepository.findAll();
        return users.stream().map(x -> this.userMapper.convertToDto(x, x.getUserRoles())).collect(Collectors.toList());
    }

    @Override
    public UserDto get(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return userMapper.convertToDto(user, user.getUserRoles());
    }

    @Override
    public UserDto signup(UserDto userDto) {
        List<Role> roles = roleRepository.findAllByNameIn(userDto.getRoles());
        User user = userMapper.convertToEntity(userDto);
        userRepository.save(user);

        Set<UserRole> userRoles = new HashSet<>();
        for (Role role : roles) {
            UserRole userRole = new UserRole(user, role);
            userRoleRepository.save(userRole);
            userRoles.add(userRole);
        }

        return userMapper.convertToDto(user, userRoles);
    }
}
