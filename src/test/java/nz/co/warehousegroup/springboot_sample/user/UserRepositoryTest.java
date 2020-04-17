package nz.co.warehousegroup.springboot_sample.user;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void save_ShouldSaveUser() {
        User userToSave = new User("s.ali.abbas.raza" + RandomString.make(5) + "@gmail.com", "123456");
        User savedUser = entityManager.persistFlushFind(userToSave);

        Assertions.assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void findByUsername_ShouldReturnUser() {
        User userToSave = new User("s.ali.abbas.raza" + RandomString.make(5) + "@gmail.com", "123456");
        entityManager.persistAndFlush(userToSave);
        User user = userRepository.findByUsername(userToSave.getUsername()).orElseThrow(() -> new EntityNotFoundException("User not Found"));

        Assertions.assertThat(user.getUsername()).isEqualTo(userToSave.getUsername());
    }

    @Test
    public void findByUsername_ShouldThrowException() {
        String username = "s.ali.abbas.raza" + RandomString.make(5) + "@gmail.com";
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not Found"));
        });
    }


}