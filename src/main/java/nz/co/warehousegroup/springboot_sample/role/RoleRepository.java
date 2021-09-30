package nz.co.warehousegroup.springboot_sample.role;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@Tag(name = "Role", description = "Role API")
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    List<Role> findAllByNameIn(List<String> roleNames);
}
