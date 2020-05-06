package nz.co.warehousegroup.springboot_sample.user;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

//    @Cacheable(value = "users", key = "#username")
    Optional<User> findByUsername(String username);


//    @Cacheable(value = "users", key = "#id")
    Optional<User> findById(Long id);

}
