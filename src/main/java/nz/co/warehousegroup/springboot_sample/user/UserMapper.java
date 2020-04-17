package nz.co.warehousegroup.springboot_sample.user;

import nz.co.warehousegroup.springboot_sample.role.UserRole;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;


    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto convertToDto(User user, Set<UserRole> userRoles) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setPassword(null);
        if (!CollectionUtils.isEmpty(userRoles)) {
            userDto.setRoles(userRoles.stream().map(x -> x.getRole().getName()).collect(Collectors.toList()));
        }
        return userDto;
    }

    public User convertToEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
