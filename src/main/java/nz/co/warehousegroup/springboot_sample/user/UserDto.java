package nz.co.warehousegroup.springboot_sample.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {
    @NotBlank
    @EqualsAndHashCode.Include
    private String username;
    @NotBlank
    private String password;
    @NotEmpty
    private List<String> roles;
}
