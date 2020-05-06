package nz.co.warehousegroup.springboot_sample.user;

import java.util.List;

public interface UserService {

    List<UserDto> getUsersByIds();

    UserDto get(Long id);
    UserDto findByUsername(String username);

    UserDto signup(UserDto user);
}
