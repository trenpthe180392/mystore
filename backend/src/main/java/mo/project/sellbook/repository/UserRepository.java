package mo.project.sellbook.repository;

import mo.project.sellbook.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findById(Integer id);

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByUserName(String userName);
}
