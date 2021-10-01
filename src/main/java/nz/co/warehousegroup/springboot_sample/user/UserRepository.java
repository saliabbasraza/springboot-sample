package nz.co.warehousegroup.springboot_sample.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@Tag(name = "User", description = "User API")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
